import sqlite3
from PYTextAnalysis import *



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
    #-----------------数据库根据默认值：1，返回评分列表(default rows id: 1)return markscheme list----------------
    scoreList = getMarkSchemeList(c,1)
    
    #user input
    user_answer = readText("./src/txt/PyCodeAnswer.txt").decode('utf-8')
    #count the socre before run the result
    final_score = countSocre_inOperator(user_answer,scoreList)
    
    #-------------------run result--------------------
    #run result(运行学生输入代码，并返回结果记录在PyCodeAnswer.txt上)
    runCode(path_1,path_2,path_output)
    
    executed_answer = readText("./src/txt/PyCodeAnswer.txt").decode('utf-8')
    #--------------------- 数据库根据id默认值：1 返回答案(default value:1) return db answer--------------
    right_answer = getAnswer(c,1)
    
    if executed_answer == right_answer and final_score == 100:
        DB_WriteText_inputValue("./src/txt/PyCodeScore.txt",str(final_score))
    elif executed_answer == right_answer and final_score != 100:
        DB_WriteText_inputValue("./src/txt/PyCodeScore.txt",str(0))
    else:
        DB_WriteText_inputValue("./src/txt/PyCodeScore.txt",str(final_score))
    
    
    conn.commit()
    conn.close()
