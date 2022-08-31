from multiprocessing.resource_tracker import main
import os
from PYDb_QnSFunction import *
from PYTextAnalysis import *



def manageDb():
    #create_sqlite(c)
    deletAllFromT(c)

def returnDBQuestion(c):
    #retrurn a random rows from db
    random_r = return_random_rows(c)
    
    #return default
    return_r = getrow(c,1)
    
    #return the first element which is question from the row
    row_q = return_r[1]
    question_path = "./src/txt/PyCodeQuestion.txt"
    DB_WriteText_inputValue(question_path,row_q)
    
    #return the second element which is solution from the row
    right_solution = return_r[2]
    solution_path = "./src/txt/PyCodeRightAnswer.txt"
    DB_WriteText_inputValue(solution_path,right_solution)
    
    #test question
    #print(row_q)
    
    
def returnDBAnswer():
    right_soultion_txt = r"./src/txt/PyCodeRightAnswer.txt"
    right_soultion_py = r"./src/txt/PyCodeRightAnswer.py"
    path_output = "./src/txt/PyCodeRightAnswer.txt"
    
    #clear pyAnswer answer if exit, make sure the py document is not exit as the beginning
    TA_Remove_File(right_soultion_py)
    #change db question txt to python
    TA_rebuildText2Python(right_soultion_txt,right_soultion_py)
    #run db solution and save as txt 
    TA_saveOutPut2Text(right_soultion_py, path_output)
    #remove the useless message from power shell and get the result save as txt 
    result = TA_getUserInput_Result(path_output)
    TA_CreateText_inputValue(path_output, result)
    
    #after reading the solution and write in txt, then remove the python document
    TA_Remove_File(right_soultion_py)
    
    #test result
    #print(result)


def insertDB_Example(c):
    question = readText("./src/example_answer/quz_2.txt").decode('utf-8')
    question_1 = "print basdasdasdaaa"
    
    solution = readText("./src/example_answer/solution_2.txt").decode('utf-8')
    solution_1 = "printacasdasdascccc"
    
    answer = "a"
    mark = "<= 100, 50, while, 50"
    
    insert_list(c,question_1,solution_1,answer,mark)



if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/qs_T.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()

    returnDBQuestion(c)
    returnDBAnswer()

    conn.commit()
    conn.close()

    






