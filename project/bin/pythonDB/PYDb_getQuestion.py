import sqlite3
from PYDb_qnsController import *
from PYDB_ToolsMethod import *


if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    rowIndex = int(DB_readText("./src/dbData/RECEIVE/rowid.txt").decode('utf-8'))
    user_id = DB_getStaffUserName()
    rows = getUserQuestionRows(c,user_id)
    
    rowQuestionID = rows[rowIndex][0]
    rowQuestion = rows[rowIndex][1]
    rowSolution = rows[rowIndex][2] 
    makrAnswer = rows[rowIndex][3]
    
    DB_WriteText_inputValue("./src/dbData/RECEIVE/dbQuestion_ID.txt",rowQuestionID)
    DB_WriteText_inputValue("./src/dbData/RECEIVE/dbQuestion.txt",rowQuestion)
    DB_WriteText_inputValue("./src/dbData/RECEIVE/dbSolution.txt",rowSolution)
    DB_WriteText_inputValue("./src/dbData/RECEIVE/dbAnswer.txt",makrAnswer)
    
    print (rows[rowIndex])
    
    conn.commit()
    conn.close()