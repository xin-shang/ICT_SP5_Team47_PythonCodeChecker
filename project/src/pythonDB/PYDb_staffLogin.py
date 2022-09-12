from genericpath import exists
import sqlite3
from login.PYDb_staff import *



def IsFile_Exit (path):
    check_path = exists(path)
    return check_path


if __name__ == '__main__':
    
    studentLoginPath = "./src/dbData/LOGIN/STAFF/"
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    
    #insertDB_Example()
    bIsStudent_userName = IsFile_Exit(studentLoginPath + "StaffUserName.txt")
    bIsStudent_password = IsFile_Exit(studentLoginPath + "StaffPassword.txt")
    
    if bIsStudent_userName == True and bIsStudent_password == True:
        student_userName = staff_readText(studentLoginPath + "StaffUserName.txt").decode('utf-8')
        student_password = staff_readText(studentLoginPath + "StaffPassword.txt").decode('utf-8')
        staff_CreateText_inputValue(studentLoginPath + "Login_StaffUserName.txt",student_userName)
        
        #check userName and password exit
        bUsername = staff_bCheckUserName(c,student_userName)
        bPassword = staff_bCheckPassword(c,student_userName,student_password)
               
        staff_CreateText_inputValue(studentLoginPath + "StaffUserName.txt",str(bUsername))
        staff_CreateText_inputValue(studentLoginPath + "StaffPassword.txt",str(bPassword))
        
        print (bUsername)
        print (bPassword)
        
    else:
        print("passwordTxT or usernameTxT is not exit, check in java!!!!")

    conn.commit()
    conn.close()
