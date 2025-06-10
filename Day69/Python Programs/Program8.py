'''

Problem: 
Write Python code to identify if given two strings are Anagrams 
(strings that contain same set of alphabets)

Sample Input: 
-------------
listen silent

Sample Output: 
--------------
true

'''
l = input().split(" ")

s1 = l[0]
s2 = l[1]

d1 = dict()
for c in s1:
    d1[c] = d1.get(c, 0) + 1

d2 = dict()
for c in s2:
    d2[c] = d2.get(c, 0) + 1

flag = True
for d in d1:
    if d not in d2 or d1[d] != d2[d]: 
        print("false") 
        flag = False
        break
        
if flag: print("true")