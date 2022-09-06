#!/usr/bin/env python3
from cmath import exp
from datetime import datetime

import sqlite3

markPointTable = "markPoint"
#---------TABLE NAME, change it here---------
def markPoint_dropTable(c):
    c.execute("DROP TABLE "+ markPointTable)

def markPoint_getHeader(c):
    c.execute('select * from '+ markPointTable)
    names = list(map(lambda x: x[0], c.description))
    return names


#---------TABLE NAME, change it here---------
def markPoint_createTable(c):
    C_table = """CREATE TABLE """+ markPointTable +"""(
        question_id text NOT NULL,
        keyword_id text NOT NULL,
        score int NOT NULL,
        FOREIGN KEY(question_id) REFERENCES question(id),
        FOREIGN KEY(keyword_id) REFERENCES keyword(id)
    )"""
    #create a table
    c.execute(C_table)

def markPoint_cleanTable(c):
    c.execute("DELETE FROM "+ markPointTable)
    
def markPoint_insertRows(c,question_id,keyword_id,score):
    list = [(question_id,keyword_id,score)]
    c.executemany("INSERT INTO "+ markPointTable +" VALUES(?,?,?)",list)
    
        
#print all the data in table
def markPoint_printAll(c):
    c.execute("SELECT rowid, * FROM " + markPointTable)
    items = c.fetchall()    
    for item in items:
        print(str(item))

def markPoint_getlen(c):
    c.execute("SELECT rowid, * FROM " + markPointTable)
    items = c.fetchall()
    return len(items)  
