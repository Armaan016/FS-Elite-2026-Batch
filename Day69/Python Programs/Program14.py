'''

Write a program that reads a sentence, count and display the total number of 
vowels and consonants.

Input: 
------
Hello World

Output:
-------
Vowels: 3, Consonants: 7

'''

s = input()

def isVowel(c):
    c = c.lower()
    return c == 'a' or c == 'e' or c == 'i' or c == 'o' or c == 'u'

vowels = 0
cons = 0
for c in s:
    if isVowel(c): vowels += 1
    elif not c.isspace(): cons += 1

print(f"Vowels: %d, Consonants: %d" % (vowels, cons))