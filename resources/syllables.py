from nltk.corpus import cmudict
import sys

d = cmudict.dict()
def nsyl(word):
	try:
		returner = [len(list(y for y in x if y[-1].isdigit())) for x in d[word.lower()]][0]
	except KeyError:
		returner = 0
	return returner

word_list = sys.argv[1].split(",")
syllable_count = []
for word in word_list:
	syllable_count.append(nsyl(word))
print syllable_count
