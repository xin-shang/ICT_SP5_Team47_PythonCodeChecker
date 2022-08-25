import os
from db_S import *
from Text_Analysis import *



#---------Database NAME, change it here---------
db_pth = './java/src/dbSqlite/qs_T.db'
conn = sqlite3.connect(db_pth)
#create a cursor
c = conn.cursor()

#create_sqlite(c)
#deletAllFromT(c)

random_r = return_random_rows(c)
row_q = random_r[1]
right_solution = random_r[2]

#printAll(c)
print(row_q)
DB_CreateText_inputValue("./java/src/txt/rightsolution_text.txt",right_solution)
DB_CreateText_inputValue("./java/src/txt/question_text.txt",row_q)

conn.commit()
conn.close()

right_soultion_txt = r"./java/src/txt/rightsolution_text.txt"
right_soultion_py = r"./java/src/txt/rightsolution_python.py"
path_output = "./java/src/txt/rightsolution_output.txt"

TA_Remove_File(right_soultion_py)
TA_rebuildText2Python(right_soultion_txt,right_soultion_py)

TA_saveOutPut2Text(right_soultion_py, path_output)

result = TA_getUserInput_Result(path_output)

print(result)
