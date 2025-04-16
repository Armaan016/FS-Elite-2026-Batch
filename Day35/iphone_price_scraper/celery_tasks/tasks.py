from celery import Celery
import subprocess
import config
import os   

app = Celery('tasks', broker=config.REDIS_BROKER_URL)

@app.task
def run_all_spiders():
    # subprocess.run(['scrapy', 'crawl', 'amazon'])
    # subprocess.run(['scrapy', 'crawl', 'flipkart'])
    # print("Running Flipkart spider...")
    # os.system("cd scraper && scrapy crawl flipkart")

    # print("Running Amazon spider...")
    # os.system("cd scraper && scrapy crawl amazon")
    
    subprocess.run("scrapy crawl flipkart", shell=True, cwd="scraper")
    subprocess.run("scrapy crawl amazon", shell=True, cwd="scraper")