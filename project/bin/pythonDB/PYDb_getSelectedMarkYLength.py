import sqlite3
from PYDb_qnsController import *
from PYDB_ToolsMethod import *

if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    
    #create a cursor
    c = conn.cursor()
    
    question_id = DB_readText("./src/dbData/POST/questionID_POST.txt").decode('utf-8')
    markRows = getQestionMarkScheme(c,question_id)
    
    length = len(markRows)
    
    print(length)

    conn.commit()
    conn.close()