'''

Write Java code to print system date & time in format like 
2021-10-02 10:30:00 AM

Sample Output: 2025-06-04 11:35:27 AM

'''
import datetime

# print(datetime.date.today())
res = datetime.datetime.now()
print(res.strftime("%Y-%m-%d %I:%M:%S %p"), end = "")