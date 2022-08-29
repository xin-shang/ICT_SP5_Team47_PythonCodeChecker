import sqlite3
from PYTextAnalysis import *
from PYDb_QnSFunction import *


def countSocre_inOperator(userinput, ScoreList):
    Total_Score = 0
    #方法一(method 1)：Using Python's "in" operator
    for score in ScoreList:
        if(score[0] in userinput):
            Total_Score = Total_Score + int(score[1])
    return Total_Score



#the function return user output from command, and save in a txt
def runCode(path_user_anwer_Text, path_user_answer_py,path_output):

    #start to execute result
    TA_rebuildText2Python(path_user_anwer_Text,path_user_answer_py)
    TA_saveOutPut2Text(path_user_answer_py, path_output)
    result = TA_getUserInput_Result(path_output)
    TA_CreateText_inputValue(path_output, result)
    TA_Remove_File(path_user_answer_py)
    
if __name__ == '__main__':
    
    #path for user input
    path_1 = r"./src/txt/PyCodeAnswer.txt"
    path_2 = r"./src/txt/PyCodeAnswer.py"

    #path for user output
    path_output = "./src/txt/PyCodeAnswer.txt"
    
    
    db_pth = './src/sqlite/qs_T.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    
    #-------------------count score--------------------
    #score list
    scoreList = createScoreList(c,1)
    #user input
    user_answer = readText("./src/txt/PyCodeAnswer.txt").decode('utf-8')
    #count the socre before run the result
    final_score = countSocre_inOperator(user_answer,scoreList)
    
    
    
    #-------------------run result--------------------
    #run result
    runCode(path_1,path_2,path_output)
    
    
    executed_answer = readText("./src/txt/PyCodeAnswer.txt").decode('utf-8')
    right_answer = readText("./src/txt/PyCodeRightAnswer.txt").decode('utf-8')
    
    if executed_answer == right_answer and final_score == 100:
        DB_CreateText_inputValue("./src/txt/PyCodeScore.txt",str(final_score))
    elif executed_answer == right_answer and final_score != 100:
        DB_CreateText_inputValue("./src/txt/PyCodeScore.txt",str(0))
    elif executed_answer != right_answer:
        DB_CreateText_inputValue("./src/txt/PyCodeScore.txt",str(final_score))
    else:
        DB_CreateText_inputValue("./src/txt/PyCodeScore.txt",str(0))
    
    
    conn.commit()
    conn.close()
