import sys, getopt
import time
import urllib
import urllib2
import httplib

url='http://localhost:9000'
headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"}

def main(argv):
    inputfile = './sorted_data_simple.csv'
    try:
      opts, args = getopt.getopt(argv,"hi:",["ifile="])
    except getopt.GetoptError:
      print 'test.py -i <inputfile>'
      sys.exit(2)
    for opt, arg in opts:
      if opt == '-h':
         print 'test.py -i <inputfile>'
         sys.exit()
      elif opt in ("-i", "--ifile"):
         inputfile = arg
    print 'Input file is ', inputfile

    sys.argv[0]
    f=open(inputfile)
    i=0
    req = urllib2.Request(url)
    for line in f.xreadlines():
        i=i+1
        print '%s: %s'%(i,line)
        req=urllib2.Request(url, line)
        response = urllib2.urlopen(req)
        time.sleep(1)

    print 'exit'

if __name__ == "__main__":
   main(sys.argv[1:])

