from PYDb_QnSFunction import *

if __name__ == '__main__':
    
    #---------Database NAME, change it here---------
    
    db_pth = './src/db/PYCodeChecker.db'
    #db_pth = './src/sqlite/qs_T.db'
    conn = sqlite3.connect(db_pth)
    #create a cursor
    c = conn.cursor()
    
    l=getRow(c,1)
    rowLength = len(l)
    print(rowLength)

    conn.commit()
    conn.close()