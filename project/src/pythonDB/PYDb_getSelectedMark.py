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
    markSchemeRowIndex = int(DB_readText("./src/dbData/POST/markPoint/SeletcedQuestionMarkScheme_rowid.txt").decode('utf-8'))
    
    keyword_id = markRows[markSchemeRowIndex][0]
    keyword = markRows[markSchemeRowIndex][1]
    score = markRows[markSchemeRowIndex][2]
    
    DB_WriteText_inputValue("./src/dbData/RECEIVE/markPoint/keyword_id.txt",keyword_id)
    DB_WriteText_inputValue("./src/dbData/RECEIVE/markPoint/keyword.txt",keyword)
    DB_WriteText_inputValue("./src/dbData/RECEIVE/markPoint/score.txt",str(score))
    
    print(markRows)

    conn.commit()
    conn.close()