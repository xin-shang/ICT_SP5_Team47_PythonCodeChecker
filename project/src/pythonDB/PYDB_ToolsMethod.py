def DB_readText(path):
    with open(path, 'rb') as file:
        data = file.read().rstrip()
        return data
    
def DB_WriteText_inputValue(path, input_string):
    with open(path, "w+",newline='') as output:
        output.write(input_string)
        
def DB_getStaffUserName():
    return DB_readText("./src/dbData/LOGIN/STAFF/Login_StaffUserName.txt").decode('utf-8')
