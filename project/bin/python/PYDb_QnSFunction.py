#!/usr/bin/env python3
from cmath import exp
import sqlite3

table = "qns"

#---------TABLE NAME, change it here---------

def DB_WriteText_inputValue(path, input_string):
    with open(path, "w+",newline='') as output:
        output.write(input_string)


def dropTable(c):
    c.execute("DROP TABLE "+ table)

#---------TABLE NAME, change it here---------
def createTable(c):
    C_table = """CREATE TABLE """+ table +"""(
        userName text NOT NULL,
        question text NOT NULL PRIMARY KEY,
        solution text NOT NULL,
        answer text NOT NULL,
        markScheme NOT NULL
    )"""
    #create a table
    c.execute(C_table)

def deleteTable(c):
    c.execute("DELETE FROM "+ table)
    
def insertRows(c,username,question,solution,answer,mark):
    list = [(username,question,solution,answer,mark)]
    c.executemany("INSERT INTO "+ table +" VALUES(?,?,?,?,?)",list)
    
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

def getUserName(c,id):
    userName = ""  
    c.execute("SELECT rowid, * FROM " + table)
    values = c.fetchall()
    for value in values:
        if(value[0] == id):
            userName = value[1]
    return userName
 
        
def getQuestion(c,id):
    question = ""  
    c.execute("SELECT rowid, * FROM " + table)
    values = c.fetchall()
    for value in values:
        if(value[0] == id):
            question = value[2]
    return question

def getSolution(c,id):
    solution = ""  
    c.execute("SELECT rowid, * FROM " + table)
    values = c.fetchall()
    for value in values:
        if(value[0] == id):
            solution = value[3]
    return solution
    
def getAnswer(c,id):
    answer = ""  
    c.execute("SELECT rowid, * FROM " + table)
    values = c.fetchall()
    for value in values:
        if(value[0] == id):
            answer = value[4]
    return answer


def getMarkScheme(c,id):
    markScheme = ""  
    c.execute("SELECT rowid, * FROM " + table)
    values = c.fetchall()
    for value in values:
        if(value[0] == id):
            markScheme = value[5]
    return markScheme


def getTableLength(c):
    c.execute("SELECT rowid, * FROM " + table)
    values = c.fetchall()
    return len(values)

def getRow(c,id):
    rows = 0
    c.execute("SELECT rowid, * FROM " + table)
    values = c.fetchall()
    for value in values:
        if(value[0] == id):
            rows = value
    return rows


    
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