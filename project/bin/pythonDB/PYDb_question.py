#!/usr/bin/env python3
from cmath import exp
from datetime import datetime

import sqlite3

questionTable = "question"

#---------TABLE NAME, change it here---------

def question_dropTable(c):
    c.execute("DROP TABLE "+ questionTable)

def question_getHeader(c):
    c.execute('select * from '+ questionTable)
    names = list(map(lambda x: x[0], c.description))
    return names


#---------TABLE NAME, change it here---------
def question_createTable(c):
    C_table = """CREATE TABLE """+ questionTable +"""(
        id text PRIMARY KEY,
        user_id text NOT NULL,
        question text NOT NULL UNIQUE,
        FOREIGN KEY(user_id) REFERENCES user(user_id)
    )"""
    #create a table
    c.execute(C_table)

def question_cleanTable(c):
    c.execute("DELETE FROM "+ questionTable)
    
def question_insertRows(c,user_id,question,):
    id = question_createID(c,question)
    list = [(id,user_id,question)]
    c.executemany("INSERT INTO "+ questionTable +" VALUES(?,?,?)",list)
    
    
def question_createID(c,questionString):
    id = ""
    if len(str(questionString)) != 0:
        now = datetime.now()
        current_time = now.strftime("%H%M%S")
        id = id + questionString[0] + questionString[-1] + str(question_getlen(c)+1) + "_" +str(current_time)
    return id       
    
    
#print all the data in table
def question_printAll(c):
    c.execute("SELECT rowid, * FROM " + questionTable)
    items = c.fetchall()    
    for item in items:
        print(str(item))

def question_getlen(c):
    c.execute("SELECT rowid, * FROM " + questionTable)
    items = c.fetchall()
    return len(items)  


def question_getID(c,question):
    id = ""
    c.execute("SELECT * FROM " + questionTable)
    items = c.fetchall()    
    for item in items:
        if item[2] == question:
            id = item[0]
    return id

def question_bcheckQuestion(c,question):
    c.execute("SELECT question FROM "+questionTable+" WHERE question = ?", (question,))
    data=len(c.fetchall())
    #这里判断可能要改成相似率
    if data != 0:
        return True
    else:
        return False  