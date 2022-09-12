#!/usr/bin/env python3
import sqlite3

markTable = "keywordAlternative"

#---------TABLE NAME, change it here---------
def KeywordAl_dropTable(c):
    c.execute("DROP TABLE "+ markTable)

def KeywordAl_getHeader(c):
    c.execute('select * from '+ markTable)
    names = list(map(lambda x: x[0], c.description))
    return names

#---------TABLE NAME, change it here---------
def KeywordAl_createTable(c):
    C_table = """CREATE TABLE """+ markTable +"""(
        keywords text NOT NULL UNIQUE
    )"""
    #create a table
    c.execute(C_table)

def KeywordAl_cleanTable(c):
    c.execute("DELETE FROM "+ markTable)
    
def KeywordAl_insertRows(c,keyword):
    id = KeywordAl_createID(c,keyword)
    list = [(id,keyword)]
    c.executemany("INSERT INTO "+ markTable +" VALUES(?,?)",list)


def KeywordAl_createID(c):
    KeywordAl_getlen(c) + 1
    return id    

    
    
#print all the data in table
def KeywordAl_printAll(c):
    c.execute("SELECT rowid, * FROM " + markTable)
    items = c.fetchall()    
    for item in items:
        print(str(item))
        
        
def KeywordAl_getlen(c):
    c.execute("SELECT rowid, * FROM " + markTable)
    items = c.fetchall()
    return len(items)  

def KeywordAl_getID(c,keyword):
    id = ""
    c.execute("SELECT * FROM " + markTable)
    items = c.fetchall()    
    for item in items:
        if item[1] == keyword:
            id = item[0]
    return id
        
def KeywordAl_bcheckKeyword(c,keyword):
    c.execute("SELECT keywords FROM "+markTable+" WHERE keywords = ?", (keyword,))
    data=len(c.fetchall())

    if data != 0:
        return True
    else:
        return False  