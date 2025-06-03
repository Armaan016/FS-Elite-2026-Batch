from flask import Flask, request, jsonify
from flask_cors import CORS
import fitz  
import os
import torch
from sentence_transformers import SentenceTransformer, util

app = Flask(__name__)
CORS(app)

PDF_DIR = "../backend/uploads"  

device = 'cuda' if torch.cuda.is_available() else 'cpu'
model = SentenceTransformer("msmarco-MiniLM-L-12-v3").to(device)

@app.route("/rag", methods=["POST"])
def rag():
    data = request.get_json()
    question = data.get("question")

    if not question:
        return jsonify({"answer": "No question provided"}), 400

    combined_text = extract_all_pdf_text(PDF_DIR)
    if not combined_text.strip():
        return jsonify({"answer": "No PDF content available"}), 404

    contexts = get_relevant_contexts(question, combined_text)
    answer = "\n\n".join(contexts)
    
    print(f"Answer: {answer}")
    return jsonify({"answer": answer})

def extract_all_pdf_text(directory):
    text = ""
    for file in os.listdir(directory):
        if file.endswith(".pdf"):
            pdf_path = os.path.join(directory, file)
            doc = fitz.open(pdf_path)
            for page in doc:
                text += page.get_text()
            doc.close()
    return text


import re
def get_relevant_contexts(query, text, window_size=4):
    sentences = [s.strip() for s in re.split(r'\.\s|\.$', text.strip()) if s.strip()]
    sentence_windows = [
        ". ".join(sentences[i:i + window_size]) + "." for i in range(len(sentences) - window_size + 1)
    ]

    window_embeddings = model.encode(sentence_windows, convert_to_tensor=True).to(device)
    query_embedding = model.encode(query, convert_to_tensor=True).to(device)

    cosine_scores = util.pytorch_cos_sim(query_embedding, window_embeddings)
    top_k = torch.topk(cosine_scores, k=3)

    top_contexts = [sentence_windows[idx] for idx in top_k.indices[0].tolist()]
    return top_contexts


if __name__ == "__main__":
    app.run(port=8000, debug=True)
