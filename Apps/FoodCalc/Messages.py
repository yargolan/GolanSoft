from tkinter import *
from tkinter import messagebox, simpledialog


def show_message(title, message):
    root = Tk()
    root.withdraw()
    messagebox.showinfo(title, message)


def info(message):
    show_message("Info", message)


def error(message):
    show_message("Error", message)


def get_user_input(title, message):
    root = Tk()
    root.withdraw()
    result = simpledialog.askstring(title, message, parent=root)
    return result
