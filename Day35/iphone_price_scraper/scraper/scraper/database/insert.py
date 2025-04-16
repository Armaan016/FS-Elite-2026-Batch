from pymongo import MongoClient
import config

client = MongoClient(config.MONGO_URI)
db = client[config.DB_NAME]

def insert_price(data):
    if 'iphone' in data.get('model_name', '').lower():
        print("Inserting:", data)  
        db.prices.insert_one(data)
    else:
        print("Skipped (not iPhone):", data.get('model_name'))
