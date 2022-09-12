from genericpath import exists
import sqlite3
from login.PYDb_Student import *



def IsFile_Exit (path):
    check_path = exists(path)
    return check_path


if __name__ == '__main__':
    
    #point to studentlogin folder
    studentLoginPath = "./src/dbData/LOGIN/STUDENT/"
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    
    bIsStudent_userName = IsFile_Exit(studentLoginPath + "StudentUserName.txt")
    bIsStudent_password = IsFile_Exit(studentLoginPath + "StudentUserPassword.txt")
    
    if bIsStudent_userName == True and bIsStudent_password == True:
        student_userName = student_readText(studentLoginPath + "StudentUserName.txt").decode('utf-8')
        student_password = student_readText(studentLoginPath + "StudentUserPassword.txt").decode('utf-8')
        student_CreateText_inputValue(studentLoginPath + "Login_StudentUserName.txt",student_userName)
        
        #check userName and password exit
        bUsername = student_bCheckUserName(c,student_userName)
        bPassword = student_bCheckPassword(c,student_userName,student_password)
               
        student_CreateText_inputValue(studentLoginPath + "StudentUserName.txt",str(bUsername))
        student_CreateText_inputValue(studentLoginPath + "StudentUserPassword.txt",str(bPassword))
        
        print (bUsername)
        print (bPassword)
        
    else:
        print("passwordTxT or usernameTxT is not exit, check in java!!!!")

    conn.commit()
    conn.close()
