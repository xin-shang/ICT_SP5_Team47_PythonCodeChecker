#!/usr/bin/env python3
from cmath import exp
import sqlite3

studentTable = "student"
#---------TABLE NAME, change it here---------

def student_dropTable(c):
    c.execute("DROP TABLE "+ studentTable)

def student_getHeader(c):
    c.execute('select * from '+ studentTable)
    names = list(map(lambda x: x[0], c.description))
    return names


#---------TABLE NAME, change it here---------
def student_createTable(c):
    C_table = """CREATE TABLE """+ studentTable +"""(
        user_id text PRIMARY KEY,
        password text NOT NULL
    )"""
    #create a table
    c.execute(C_table)

def student_cleanTable(c):
    c.execute("DELETE FROM "+ studentTable)
    
def student_insertRows(c,username,password):
    id = student_getlen(c) + 1
    list = [(username,password)]
    c.executemany("INSERT INTO "+ studentTable +" VALUES(?,?)",list)
    

#print all the data in table
def student_printAll(c):
    c.execute("SELECT rowid, * FROM " + studentTable)
    items = c.fetchall()    
    for item in items:
        print(str(item))

def student_getlen(c):
    c.execute("SELECT rowid, * FROM " + studentTable)
    items = c.fetchall()
    return len(items)


def student_bcheckUserID(c,username):
    c.execute("SELECT user_id FROM "+studentTable+" WHERE user_id = ?", (username,))
    data=len(c.fetchall())

    if data != 0:
        return True
    else:
        return False
    
def student_readText(path):
    with open(path, 'rb') as file:
        data = file.read().rstrip()
        return data


def student_bCheckUserName(c,userName):
    c.execute("SELECT rowid, * FROM " + studentTable)
    items = c.fetchall()
    count = 0
    for item in items:
        if item[1] == userName:
           count = count + 1
    if count >= 1:
        return True
    else:
        return False


def student_bCheckPassword(c,username,password):
    c.execute("SELECT rowid, * FROM " + studentTable)
    items = c.fetchall()
    count = 0
    for item in items:
        if item[1] == username and item[2] == password:
            count = count + 1
    if count >= 1:
        return True
    else:
        return False
    
def student_CreateText_inputValue(path, input_string):
    with open(path, "w+",newline="") as output:
        output.write(input_string)