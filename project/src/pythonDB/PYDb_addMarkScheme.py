import sqlite3
from PYDb_qnsController import *

from PYDB_ToolsMethod import *


if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()

    question = DB_readText("./src/dbData/POST/dbQuestion_POST.txt").decode('utf-8')
    
    addQuestionMarkSheme(c,question)
    
    conn.commit()
    conn.close()