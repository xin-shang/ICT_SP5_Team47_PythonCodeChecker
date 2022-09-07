from questionDB.PYDb_keywords import *

from questionDB.PYDb_question import *
from questionDB.PYDb_solution import *
from login.PYDb_staff import *

from login.PYDb_Student import*
from questionDB.PYDb_markPoint import *

from PYDb_qnsController import *





def markpoint(c):
    qs_id = "pt1_094709"
    ks_id = "pt1"
    score = 100
    
    

def addQuestion_test(c):
    username = "staff"
    question = "print dog"
    solution = "print('dog')"
    answer = "dog"
    
    #addQuestion(c,username,question,solution,answer)
    #addQuestionMarkSheme(c,question,"print",100)
    
    deleteQuestion(c,question)


if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    
    markPoint_printAll(c)
    conn.commit()
    conn.close()