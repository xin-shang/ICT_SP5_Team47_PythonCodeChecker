from PYDb_QnSFunction import *

if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    #db_pth = './src/sqlite/qs_T.db'
    db_pth = './src/db/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    id_string = readText("./src/dbData/dbID.txt").decode(('utf-8'))
    id = int(id_string)
    
    id = 1
    #get row by id 
    row = getRow(c,1)
    
    
    
    #values to txt
    userName = row[1]
    rowQuestion = row[2]
    rowSolution = row[3]
    rowAnswer = row[4]
    makrScheme = row[5]
    
    DB_WriteText_inputValue("./src/dbData/dbUserName.txt",rowQuestion)
    DB_WriteText_inputValue("./src/dbData/dbQuestion.txt",rowQuestion)
    DB_WriteText_inputValue("./src/dbData/dbSolution.txt",rowSolution)
    DB_WriteText_inputValue("./src/dbData/dbAnswer.txt",rowAnswer)
    DB_WriteText_inputValue("./src/dbData/dbScheme.txt",makrScheme)
    
    conn.commit()
    conn.close()