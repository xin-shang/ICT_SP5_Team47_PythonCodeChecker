import sqlite3
from login.PYDb_Student import *
from PYDB_ToolsMethod import *


if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)

    #create a cursor
    c = conn.cursor()

    user_id = DB_readText("./src/dbData/LOGIN/STUDENT/StudentUserName.txt").decode('utf-8')
    password = DB_readText("./src/dbData/LOGIN/STUDENT/StudentUserPassword.txt").decode('utf-8')
    student_insertRows(c,user_id,password)
    
    conn.commit()
    conn.close()