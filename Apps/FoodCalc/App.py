from tkinter import *


class App(object):
    def __init__(self):
        self.text = "YG"
        self.root = Tk()
        self.root.wm_title("Question 7")
        self.label = Label(self.root, text="Enter your weight in pounds.")
        self.label.pack()

        self.entry_text = StringVar()
        Entry(self.root, textvariable=self.entry_text).pack()

        self.button_text = StringVar()
        self.button_text.set("Calculate")
        Button(self.root, textvariable=self.button_text, command=self.clicked1).pack()

        self.label = Label(self.root, text="")
        self.label.pack()

        self.root.mainloop()

    def clicked1(self):
        input1 = 3423
        self.label.configure(text=input1)
        self.text = self.entry_text.get()

    def button_click(self, e):
        pass


x = App()
print(x.text)
