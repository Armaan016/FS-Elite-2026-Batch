# Write python code for a method which takes a String (a sentence) as input 
# and returns a Map. The Map key is String (word in the String) and 
# value is frequency of the word in the given sentence.
# (In the words ignore any special characters)

# Sample Input:
# -------------
# Java is fun, and Ja#va@ is powerful 

# Sample Output:
# --------------
# java: 2
# is: 2
# fun: 1
# and: 1
# powerful: 1

s = input()

d = dict()
words = ""
# for word in words:
#     word = word.lower()
#     w = word.replace("#", "")
#     w = w.replace("@", "")
#     w = w.replace(",", "")
#     w = w.replace("$", "")
#     d[w] = 0

for ch in s:
    if ch.isalnum() or ch.isspace(): words += ch.lower()

words = words.split(" ")

for w in words:
    # print(w)
    d[w] = d.get(w, 0) + 1
    
# for key in d.keys():
#     print(f"%s: %d" % (key, d[key]))
print(d)