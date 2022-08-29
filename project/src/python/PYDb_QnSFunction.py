
import random
import sqlite3

table = "qs"

#---------TABLE NAME, change it here---------

def DB_CreateText_inputValue(path, input_string):
    with open(path, "w+",newline="") as output:
        output.write(input_string)


#---------TABLE NAME, change it here---------
def create_sqlite(c):
    C_table = """CREATE TABLE """+ table +"""(
        question text,
        solution text,
        markScheme text
    )"""
    #create a table
    c.execute(C_table)

def deletAllFromT(c):
    c.execute("DELETE FROM "+ table)
    
def insert_list(c,question,solution,mark):
    list = [(question,solution,mark)]
    c.executemany("INSERT INTO "+ table +" VALUES(?,?,?)",list)
    
#print all the data in table
def printAll(c):
    c.execute("SELECT rowid, * FROM " + table)
    items = c.fetchall()    
    for item in items:
        print(str(item))

def readText(path):
    with open(path, 'rb') as file:
        data = file.read().rstrip()
        return data

def return_random_rows(c):
    rows = ""
    c.execute("SELECT rowid, * FROM " + table)
    items = c.fetchall()
    random_r = random.randint(1, len(items))
    for value in items:
        if(value[0] == random_r):
            rows = value
    return rows


#create Score List
def createScoreList(c,id):
    scoreScheme = ""
    c.execute("SELECT rowid, * FROM " + table)
    items = c.fetchall()

    #only select the markScheme
    for value in items:
        if(value[0] == id):
            scoreScheme = value[3]

    stringL = scoreScheme.split(',')
    ls_mark = transferTXT2ScoreList(stringL)
    return ls_mark

#transfer text to score list
def transferTXT2ScoreList(score_list):
    count = 0
    #a value for short memory
    mark_point = ""
    new_score = 0

    #create a list to store each lines of data
    mark_list = []
    for character in score_list:
        if(count%2 == 0):
            mark_point = remove_space(character)
        else:
            new_score = remove_space(character)
            signle_mk = (mark_point,new_score)
            mark_list.append(signle_mk)
        count = count + 1  
    return mark_list

#check the space front of the string
def remove_space(string):
    if(string[0] == " "):
        return string[1:]
    else:
        return string
    
def readText(path):
    with open(path, 'rb') as file:
        data = file.read().rstrip()
        return data