'''

Write python program to find and return minimum number of denominations – given 
an infinite supply of each denomination of {1, 2, 5, 10, 20, 50, 100, 200,500, 
2000} and a target value N

Sample Input: 
-------------
187

Sample Output: 
--------------
[100, 50, 20, 10, 5, 2]

'''
n = int(input())

denoms = [2000, 500, 200, 100, 50, 20, 10, 5, 2, 1]

res = []
for denom in denoms:
    if n == 0: break
    if denom > n: continue
    
    c = int(n / denom)
    # print("c:", c, "denom:", denom, "n:", n)
    
    while c > 0: 
        res.append(denom)
        c -= 1
        n -= denom

print(res)