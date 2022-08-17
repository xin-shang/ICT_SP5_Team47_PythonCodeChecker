import os
from Score import *
from Text_Analysis import *
from Feedback import *


#example question&solution
Question1 =  "Print a Cat"
Question1_solution = "Cat"

#path for user input
path_1 = r"./java/src/txt/PyCodeAnswer.txt"
path_2 = r"./java/src/txt/PyCodeAnswer.py"


#path for user output
path_output = "./java/src/txt/user_output.txt"


#the function return user output from command, and save in a txt
def TextAnalysis(path_user_anwer_Text, path_user_answer_py,path_output):
    #clear the last userinput.py make sure the text can be transferred as python
    TA_Remove_File(path_user_answer_py)

    TA_rebuildText2Python(path_user_anwer_Text,path_user_answer_py)
    TA_saveOutPut2Text(path_user_answer_py, path_output)
    result = TA_getUserInput_Result(path_output)
    TA_CreateText_inputValue(path_output, result)

    print("")

def GetFeedBack():
    print("")

def GetScore():
    print("")


def run():
    TextAnalysis(path_1,path_2,path_output)
   

run()