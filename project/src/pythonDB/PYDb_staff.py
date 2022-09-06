#!/usr/bin/env python3
from cmath import exp
import sqlite3

staffTable = "staff"

#---------TABLE NAME, change it here---------

def staff_dropTable(c):
    c.execute("DROP TABLE "+ staffTable)

def staff_getHeader(c):
    c.execute('select * from '+ staffTable)
    names = list(map(lambda x: x[0], c.description))
    return names


#---------TABLE NAME, change it here---------
def staff_createTable(c):
    C_table = """CREATE TABLE """+ staffTable +"""(
        user_id text PRIMARY KEY,
        password text NOT NULL
    )"""
    #create a table
    c.execute(C_table)

def staff_cleanTable(c):
    c.execute("DELETE FROM "+ staffTable)
    
def user_insertRows(c,username,password):
    id = staff_getlen(c) + 1
    list = [(username,password)]
    c.executemany("INSERT INTO "+ staffTable +" VALUES(?,?)",list)
    

#print all the data in table
def staff_printAll(c):
    c.execute("SELECT rowid, * FROM " + staffTable)
    items = c.fetchall()    
    for item in items:
        print(str(item))

def staff_getlen(c):
    c.execute("SELECT rowid, * FROM " + staffTable)
    items = c.fetchall()
    return len(items)


def staff_bcheckUserID(c,username):
    c.execute("SELECT user_id FROM "+staffTable+" WHERE user_id = ?", (username,))
    data=len(c.fetchall())

    if data != 0:
        return True
    else:
        return False  