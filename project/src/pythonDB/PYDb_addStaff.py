import sqlite3
from login.PYDb_staff import *
from PYDB_ToolsMethod import *


if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    db_pth = './src/sqlite/PYCodeChecker.db'
    conn = sqlite3.connect(db_pth)

    #create a cursor
    c = conn.cursor()

    user_id = DB_readText("./src/dbData/LOGIN/STAFF/StaffUserName.txt").decode('utf-8')
    password = DB_readText("./src/dbData/LOGIN/STAFF/StaffPassword.txt").decode('utf-8')
    rows = staff_insertRows(c,user_id,password)
    print(rows)
    
    
    conn.commit()
    conn.close()