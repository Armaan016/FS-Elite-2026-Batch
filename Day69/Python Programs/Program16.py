'''

Write a python program, for given a birthdate in yyyy-MM-dd format, calculate 
the person’s current age in years, months, and days.

Input:
------
1990-05-25

Output:
-------
Age: 35 years, 0 months, 16 days

'''

import datetime

s1 = input()
d1 = datetime.datetime.strptime(s1, "%Y-%m-%d")

currDate = datetime.date.today()

years = currDate.year - d1.year
months = currDate.month - d1.month
days = currDate.day - d1.day

if(days < 0):
    months -= 1
    days += 31

if(months < 0):
    years -= 1
    months += 12   

print(f"Age: {years} years, {months} months, {days} days")