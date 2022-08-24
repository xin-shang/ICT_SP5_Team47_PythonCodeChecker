
import random
import sqlite3


def DB_CreateText_inputValue(path, input_string):
    with open(path, "w+",newline="") as output:
        output.write(input_string)




#---------TABLE NAME, change it here---------
table = "qs"

def setTable(table_n):
    table = table_n

def create_sqlite(c):
    C_table = """CREATE TABLE """+ table +"""(
        question text,
        solution text,
        markScheme text
    )"""
    #create a table
    c.execute(C_table)

def deletAllFromT(c):
    c.execute("DELETE FROM "+ table)
    
def insert_list(c,question,solution,mark):
    list = [(question,solution,mark)]
    c.executemany("INSERT INTO "+ table +" VALUES(?,?,?)",list)
    
    



#print all the data in table
def printAll(c):
    c.execute("SELECT rowid, * FROM " + table)
    items = c.fetchall()
    
    for item in items:
        print(item)

def readText(path):
    with open(path, 'rb') as file:
        data = file.read().rstrip()
        return data


def return_random_rows(c):
    rows = ""
    c.execute("SELECT rowid, * FROM " + table)
    items = c.fetchall()
    random_r = random.randint(1, len(items))
    for value in items:
        if(value[0] == random_r):
            rows = value
    return rows