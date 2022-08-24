import os
from db_S import *



#---------Database NAME, change it here---------
db_pth = './java/src/dbSqlite/qs_T.db'
conn = sqlite3.connect(db_pth)
#create a cursor
c = conn.cursor()


#create_sqlite(c)
#deletAllFromT(c)

row_q = return_random_rows(c)[1]

#printAll(c)
print(row_q)

DB_CreateText_inputValue("./java/src/txt/question_text.txt",row_q)
conn.commit()
conn.close()