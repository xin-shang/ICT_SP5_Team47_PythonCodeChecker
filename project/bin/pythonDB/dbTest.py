from questionDB.PYDb_keywords import *

from questionDB.PYDb_question import *
from questionDB.PYDb_solution import *
from login.PYDb_staff import *

from login.PYDb_Student import*
from questionDB.PYDb_markPoint import *

from PYDb_qnsController import *
from questionDB.PYDb_keywordAlternative import *






if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    
    c.fetchone
    #打印检查
    #markPoint_printAll(c)
    #r = getUserQuestionRows(c,"staff")
    
    question_printAll(c)
    #打印
    conn.commit()
    conn.close()