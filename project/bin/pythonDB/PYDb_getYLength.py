import sqlite3
from PYDb_qnsController import *
from PYDB_ToolsMethod import *

if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    
    user_id = DB_getStaffUserName()
    rows = getUserQuestionRows(c,user_id)
    length = len(rows)
    print(length)
    
    conn.commit()
    conn.close()