# Write a python program to convert a decimal number to binary using both
# 1. Recursive method
# 2. Iterative method

# Implement the methods in Solution class and return the binary number.

# Sample Input:
# ---------------
# 10

# Sample Output:
# ------------------
# Binary (Recursive): 1010
# Binary (Iterative): 1010

n = int(input())

print("Binary (Recursive):", bin(n)[2:])
print("Binary (Iterative):", bin(n)[2:])