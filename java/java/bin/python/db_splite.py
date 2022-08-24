#! /usr/bin/python
from genericpath import exists
import os

from pickle import TRUE
import random
import sqlite3
import sys
from  Linked_List import *

#---------Database NAME, change it here---------
db_name = 'qs_T.db'
conn = sqlite3.connect(db_name)
#create a cursor
c = conn.cursor()

#---------TABLE NAME, change it here---------
table = "qs"
    


#remove all data from table, be careful to use it
def deletAllFromT():
    c.execute("DELETE FROM "+ table)

#insert value to a table
def insert_list(value_1,value_2,value_3):
    list = [(value_1,value_2,value_3)]
    c.executemany("INSERT INTO "+ table +" VALUES(?,?,?)",list)

#create table
def create_sqlite():

    C_table = """CREATE TABLE """+ table +"""(
        question text,
        solution text,
        markScheme text
    )"""
    #create a table
    c.execute(C_table)

#select table data by rowid in asc
def select_id_by_asc():
    c.execute("SELECT rowid, * FROM " + table)

#print all the data in table
def _loop_db(values):
    for value in values:
        print(value)

#print all the data in table
def printAll():
    select_id_by_asc()
    items = c.fetchall()
    _loop_db(items)


#check the file exit or not
def IsFile_Exit (path):
    check_path = exists(path)
    return check_path

#create Score List
def createScoreList(id):
    scoreScheme = ""
    select_id_by_asc()
    items = c.fetchall()

    #only select the markScheme
    for value in items:
        if(value[0] == id):
            scoreScheme = value[3]

    stringL = scoreScheme.split(',')
    ls_mark = transferToScoreList(stringL)
    return ls_mark

#transfer text to score list
def transferToScoreList(score_list):
    count = 0
    #a value for short memory
    mark_point = NULL
    new_score = NULL

    #create a list to store each lines of data
    mark_list = []
    for character in score_list:
        if(count%2 == 0):
            mark_point = remove_space(character)
        else:
            new_score = remove_space(character)
            signle_mk = (mark_point,new_score)
            mark_list.append(signle_mk)
        count = count + 1  
    return mark_list

def return_rows_id(question):
    id = NULL
    select_id_by_asc()
    items = c.fetchall()

    #only select the markScheme
    for value in items:
        #that will return the last index, if the question has more than 1
        if(value[1] == question):
            id = value[0]
    return id

def return_random_rows():
    rows = NULL
    select_id_by_asc()
    items = c.fetchall()
    random_r = random.randint(1, len(items))
    for value in items:
        if(value[0] == random_r):
            rows = value
    return rows

#check the space front of the string
def remove_space(string):
    if(string[0] == " "):
        return string[1:]
    else:
        return string

def readText(path):
    with open(path, 'rb') as file:
        data = file.read().rstrip()
        return data

def DB_CreateText_inputValue(path, input_string):
    with open(path, "w+",newline="") as output:
        output.write(input_string)
    
def run():
    #create_sqlite()
    
    #question = readText("quz_3.txt").decode('utf-8')
    #solution = readText("solution_3.txt").decode('utf-8')
    #mark =  """for, 50, append, 50"""
    #insert_list(question, solution, mark)
        
    #printAll()
    
    random_r = return_random_rows()
    
    question_text = "./java/src/txt/question_txt.txt"
    
    
    DB_CreateText_inputValue(question_text,random_r[1])
    print(random_r[1])
    #print(return_id)
    #scoreL = createScoreList(2)
    #print(scoreL)
    #deletAllFromT()
    print("done")





if __name__ == '__main__':

    run()

    

    print()
    conn.commit()
    conn.close()