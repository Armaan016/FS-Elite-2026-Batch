'''

Write a python program that reads a sentence and counts how many times each word 
appears. Display only the words that occur more than once.

Input: 
------
this is a test this test is easy

Output:
-------
this -> 2
is -> 2
test -> 2

'''

d = dict()

s = input().split(" ")
for word in s:
    d[word] = d.get(word, 0) + 1
    
for e in d:
    if d[e] > 1: print(e, "->", d[e])
