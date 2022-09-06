#!/usr/bin/env python3
from questionDB.PYDb_question import *
from questionDB.PYDb_solution import *

from questionDB.PYDb_keywords import *
from login.PYDb_staff import *
from questionDB.PYDb_markPoint import *


#根据userID 来返回值
def getUserQuestionRows(c,userID):
    userID = str(userID)
    c.execute('''SELECT 
                    question.id,
                    question.question,
                    solution.solution,
                    solution.answer 
                FROM question INNER JOIN solution ON question.id = solution.question_id
                WHERE question.user_id = ?''',(userID,))
    result = c.fetchall()
    return result

#根据questionID 返回每道题的评分标准
def getQestionMarkScheme(c,questionID):
    questionID = str(questionID)
    c.execute('''SELECT 
                    keywords.id,
                    keywords.keywords,
                    markPoint.score
                FROM markPoint 
                INNER JOIN question ON markPoint.question_id = question.id
                LEFT JOIN keywords ON markPoint.keyword_id = keywords.id
                WHERE question.id = ?''',(questionID,))
    result = c.fetchall()
    return result

#添加问题
def addQuestion(c,user_id,question,solution,answer):
    
    buser_id = staff_bcheckUserID(c,user_id)
    if buser_id == True:
        question_insertRows(c,user_id,question)
        question_ID = question_getID(c,question)
        solution_insertRows(c,question_ID,solution,answer)
        return question

#添加问题-----评分标准
def addQuestionMarkSheme(c,question,keyword,point):
    
    bkeyword = keywords_bcheckKeyword(c,keyword)
    #如果keyword 不存在的情况才把keyword塞进数据库里面
    if bkeyword == False:
        #keyword塞进 keywords 表里面
        keywords_insertRows(c,keyword)
    
    #返回keyword id 再塞进makrpoint 的表里面
    keyword_id = keywords_getID(c,keyword)
    question_id = question_getID(c,question)
    markPoint_insertRows(c,question_id,keyword_id,point)
    
    
#删除问题
def deleteQuestion(c,question):
    bQuestion = question_bcheckQuestion(c,question)
    if bQuestion == True:
        delet_id = question_getID(c,question)
        c.execute('''DELETE from question where id = ?''',(delet_id,))
        c.execute('''DELETE from solution where question_id = ?''',(delet_id,))
        c.execute('''DELETE from markPoint where question_id = ?''',(delet_id,))

#删除评分标准
def deleteQuestionMarkScheme(c,question,keyword):
    bQuestion = question_bcheckQuestion(c,question)
    bKeyword = keywords_bcheckKeyword(c,keyword)
    if bQuestion == True and bKeyword == True:
        keywordID = keywords_getID(c,keyword)
        questionID = question_getID(c,question)
        c.execute('''DELETE from markPoint where question_id = ? AND keyword_id = ?''',(questionID,keywordID,))
        

def editQuestion(c,question):
    pass

def editSolution(c,solution):
    pass
