from PYDb_QnSFunction import *

if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/qs_T.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    id_string = readText("./src/dbData/dbID.txt").decode(('utf-8'))
    id = int(id_string)
    #get row by id 
    row = getrow(c,id)
    
    rowQuestion = row[1]
    rowSolution = row[2]
    rowAnswer = row[3]
    makrScheme = row[4]
    
    DB_WriteText_inputValue("./src/dbData/dbQuestion.txt",rowQuestion)
    DB_WriteText_inputValue("./src/dbData/dbSolution.txt",rowSolution)
    DB_WriteText_inputValue("./src/dbData/dbAnswer.txt",rowAnswer)
    DB_WriteText_inputValue("./src/dbData/dbScheme.txt",makrScheme)
    
    conn.commit()
    conn.close()