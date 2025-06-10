'''
Write a python program to read a comma-separated values file and print its 
contents in table format, replacing commas with tabs or spaces.

Input File: 
------
file.csv

Output:
-------
name age
John 20
Jane 25

Explanation:
-------------
File contains:- 

name,age
John,20
Jane,25

'''
# import pandas as pd
# df = pd.read_csv("file.csv");
file = input()

# print(df)
with open(file, 'r') as f:
    line = f.read()
    print(line.replace(",", " "))
