import sqlite3
from PYDb_qnsController import *

from PYDB_ToolsMethod import *


if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    user_id = DB_readText("./src/dbData/LOGIN/STAFF/Login_StaffUserName.txt").decode('utf-8')
    question = DB_readText("./src/dbData/POST/dbQuestion_POST.txt").decode('utf-8')
    solution = DB_readText("./src/dbData/POST/dbSolution_POST.txt").decode('utf-8')
    answer = DB_readText("./src/txt/dbAnswer_POST.txt").decode('utf-8')
    
    rows = addQuestion(c,user_id,question,solution,answer)
    print(rows)
    
    conn.commit()
    conn.close()