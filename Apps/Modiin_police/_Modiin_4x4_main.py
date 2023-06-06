import json
import tkinter as tk
from CreateButton import create_button

with open("app_config.json") as ac:
    texts = json.load(ac)

cfg = {
    "frame": {
        "w": 800,
        "h": 500,
        "bg": "#3385ff"
    },
    "label": {
        "bg": "#3385ff",
        "fg": "#000000"
    },
    "entry": {
        "w1": 5,
        "w2": 15,
        "bg": "#ffffff",
        "fg": "#0000ff"
    }
}
YES_NO = ["yes", "no"]
DATA_FILE = "drives.json"

# Set the root TK object
root = tk.Tk()


def run_ui():
    """Run the main UI widget"""
    # Set the main frame's title.
    root.title = texts['title']

    # Set the main frame.
    f_main = tk.Frame(
        root,
        bg = cfg['frame']['bg'],
        width=cfg['frame']['w'],
        height=cfg['frame']['h']
    )
    f_main.tkraise()
    f_main.location(600, 200)
    f_main.pack_propagate(False)
    f_main.grid(row=0, column=0, sticky="nesw")

    # +-------------------
    # |   Labels
    # +-------------------
    tk.Label(
        f_main,
        text=texts['title'],
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 30)
    ).place(x=120, y=30)

    tk.Label(
        f_main,
        text="Yaron Golan, yargolan@gmail.com",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 10)
    ).place(x=20, y=450)

    # +-------------------
    # |   Buttons
    # +-------------------
    # Search
    b_data = create_button("M", f_main, "חיפוש")
    b_data.place(x=350, y=150)
    b_data.configure(command=volunteers)

    # Volunteers
    b_data = create_button("M", f_main, "מתנדבים")
    b_data.place(x=550, y=150)
    b_data.configure(command=None)

    # Report
    b_report = create_button("M", f_main, "הפקת דוח")
    b_report.place(x=150, y=150)
    b_report.configure(command=None)


    # Run the app
    root.mainloop()


def volunteers():
    # Set the volunteers frame.
    f_volunteers = tk.Frame(
        root,
        bg = cfg['frame']['bg'],
        width=cfg['frame']['w'],
        height=cfg['frame']['h']
    )
    f_volunteers.tkraise()
    f_volunteers.location(600, 200)
    f_volunteers.pack_propagate(False)
    f_volunteers.grid(row=0, column=0, sticky="nesw")

    # +-------------------
    # |   Labels
    # +-------------------
    tk.Label(
        f_volunteers,
        text=texts['title'],
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 30)
    ).place(x=120, y=30)

    # +-------------------
    # |   Buttons
    # +-------------------
    # Add
    b_data = create_button("M", f_volunteers, "הוספה")
    b_data.place(x=580, y=150)
    b_data.configure(command=vol_add)

    # Remove
    b_data = create_button("M", f_volunteers, "גריעה")
    b_data.place(x=400, y=150)
    b_data.configure(command=vol_del)

    # vol card
    b_data = create_button("M", f_volunteers, "כרטיס")
    b_data.place(x=220, y=150)
    b_data.configure(command=vol_show)

    # Back
    b_data = create_button("S", f_volunteers, "back")
    b_data.place(x=20, y=460)
    b_data.configure(command=run_ui)


def vol_del():
    pass


def vol_add():
    # Set the volunteers frame.
    f_vol_add = tk.Frame(
        root,
        bg = cfg['frame']['bg'],
        width=cfg['frame']['w'],
        height=cfg['frame']['h']
    )
    f_vol_add.tkraise()
    f_vol_add.location(600, 200)
    f_vol_add.pack_propagate(False)
    f_vol_add.grid(row=0, column=0, sticky="nesw")

    # +-------------------
    # |   Labels
    # +-------------------
    tk.Label(
        f_vol_add,
        text=texts['title'],
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 30)
    ).place(x=120, y=30)

    tk.Label(
        f_vol_add,
        text="מספר סידורי",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=100)

    tk.Label(
        f_vol_add,
        text="שם משפחה",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=140)

    tk.Label(
        f_vol_add,
        text="שם פרטי",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=180)

    tk.Label(
        f_vol_add,
        text="מספר ת. זהות",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=220)

    tk.Label(
        f_vol_add,
        text="כתובת",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=260)

    tk.Label(
        f_vol_add,
        text="מספר טלפון",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=300)

    tk.Label(
        f_vol_add,
        text="דואר אלקטרוני",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=340)

    # +-------------------
    # |   Entries
    # +-------------------
    # Vol ID
    vol_id_num = tk.StringVar()
    entry_id_num = tk.Entry(
        f_vol_add,
        width=cfg['entry']['w2'],
        bg=cfg['entry']['bg'],
        fg=cfg['entry']['fg'],
        textvariable=vol_id_num
    )
    entry_id_num.place(x=470, y=90)
    entry_id_num.delete(0, tk.END)

    # Vol last name
    vol_name_last = tk.StringVar()
    entry_name_last = tk.Entry(
        f_vol_add,
        width=cfg['entry']['w2'],
        bg=cfg['entry']['bg'],
        fg=cfg['entry']['fg'],
        textvariable=vol_name_last
    )
    entry_name_last.place(x=470, y=130)
    entry_name_last.delete(0, tk.END)

    # Vol first name
    vol_name_first = tk.StringVar()
    entry_name_first = tk.Entry(
        f_vol_add,
        width=cfg['entry']['w2'],
        bg=cfg['entry']['bg'],
        fg=cfg['entry']['fg'],
        textvariable=vol_name_first
    )
    entry_name_first.place(x=470, y=170)
    entry_name_first.delete(0, tk.END)

    # +-------------------
    # |   Buttons
    # +-------------------
    # Back
    b_data = create_button("S", f_vol_add, "back")
    b_data.place(x=20, y=460)
    b_data.configure(command=volunteers)


def vol_show():
    # Set the volunteers frame.
    f_vol_show = tk.Frame(
        root,
        bg = cfg['frame']['bg'],
        width=cfg['frame']['w'],
        height=cfg['frame']['h']
    )
    f_vol_show.tkraise()
    f_vol_show.location(600, 200)
    f_vol_show.pack_propagate(False)
    f_vol_show.grid(row=0, column=0, sticky="nesw")

    # +-------------------
    # |   Labels
    # +-------------------
    tk.Label(
        f_vol_show,
        text=texts['title'],
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 30)
    ).place(x=120, y=30)

    tk.Label(
        f_vol_show,
        text="מספר סידורי",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=100)

    tk.Label(
        f_vol_show,
        text="שם משפחה",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=140)

    tk.Label(
        f_vol_show,
        text="שם פרטי",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=180)

    tk.Label(
        f_vol_show,
        text="מספר ת. זהות",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=220)

    tk.Label(
        f_vol_show,
        text="כתובת",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=260)

    tk.Label(
        f_vol_show,
        text="מספר טלפון",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=300)

    tk.Label(
        f_vol_show,
        text="דואר אלקטרוני",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=650, y=340)


    # +-------------------
    # |   Buttons
    # +-------------------
    # Back
    b_data = create_button("S", f_vol_show, "back")
    b_data.place(x=20, y=460)
    b_data.configure(command=volunteers)


if __name__ == '__main__':

    # Run the UI part.
    try:
        run_ui()
    except KeyboardInterrupt as ke:
        print("Interrupted by the user...")
