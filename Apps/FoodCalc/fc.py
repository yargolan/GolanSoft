import sys
import Messages
import SqliteWrapper
from tkinter import *


# DB
db = SqliteWrapper.SqliteWrapper()


def c_add_trip():

    # Get the trip's name.
    trip_name = Messages.get_user_input("Trip name", "Enter the trip's name")
    if trip_name is None:
        return

    # Check if the trip name already exists in the DB.
    if db.is_trip_exists(trip_name):
        Messages.error(f"The trip '{trip_name}' already exists.")
    else:
        trip_date = Messages.get_user_input("Trip date", "Enter the trip's date")
        db.add_trip(trip_name=trip_name, trip_date=trip_date)



    #
    # if trip_name is None:
    #     return
    #
    #
    # t = Trip(trip_name)
    #
    # if t is not None:
    #
    #     if is_already_exists(t.get_name()):
    #         Messages.show_message("Error", "The trip '{}' already exist.".format(t.get_name()))



def is_already_exists(trip_name):
    print(trip_name)
    return False


def main():

    def c_exit():
        root.destroy()
        sys.exit(0)

    root = Tk()
    root.geometry("600x300+400+200")

    # Add a close button
    btn_close = Button(root, text="Close the app", command=c_exit)
    btn_close.place(x=450, y=20)

    # Add a new trip button
    btn_add = Button(root, text="Add a trip", command=c_add_trip)
    btn_add.place(x=20, y=20)



    root.mainloop()


if __name__ == '__main__':
    main()
