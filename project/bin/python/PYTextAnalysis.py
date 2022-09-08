import os
import subprocess
from os.path import exists


#the function will return a boolean when the path is exit
def _IsFile_Exit (path):
    check_path = exists(path)
    return check_path

def TA_Remove_File(path):
    file_path = path
    if os.path.isfile(file_path):
        os.remove(file_path)
        print("File: " + path + " has been deleted")
    else:
        print("File: " + path + " does not exist") 


#the function will read the python code and save the output in a text
def TA_saveOutPut2Text(userInput_path, save_path):
    with open(save_path, "w+") as output:
        c_r = subprocess.call(["python",userInput_path],stderr=output,stdout=output)
 

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

    if(_IsFile_Exit(TransferFilePath_a) == True):
        # if path b is not exit, then transfer the document 
        if(_IsFile_Exit(TransferFilePath_b) == False):
            # transfer text file as python
            old_name = TransferFilePath_a
            new_name = TransferFilePath_b
            os.rename(old_name,new_name)
        else:
            print("The File: "+ TransferFilePath_b +" is exit, you cannot covert it")
    else:
        print("The File: "+ TransferFilePath_a +" is not exit, pls double check!!")



#the function will return user input answer
def TA_getUserInput_Result(_text_path_R):
    
    
    # If the answer text is exit, run the below code(Safe)
    if(_IsFile_Exit(_text_path_R) == True):
        #return the first lines of text file
        if(len(TA_readText(_text_path_R))==1):
            _user_result = TA_readText(_text_path_R)[0][:-1]
            TA_CreateText_inputValue("./src/txt/sytaxError_b.txt","False")
            return _user_result
        elif(len(TA_readText(_text_path_R)) > 1):
            select_index = len(TA_readText(_text_path_R)) - 1
            _user_result = TA_readText(_text_path_R)[select_index][:-1]
            TA_CreateText_inputValue("./src/txt/sytaxError_b.txt","True")
            return _user_result
        else:
            return "the output is empty"
    else:
         return "The " + _text_path_R + " is not exit"
