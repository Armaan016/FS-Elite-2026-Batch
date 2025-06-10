# Write a python program to find the nth prime number. 
# The value of n should be input by the user.

# Sample Input:
# ---------------
# 5

# Sample Output:
# -----------------
# 11

n = int(input())

def isPrime(n):
    for i in range(2, n):
        if n % i == 0: return False
    return True

curr = 2
while n > 0:
    if isPrime(curr):
        res = curr
        n -= 1
        
    curr += 1

print(res)