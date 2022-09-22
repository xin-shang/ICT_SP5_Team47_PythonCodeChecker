from PYTextAnalysis import *


#path for user input
path_1 = r"./src/txt/PyCodeAnswer.txt"
path_2 = r"./src/txt/PyCodeAnswer.py"

#path for user output
path_output = "./src/txt/PyCodeAnswer.txt"

#the function return user output from command, and save in a txt
def runCode(path_user_anwer_Text, path_user_answer_py,path_output):
    #clear the last userinput.py make sure the text can be transferred as python
    TA_Remove_File(path_user_answer_py)
    

    TA_rebuildText2Python(path_user_anwer_Text,path_user_answer_py)
    TA_saveOutPut2Text(path_user_answer_py, path_output)
    result = TA_getUserInput_Result(path_output)
    TA_CreateText_inputValue(path_output, result)
    TA_Remove_File(path_user_answer_py)


if __name__ == '__main__':
    runCode(path_1,path_2,path_output)