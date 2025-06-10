'''

Write a python program that reads two timestamps (yyyy-MM-dd HH:mm:ss format) and
display the time elapsed between them in minutes and seconds.

Input: 
------
2025-06-04 10:30:00
2025-06-04 11:15:40

Output: 
-------
Elapsed: 45 minutes 40 seconds

'''

from datetime import datetime, timedelta

d1 = input()
d2 = input()

t1 = datetime.strptime(d1, "%Y-%m-%d %H:%M:%S")
t2 = datetime.strptime(d2, "%Y-%m-%d %H:%M:%S")

secs = abs(t2 - t1).total_seconds()

print(f"Elapsed: {int(secs//60)} minutes, {int(secs % 60)} seconds")

# print(d2 - d1)