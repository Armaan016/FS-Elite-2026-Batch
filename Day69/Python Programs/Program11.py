'''
Write a Python Program to read a sentence and print each word reversed, but 
maintain the original word order.

Input: 
------
Java is fun

Output:
-------
avaJ si nuf

'''
s = input()

for word in s.split(" "):
    print(word[::-1], end = " ")