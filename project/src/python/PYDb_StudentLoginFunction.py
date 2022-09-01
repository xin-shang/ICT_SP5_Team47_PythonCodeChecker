import random
import sqlite3

table = "login_student"

#---------TABLE NAME, change it here---------
def setTable(table_n):
    table = table_n

def DB_CreateText_inputValue(path, input_string):
    with open(path, "w+",newline="") as output:
        output.write(input_string)

def dropTable(c):
    c.execute("DROP TABLE "+ table)

#---------TABLE NAME, change it here---------
def create_sqlite(c):
    C_table = """CREATE TABLE """+ table +"""(
        userName text,
        userPassword text,
        EmailAddress text
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



def bCheckUserName(c,userName):
    c.execute("SELECT rowid, * FROM " + table)
    items = c.fetchall()
    count = 0
    for item in items:
        if item[1] == userName:
           count = count + 1
           
    if count >= 1:
        return True
    else:
        return False


def bCheckPassword(c,username,password):
    c.execute("SELECT rowid, * FROM " + table)
    items = c.fetchall()
    count = 0
    for item in items:
        if item[1] == username and item[2] == password:
            count = count + 1
    if count >= 1:
        return True
    else:
        return False



    
    
