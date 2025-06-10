'''

Write a program that finds the longest substring that reads the same forwards 
and backwards.

Input: 
------
babad

Output: 
-------
bab or aba

'''
s = input()

def isPalindrome(s):
    l = 0
    r = len(s) - 1
    
    while l < r:
        if s[l] != s[r]: return False
        l += 1
        r -= 1
    return True
    
n = len(s)
maxLen = 0
res = s

for i in range(0, n):
    for j in range(i, n + 1):
        if isPalindrome(s[i: j]) and maxLen < j - i:
            maxLen = j - i
            res = s[i: j]
            
print(res)