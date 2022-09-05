from multiprocessing.resource_tracker import main
import os
from venv import create
from PYDb_QnSFunction import *
from PYTextAnalysis import *




def insertDB_Example(c):
    
    username = "staff"
    question = readText("./src/example_answer/quz_2.txt").decode('utf-8')
    #question_1 = "print basdasdasdaaa"
    
    solution = readText("./src/example_answer/solution_2.txt").decode('utf-8')
    #solution_1 = "printacasdasdascccc"
    
    answer = "100"
    mark = "1,2,3"
    
    insertRows(c,username,question,solution,answer,mark)



if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/db/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()

    #createTable(c)
    #insertDB_Example(c)
    

    conn.commit()
    conn.close()

    






