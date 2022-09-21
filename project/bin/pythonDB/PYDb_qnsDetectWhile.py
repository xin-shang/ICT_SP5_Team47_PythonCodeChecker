from ast import While
import string

def TA_readText(Text_path):
    with open(Text_path) as f:
        lines = f.readlines()
    return lines

def checkFinalImportRow(stringList):
    countImport = 0
    row = 0
    countImport_2 = 0
    
    for line in stringList:
        if "import" in line:
            countImport = countImport + 1

    if  countImport != 0:
        for line in stringList:
            if "import" in line:
                countImport_2 = countImport_2 + 1
            if countImport_2 == countImport:
                break
            row = row + 1
    return row

def DB_WriteText_inputValue(path, input_string):
    with open(path, "w+",newline='') as output:
        for line in input_string:
            output.write(line)


def writeText():
    tabe = "    "
    method = "def WriteText(path,input_string):" + "\n" + tabe + '''with open(path, "w+",newline='') as output:''' + "\n" + tabe + tabe + "for line in input_string:" + "\n" +  tabe + tabe + tabe + "output.write(line)"
    return method


def reWriteString(stringCode_List):
    list_a = []
    row = checkFinalImportRow(stringCode_List)
    countLine = 0
    for line in stringCode_List:
        
        if countLine == row:
            if row == 0:
                line = "import time" + "\n" + "start_time = time.time()" + "\n" + writeText() + "\n" + line 
            else:
                line = line + "\n" + "import time" + "\n" + "start_time = time.time()" + "\n" + writeText()
        elif "while" in line or "for" in line :
            index = line.find("while")
            count = 0
            for i in range(0,index):
                if string[i] == " ":
                    count = count + 1
                    
            space = ""
            space_2 = ""
            
            for i in range(0,count+4):
                space = space + " " 
            for i in range(0,4):
                space_2 = space_2 + " "

            line =  line + "\n" + space + "if time.time() - start_time > 1:" + "\n" + space_2 + space + "WriteText('./src/txt/loopError.txt','True')" +"\n" + space_2 + space + "break" + "\n"

        countLine = countLine + 1
        list_a.append(line)
    
    return list_a


if __name__ == '__main__':
    string_1 = TA_readText("./src/txt/PyCodeAnswer.txt")
    string_rebuild = reWriteString(string_1)
    

    DB_WriteText_inputValue("./src/txt/PyCodeAnswer.txt",string_rebuild)






