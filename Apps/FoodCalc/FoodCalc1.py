import sys
import Messages
from tkinter import *
from UserInput import UserInput


def trips_add_new():
    print("Trips --> new")
    # Get the new trip name
    ui = UserInput("New trip name", "Enter name")
    ui.root.destroy()
    print(ui.get_user_text())



def trips_list():
    print("Trips --> list")


def exit_app():
    Messages.info("Thank you ...")
    sys.exit(0)


# Set the main window
root = Tk()
root.withdraw()


# Set the main menus
menu_bar = Menu(root)


# Set the main menu
trips_menu = Menu(menu_bar, tearoff=0)


# Set the 'Trips' sub menus
trips_menu.add_command(label="New",  command=trips_add_new)
trips_menu.add_command(label="List", command=trips_list)
trips_menu.add_separator()
trips_menu.add_command(label="Exit", command=exit_app)



menu_bar.add_cascade(label="Trips", menu=trips_menu)

root.config(menu=menu_bar)
root.mainloop()
