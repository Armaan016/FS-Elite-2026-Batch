'''
Problem: Write a program to count each word's first index and total occurrences 
in a sentence.

Sample Input: 
apple banana apple orange banana apple

Sample Output:
apple -> first index: 0, count: 3
banana -> first index: 6, count: 2
orange -> first index: 19, count: 1

'''

s = input()

first = dict()
freq = dict()

for word in s.split(" "):
    if word not in first:
        first[word] = s.index(word)
    # i += 1
    freq[word] = freq.get(word, 0) + 1
    
for word in first.keys():
    print(word, "->", "first index:", first[word], "count:", freq[word])