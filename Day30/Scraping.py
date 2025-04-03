import requests
from bs4 import BeautifulSoup
import pymongo

client = pymongo.MongoClient("mongodb://<IPV4_address>:27017/") # Update mongod.conf with your IPV4 address
db = client["bookstore"]
collection = db["books"]

BASE_URL = "https://books.toscrape.com/"

def get_books_from_page(url):
    """Scrapes all books from a single page."""
    response = requests.get(url)
    soup = BeautifulSoup(response.text, "html.parser")

    books = []
    for book in soup.select(".product_pod"):
        title = book.h3.a["title"]
        price = book.select_one(".price_color").text.strip()
        availability = "In" if "In stock" in book.select_one(".availability").text.strip() else "Out"
        rating = book.p["class"][1]  
        category = "Books"  

        book_data = {
            "title": title,
            "price": price,
            "availability": availability,
            "rating": rating,
            "category": category
        }
        books.append(book_data)

    return books

def scrape_all_books():
    """Scrapes all books across multiple pages."""
    page = 1
    while True:
        url = f"https://books.toscrape.com/catalogue/page-{page}.html"
        response = requests.get(url)
        if response.status_code != 200:
            break  

        books = get_books_from_page(url)
        if not books:
            break  

        collection.insert_many(books) 
        print(f"Page {page} scraped and stored.")
        page += 1

scrape_all_books()
print("All books scraped successfully! 🎉")
