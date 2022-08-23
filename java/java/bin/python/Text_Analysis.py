from asyncio.windows_events import NULL
import io
import sys
import os
from io import StringIO
import subprocess
from os.path import exists, isfile
from tkinter import E
import csv
import re



QUESTION_PATH = 'E:\GitHub\ICT_SP5_Team47_PythonCodeChecker\java\java\src\python\question_input.csv'


#the function will return a boolean when the path is exit
def _IsFile_Exit (path):
    check_path = exists(path) and isfile(path)
    return check_path

def TA_Remove_File(path):
    file_path = path
    if _IsFile_Exit(file_path):
        os.remove(file_path)
        print("File: " + path + " has been deleted")
    else:
        print("File: " + path + " does not exist") 


#the function will read the python code and save the output in a text
def TA_saveOutPut2Text(userInput_path, save_path):
    with open(save_path, "w+") as output:
        subprocess.call(["python",userInput_path],stderr=output,stdout=output)
 

def TA_CreateText_inputValue(path, input_string):
    with open(path, "w+") as output:
        output.write(input_string) 


#the function will return the content from text
def TA_readText(Text_path):
    with open(Text_path) as f:
        lines = f.readlines()
    return lines

#the function will transfer the txt to py
def TA_rebuildText2Python(TransferFilePath_a, TransferFilePath_b):

    if _IsFile_Exit(TransferFilePath_a):
        # if path b is not exit, then transfer the document 
        if not _IsFile_Exit(TransferFilePath_b):
           
            os.rename(TransferFilePath_a, TransferFilePath_b)
        else:
            print("The File: "+ TransferFilePath_b +" is exit, you cannot covert it")
    else:
        print("The File: "+ TransferFilePath_a +" is not exit, pls double check!!")


#the function will return user input answer
def TA_getUserInput_Result(_text_path_R):
    # If the answer text is exit, run the below code(Safe)
    if _IsFile_Exit(_text_path_R):
        #return the first lines of text file
        if len(TA_readText(_text_path_R)) >= 1:
            _user_result = TA_readText(_text_path_R)[-1].strip('\n')
            return _user_result
        else:
            return "the output is empty"
    else:
         return "The " + _text_path_R + " is not exit"


def TA_getQuestion_Content(QUESTION_PATH, question_content):
    with open(QUESTION_PATH) as f:
        content = csv.DictReader(f)
        for question in content:
            if question['Title'] == question_content:
                return question
    
    print('Question Not Found')

def TA_Text2csv(text, question_path):
    try:
        Title = re.findall(r'Title:(.*?);Keyword_Check', text)[0]
        Keyword_Check = re.findall(r'Keyword_Check:(.*?);KW_Score', text)[0]
        KW_Score = re.findall(r'KW_Score:(.*?);Result_Check', text)[0]
        Result_Check = re.findall(r'Result_Check:(.*?);Result_Score', text)[0]
        Result_Score = re.findall(r'Result_Score:(.*?);Variable_Value_Check', text)[0]
        Variable_Value_Check = re.findall(r'Variable_Value_Check:(.*?);Variable_Value_Score', text)[0]
        Variable_Value_Score = re.findall(r'Variable_Value_Score:(.*?);Function_Check', text)[0]
        Function_Check = re.findall(r'Function_Check:(.*?);Function_Score', text)[0]
        Function_Score = re.findall(r'Function_Score:(.*?)$', text)[0]

        with open(question_path, 'r') as f1:
            content = csv.DictReader(f1)
            for item in content:
                if item['Title'] == Title:
                    print('Question Exist')
                    return
        
        with open('question_input.csv', 'a') as f2:
            f2.write(f'{Title},{Keyword_Check},{KW_Score},{Result_Check},{Result_Score},{Variable_Value_Check},{Variable_Value_Score},{Function_Check},{Function_Score}\n')
        
        print('Write success...')
    
    except:
        print('Write fail, check your input format')



if __name__ == '__main__':

    question = """Title:Define the function F(n) to calculate the sum of the first n digits of the Fibonacci sequence Calculate the sum of the first 10 digits And store the returned result in res and print it out;Keyword_Check:for if def return;KW_Score:20 10 10 10;Result_Check:143;Result_Score:20;Variable_Value_Check:res:143;Variable_Value_Score:10;Function_Check:F(10):143;Function_Score:20"""
    question_path = 'E:\GitHub\ICT_SP5_Team47_PythonCodeChecker\java\java\src\python\question_input.csv'
    with open(question_path, 'r') as f:
        content = f.read()
        print(content)
    TA_Text2csv(question, question_path)

    with open(question_path, 'r') as f:
        content = f.read()
        print(content)
    TA_Text2csv(question, question_path)
    with open(question_path, 'r') as f:
        content = f.read()
        print(content)
    