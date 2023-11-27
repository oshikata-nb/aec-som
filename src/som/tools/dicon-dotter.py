#
# !!execute at project home!!
#

import os
import xml.parsers.expat

PATH='src'
OUTPUT='target/dicon-graph.gif'

out = open('data.txt', 'w')
out.write("digraph dicon {\n")

current = ""
def start_element(name, att):
	if name == 'include':
		a = att['path']
		if a.rfind('/') > 0:
			a = a[a.rfind('/') + 1:]
		out.write("\t\"" + current[0:-6] + "\" -> \"" + a[0:-6] + "\";\n")

for root, dirs, files in os.walk(PATH):
	for filename in files:
		if filename.find('.dicon') == len(filename) - len('.dicon'):
			target = root + '/' + filename
			current = filename
			parser = xml.parsers.expat.ParserCreate()
			parser.StartElementHandler = start_element
			parser.ParseFile(open(target, 'r'))

out.write("}\n")
out.close()

os.system("dot -Tgif data.txt -o %s" % (OUTPUT))
os.remove('data.txt')
