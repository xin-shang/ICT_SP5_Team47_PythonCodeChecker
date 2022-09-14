 
import sqlite3
from questionDB.PYDb_keywordAlternative import *
from PYDB_ToolsMethod import *

if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    
    #create a cursor
    c = conn.cursor()
    
   
    row_id = int(DB_readText("./src/dbData/keywordAlternative/rowid.txt").decode('utf-8'))
    keyword = KeywordAl_getKeyword(c,row_id)

    DB_WriteText_inputValue("./src/dbData/keywordAlternative/keywords.txt",keyword)
    conn.commit()
    conn.close()
 
