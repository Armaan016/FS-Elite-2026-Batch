from pymongo import MongoClient
client = MongoClient("mongodb://localhost:27017/")
db = client["iphone_prices"]
for record in db.prices.find():
    print(record)
