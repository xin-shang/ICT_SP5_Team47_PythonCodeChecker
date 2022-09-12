from genericpath import exists
from webbrowser import get
from PYDb_StudentLoginFunction import *
from PYTextAnalysis import *

def IsFile_Exit (path):
    check_path = exists(path)
    return check_path

def insertDB_Example():
    username = "student"
    password = "student"
    emailAdress = "student@unisa.com"
    
    insert_list(c,username,password,emailAdress)




if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/qs_T.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    
    #insertDB_Example()
    bIsStudent_userName = IsFile_Exit("./src/txt/StudentUserName.txt")
    bIsStudent_password = IsFile_Exit("./src/txt/StudentUserPassword.txt")
    
    if bIsStudent_userName == True and bIsStudent_password == True:
        student_userName = readText("./src/txt/StudentUserName.txt").decode('utf-8')
        student_password = readText("./src/txt/StudentUserPassword.txt").decode('utf-8')
       
        #check userName and password exit
        bUsername = bCheckUserName(c,student_userName)
        bPassword = bCheckPassword(c,student_userName,student_password)
               
        TA_CreateText_inputValue("./src/txt/StudentUserName.txt",str(bUsername))
        TA_CreateText_inputValue("./src/txt/StudentUserPassword.txt",str(bPassword))
        
        print (bUsername)
        print (bPassword)
        
    else:
        print("passwordTxT or usernameTxT is not exit, check in java!!!!")

    conn.commit()
    conn.close()
