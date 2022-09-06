#!/usr/bin/env python3
import sqlite3

markTable = "keywords"

#---------TABLE NAME, change it here---------

def keywords_dropTable(c):
    c.execute("DROP TABLE "+ markTable)

def keywords_getHeader(c):
    c.execute('select * from '+ markTable)
    names = list(map(lambda x: x[0], c.description))
    return names

#---------TABLE NAME, change it here---------
def keywords_createTable(c):
    C_table = """CREATE TABLE """+ markTable +"""(
        id text PRIMARY KEY,
        keywords text NOT NULL UNIQUE
    )"""
    #create a table
    c.execute(C_table)

def keywords_cleanTable(c):
    c.execute("DELETE FROM "+ markTable)
    
def keywords_insertRows(c,keyword):
    id = keywords_createID(c,keyword)
    list = [(id,keyword)]
    c.executemany("INSERT INTO "+ markTable +" VALUES(?,?)",list)



def keywords_createID(c,keywordsString):
    id = ""
    if len(str(keywordsString)) != 0:
        id = id + keywordsString[0] + keywordsString[-1] + str(keywords_getlen(c)+1)
    return id    

    
    
#print all the data in table
def keywords_printAll(c):
    c.execute("SELECT rowid, * FROM " + markTable)
    items = c.fetchall()    
    for item in items:
        print(str(item))
        
        
def keywords_getlen(c):
    c.execute("SELECT rowid, * FROM " + markTable)
    items = c.fetchall()
    return len(items)  

def keywords_getID(c,keyword):
    id = ""
    c.execute("SELECT * FROM " + markTable)
    items = c.fetchall()    
    for item in items:
        if item[1] == keyword:
            id = item[0]
    return id
        
def keywords_bcheckKeyword(c,keyword):
    c.execute("SELECT keywords FROM "+markTable+" WHERE keywords = ?", (keyword,))
    data=len(c.fetchall())

    if data != 0:
        return True
    else:
        return False  