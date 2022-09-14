import sqlite3
from questionDB.PYDb_keywordAlternative import *
from PYDB_ToolsMethod import *

if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    
    #create a cursor
    c = conn.cursor()
   
    length = KeywordAl_getlen(c)
    
    print(length)

    conn.commit()
    conn.close()