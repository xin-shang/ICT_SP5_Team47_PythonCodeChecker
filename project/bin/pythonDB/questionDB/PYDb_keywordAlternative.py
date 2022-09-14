#!/usr/bin/env python3
import sqlite3

markTableAL = "keywordAlternative"

#---------TABLE NAME, change it here---------
def KeywordAl_dropTable(c):
    c.execute("DROP TABLE "+ markTableAL)

def KeywordAl_getHeader(c):
    c.execute('select * from '+ markTableAL)
    names = list(map(lambda x: x[0], c.description))
    return names

#---------TABLE NAME, change it here---------
def KeywordAl_createTable(c):
    C_table = """CREATE TABLE """+ markTableAL +"""(
        keywords text NOT NULL UNIQUE
    )"""
    #create a table
    c.execute(C_table)

def KeywordAl_cleanTable(c):
    c.execute("DELETE FROM "+ markTableAL)
    
def KeywordAl_insertRows(c,keyword):
    c.execute("INSERT INTO "+ markTableAL +" VALUES(?)",(keyword,))


    
    
#print all the data in table
def KeywordAl_printAll(c):
    c.execute("SELECT rowid, * FROM " + markTableAL)
    items = c.fetchall()    
    for item in items:
        print(str(item))
        
        
def KeywordAl_getlen(c):
    c.execute("SELECT rowid, * FROM " + markTableAL)
    items = c.fetchall()
    return len(items)  

def KeywordAl_getID(c,keyword):
    id = ""
    c.execute("SELECT * FROM " + markTableAL)
    items = c.fetchall()    
    for item in items:
        if item[1] == keyword:
            id = item[0]
    return id


def KeywordAl_getKeyword(c,id):
    keyword = ""
    c.execute("SELECT rowid, * FROM " + markTableAL)
    items = c.fetchall()    
    for item in items:
        if item[0] == id:
            keyword = item[1]
    return keyword

        
def KeywordAl_bcheckKeyword(c,keyword):
    c.execute("SELECT keywords FROM "+markTableAL+" WHERE keywords = ?", (keyword,))
    data=len(c.fetchall())

    if data != 0:
        return True
    else:
        return False  