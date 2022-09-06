import sqlite3
from PYDb_qnsController import *


def _readText(path):
    with open(path, 'rb') as file:
        data = file.read().rstrip()
        return data


if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    user_id = _readText("./src/txt/Login_StaffUserName.txt").decode('utf-8')
    question = _readText("./src/dbData/dbQuestion_POST.txt").decode('utf-8')
    solution = _readText("./src/dbData/dbSolution_POST.txt").decode('utf-8')
    answer = _readText("./src/dbData/dbAnswer_POST.txt").decode('utf-8')
    
    addQuestion(c,user_id,question,solution,answer)
    
    
    conn.commit()
    conn.close()