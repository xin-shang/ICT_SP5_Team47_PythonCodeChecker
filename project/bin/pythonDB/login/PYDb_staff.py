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
    
def staff_insertRows(c,username,password):
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
    
def staff_readText(path):
    with open(path, 'rb') as file:
        data = file.read().rstrip()
        return data


def staff_bCheckUserName(c,userName):
    c.execute("SELECT rowid, * FROM " + staffTable)
    items = c.fetchall()
    count = 0
    for item in items:
        if item[1] == userName:
           count = count + 1
    if count >= 1:
        return True
    else:
        return False


def staff_bCheckPassword(c,username,password):
    c.execute("SELECT rowid, * FROM " + staffTable)
    items = c.fetchall()
    count = 0
    for item in items:
        if item[1] == username and item[2] == password:
            count = count + 1
    if count >= 1:
        return True
    else:
        return False
    
def staff_CreateText_inputValue(path, input_string):
    with open(path, "w+",newline="") as output:
        output.write(input_string)