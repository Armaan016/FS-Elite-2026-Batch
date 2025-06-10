'''
Problem: 
--------
Write a Python code to identify the nth largest number without 
sorting the array


Sample Input:
-------------
4 2
3 1 5 2

Sample Output:
--------------
3

'''
import heapq

l1 = input()
l2 = input()

l1 = l1.split(" ")
l = int(l1[0])
n = int(l1[1])

nums = []
for num in l2.split(" "):
    nums.append(int(num))
    
# print(nums)
print(heapq.nlargest(n, nums)[-1])