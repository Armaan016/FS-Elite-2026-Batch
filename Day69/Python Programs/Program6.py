'''

Write a Python code to return the length of longest sub-string without repeating 
characters

Sample Input: 
-------------
abcabcbb

Sample Output: 
--------------
3

'''
s = input()

n = len(s)
i = 0
j = 0

res = 0
st = set()
for j in range(0, n):
    c = s[j]
    if c not in st: res = max(res, j - i + 1)
    else :
        while c in st:
            st.remove(s[i])
            i += 1
    
    st.add(c)
    j += 1
    
print(res)
    