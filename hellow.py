
from cProfile import label
from fileinput import filename
from genericpath import isfile
from pickle import FRAME

import tkinter as tk

from tkinter import Canvas, filedialog, Text

import os

root = tk.Tk()
apps = []

if os.path.isfile('save.text'):
    with open('save.txt','r') as f:
        tempApps = f.read()
        tempApps = tempApps.split(',')
        apps = [x for x in tempApps if x.strip()]


def addApp():

    #destory the list of name during clicking the open file
    for widget in frame.winfo_children():
        widget.destory()

    filename = filedialog.askopenfilename(initialdir="/", title="Select File", filetypes = (("executables", "*.exe"), ("all files", "*.*")))
    apps.append(filename)
    print(filename)
    for app in apps:
        label = tk.Label(frame,text=app, bg = "gray")
        label.pack()

def runApps():
    for app in apps:
        os.startfile(app)



Canvas = tk.Canvas(root, height= 700, width= 700, bg="#263D42")
Canvas.pack()


frame = tk.Frame(root, bg="White")
frame.place(relwidth = 0.8, relheight=0.8, relx=0.1, rely=0.1)



OpenFile = tk.Button(root,text="Open File", padx = 10, pady=5, fg="White", bg="#263D42", command = addApp)
OpenFile.pack()

runApps = tk.Button(root,text="Run Apps", padx = 10, pady=5, fg="White", bg="#263D42", command = runApps)
runApps.pack()


root.mainloop()
