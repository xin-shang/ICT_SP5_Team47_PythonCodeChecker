import os

from Text_Analysis import *

_TotalScore = 0


#compare the answer
def _S_CompareAnswer(answer_1, answer_2):
    answer_1 == answer_2

def S_getTotalScore(userInput, Solution):
    score = 0
    score += (KeyWordCheck(question, userInput) + FunctionCheck(question, userInput) + ResultCheck(question, userInput) + VariableCheck(question, userInput))
    return score

# whether keyword exists
def KeyWordCheck(question, userInput):
    Keywordlist = question['keyword_Check'].split()
    KWscore = question['KW_Score'].split()
    userInputlist = userInput.replace('\n', ' ').split()
    score = 0
    for keyword, kws in zip(Keywordlist, KWscore):
        if keyword in userInputlist:
            score += float(kws)
    
    return score


def FunctionCheck(question, userInput):
    Functionlist = [f'\nprint({func.split(":")[0]})' for func in question['Function_Check'].split()]
    answerlist = [func.split(":")[1] for func in question['Function_Check'].split()]
    FScore = question['Function_Score'].split(',')
    score = 0
    for pfunc, ans, fss in zip(Functionlist, answerlist, FScore):
        with open('tmp.py', 'w') as f:
            f.write(userInput)
            f.writelines(pfunc)
        res = subprocess.check_output(['python', 'tmp.py']).decode('utf-8').split()
        if _S_CompareAnswer(res[-1], ans):
            score += float(fss)

    os.remove('tmp.py')
    return score


def ResultCheck(question. userInput):
    Resultlist = [f'\nprint({result.split(":")[0]})' for result in question['Result_Check'].split()]
    answerlist = [result.split(":")[1] for result in question['Result_Check'].split()]
    RScore = question['Result_Score'].split()
    score = 0
    for pres, ans, rss in zip(Resultlist, answerlist, RScore):
        with open('tmp.py', 'w') as f:
            f.write(userInput)
            f.writelines(pres)
        res = subprocess.check_output(['python', 'tmp.py']).decode('utf-8').split()
        if _S_CompareAnswer(res[-1], ans):
            score += float(rss)
    os.remove('tmp.py')
    return score


def VariableCheck(question, userInput):
    Variablelist = [f'\nprint({variable.split(":")[0]})' for variable in question['Variable_Value_Check'].split()]
    valuelist = [variable.split(":")[1] for variable in question['Variable_Value_Check'].split()]
    VScore = question['Variable_Value_Score'].split()
    score = 0
    for pval, val, vss in zip(Variablelist, valuelist, VScore):
        with open('tmp.py', 'w') as f:
            f.write(userInput)
            f.writelines(pval)
        res = subprocess.check_output(['python', 'tmp.py']).decode('utf-8').split()
        if _S_CompareAnswer(res[-1], val):
            score += float(vss)
    os.remove('tmp.py')
    return score



