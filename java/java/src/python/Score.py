from asyncio.windows_events import NULL
import os

_TotalScore = 0


#compare the answer
def _S_CompareAnswer(answer_1, answer_2):
    if answer_1 == answer_2:
        return True
    else:
        return False


def S_countFunction():
    print("")

def S_countValuable():
    print("")


def S_getTotalScore(userInput, Solution):
    bIfcorrect = _S_CompareAnswer(userInput, Solution)
    if(bIfcorrect == True):
        _TotalScore = 100
        return _TotalScore
    else:
        _TotalScore = 0
        return _TotalScore


