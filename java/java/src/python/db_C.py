from multiprocessing.resource_tracker import main
import os
from db_S import *
from Text_Analysis import *

def manageDb():
    #create_sqlite(c)
    deletAllFromT(c)

def returnDBQuestion():
    random_r = return_random_rows(c)
    row_q = random_r[1]
    right_solution = random_r[2]

    print(row_q)
    DB_CreateText_inputValue("./java/src/txt/rightsolution_text.txt",right_solution)
    DB_CreateText_inputValue("./java/src/txt/question_text.txt",row_q)

def returnDBAnswer():
    right_soultion_txt = r"./java/src/txt/rightsolution_text.txt"
    right_soultion_py = r"./java/src/txt/rightsolution_python.py"
    path_output = "./java/src/txt/rightsolution_output.txt"
    TA_Remove_File(right_soultion_py)
    TA_rebuildText2Python(right_soultion_txt,right_soultion_py)
    TA_saveOutPut2Text(right_soultion_py, path_output)
    result = TA_getUserInput_Result(path_output)
    TA_CreateText_inputValue(path_output, result)
    print(result)


def insertDB_Example():
    question = readText("./java/src/dbSqlite/quz_2.txt").decode('utf-8')
    solution = readText("./java/src/dbSqlite/solution_2.txt").decode('utf-8')
    mark = "<= 100, 100"
    insert_list(c,question,solution,mark)

#---------Database NAME, change it here---------
db_pth = './java/src/dbSqlite/qs_T.db'
conn = sqlite3.connect(db_pth)
#create a cursor
c = conn.cursor()

#insertDB_Example()
#printAll(c)
#manageDb()
returnDBQuestion()


conn.commit()
conn.close()

returnDBAnswer()






