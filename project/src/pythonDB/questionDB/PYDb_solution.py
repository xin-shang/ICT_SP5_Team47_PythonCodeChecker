#!/usr/bin/env python3
from cmath import exp
from datetime import datetime
import sqlite3

solutionTable = "solution"

#---------TABLE NAME, change it here---------

def solution_dropTable(c):
    c.execute("DROP TABLE "+ solutionTable)

def solution_getHeader(c):
    c.execute('select * from '+ solutionTable)
    names = list(map(lambda x: x[0], c.description))
    return names

#---------TABLE NAME, change it here---------
def solution_createTable(c):
    C_table = """CREATE TABLE """+ solutionTable +"""(
        id text PRIMARY KEY,
        question_id text NOT NULL,
        solution text NOT NULL,
        answer text NOT NULL,
        FOREIGN KEY(question_id) REFERENCES question(id)
    )"""
    #create a table
    c.execute(C_table)

def solution_cleanTable(c):
    c.execute("DELETE FROM "+ solutionTable)
    
def solution_insertRows(c,questionID,solution,answer):
    id = question_createID(c,solution)
    list = [(id,questionID,solution,answer)]
    c.executemany("INSERT INTO "+ solutionTable +" VALUES(?,?,?,?)",list)
    
    
def question_createID(c,questionString):
    id = ""
    if len(str(questionString)) != 0:
        now = datetime.now()
        current_time = now.strftime("%H%M%S")
        id = id + questionString[0] + questionString[-1] + str(solution_getlen(c)+1) + "_" +str(current_time)
    return id       

#print all the data in table
def solution_printAll(c):
    c.execute("SELECT rowid, * FROM " + solutionTable)
    items = c.fetchall()    
    for item in items:
        print(str(item))

def solution_getlen(c):
    c.execute("SELECT rowid, * FROM " + solutionTable)
    items = c.fetchall()
    return len(items)  
