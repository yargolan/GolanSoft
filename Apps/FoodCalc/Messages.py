from tkinter import *
from tkinter import messagebox


def show_message(title, message):
    root = Tk()
    root.withdraw()
    messagebox.showinfo(title, message)


def info(message):
    show_message("Info", message)
