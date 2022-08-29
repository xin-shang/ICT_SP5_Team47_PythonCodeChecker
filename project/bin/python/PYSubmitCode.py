import sqlite3
from PYTextAnalysis import *
from PYDb_QnSFunction import *




def countSocre_inOperator(userinput, ScoreList):
    Total_Score = 0
    #方法一：Using Python's "in" operator
    for score in ScoreList:
        if(score[0] in userinput):
            Total_Score = Total_Score + int(score[1])
    return Total_Score


if __name__ == '__main__':
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/qs_T.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    scoreList = createScoreList(c,1)
    pycodeAnswer = "./src/txt/PyCodeAnswer.txt"
    user_answer = readText(pycodeAnswer).decode('utf-8')
    
    final_score = countSocre_inOperator(pycodeAnswer,scoreList)
    print(final_score)
    
    
    
    conn.commit()
    conn.close()
