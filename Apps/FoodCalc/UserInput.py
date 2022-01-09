
from tkinter import *


class UserInput(object):

    def __init__(self, title, message):
        self.text = "N/A"
        self.root = Tk()
        self.root.wm_title(title)
        self.label = Label(self.root, text=message)
        self.label.pack()

        self.entry_text = StringVar()
        Entry(self.root, textvariable=self.text).pack()
        # Entry(self.root, textvariable=self.entry_text).pack()

        self.button_text = " OK "
        self.button_text = StringVar()
        self.button_text.set(" ok ")
        Button(self.root, textvariable=self.button_text, command=self.clicked).pack()

        self.root.mainloop()


    def clicked(self):

        print(f"[[{self.entry_text.get()}]]")
        print(f"[[{self.entry_text}]]")
        self.text = self.entry_text.get()

        print(f"[{self.text}]")
        self.root.destroy()
        print(f"[{self.text}]")


    def get_user_text(self):
        return self.text
