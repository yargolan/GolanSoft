import re
import tkinter as tk
from datetime import date
from CreateButton import create_button


cfg = {
    "frame": {
        "w": 500,
        "h": 420,
        "bg": "#3385ff"
    },
    "label": {
        "bg": "#3385ff",
        "fg": "#000000"
    },
    "entry": {
        "w": 15,
        "bg": "#ffffff",
        "fg": "#0000ff"
    }
}
yes_no = ["כן", "לא"]

# Set the root TK object
root = tk.Tk()


def generate_report():
    pass


def insert_data(current_drive):

    regex_time = re.compile('^\\d{2}:\\d{2}$')
    regex_odometer = re.compile('^\\d{1,6}$')

    # validate
    if "date" not in current_drive:
        print("No date provided.")
        return
    if "time_start" not in current_drive:
        print("No end time provided.")
        return
    if "time_end" not in current_drive:
        print("No end time provided.")
        return
    if "odometer_start" not in current_drive:
        print("No end odometer provided.")
        return
    if "odometer_end" not in current_drive:
        print("No start odometer provided.")
        return
    if "non_urban" not in current_drive:
        print("No non urban flag provided.")
        return
    if "urban" not in current_drive:
        print("No urban flag provided.")
        return

    if current_drive['date'] is None or current_drive['date'] == "":
        print("Invalid date provided.")
        return
    if current_drive['time_end'] is None or current_drive['time_end'] == "":
        print("Invalid end time provided.")
        return
    if current_drive['time_start'] is None or current_drive['time_start'] == "":
        print("Invalid start time provided.")
        return
    if current_drive['odometer_end'] is None or current_drive['odometer_end'] == "":
        print("Invalid end odometer read provided.")
        return
    if current_drive['odometer_start'] is None or current_drive['odometer_start'] == "":
        print("Invalid start odometer read provided.")
        return

    if regex_time.match(current_drive['time_start']) is None:
        print("Start time has wrong format.")
        return
    if regex_time.match(current_drive['time_end']) is None:
        print("End time has wrong format.")
        return
    if regex_odometer.match(current_drive['odometer_start']) is None:
        print("Odometer start has wrong format.")
        return
    if regex_odometer.match(current_drive['odometer_end']) is None:
        print("Odometer end has wrong format.")
        return

    # calculate the amount of kilometers driven
    kilometers_driven = int(current_drive['odometer_end']) - int(current_drive['odometer_start'])
    if kilometers_driven <= 0:
        print("Odometer end cannot be lower the start.")
        return

    current_drive['driven_kilometer'] = kilometers_driven

    print(current_drive)



def get_date():
    today = date.today()
    return today.strftime("%d/%m/%Y")


def ui_insert_data():
    # Load the data insertion frame
    f_data_insertion = tk.Frame(
        root,
        bg=cfg['frame']['bg'],
        width=cfg['frame']['w'],
        height=cfg['frame']['h'],
    )
    f_data_insertion.location(400, 100)
    f_data_insertion.tkraise()
    f_data_insertion.pack_propagate(False)
    f_data_insertion.grid(row=0, column=0, sticky="nesw")

    # +-----------------
    # |   Labels
    # +-----------------
    # Date
    tk.Label(
        f_data_insertion,
        text="תאריך",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=380, y=20)

    tk.Label(
        f_data_insertion,
        text="שעת התחלה",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=380, y=70)

    tk.Label(
        f_data_insertion,
        text="שעת סיום",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=380, y=120)

    tk.Label(
        f_data_insertion,
        text="מד אוץ התחלה",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=380, y= 170)

    tk.Label(
        f_data_insertion,
        text="מד אוץ סיום",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=380, y=220)

    tk.Label(
        f_data_insertion,
        text="עירוני",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=380, y=270)

    tk.Label(
        f_data_insertion,
        text="בינעירוני",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 15)
    ).place(x=380, y=320)

    tk.Label(
        f_data_insertion,
        text="HH:MM, 24h",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 12)
    ).place(x=120, y=70)

    tk.Label(
        f_data_insertion,
        text="HH:MM, 24h",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 12)
    ).place(x=120, y=120)

    # +-------------------
    # |   Entries
    # +-------------------
    # Date
    drive_date = tk.StringVar()
    entry_drive_date = tk.Entry(
        f_data_insertion,
        width=cfg['entry']['w'],
        bg=cfg['entry']['bg'],
        fg=cfg['entry']['fg'],
        textvariable=drive_date
    )
    entry_drive_date.place(x=210, y=18)
    entry_drive_date.delete(0, tk.END)

    # Hour - start
    drive_hour_start = tk.StringVar()
    entry_drive_hour_start = tk.Entry(
        f_data_insertion,
        width=cfg['entry']['w'],
        bg=cfg['entry']['bg'],
        fg=cfg['entry']['fg'],
        textvariable=drive_hour_start
    )
    entry_drive_hour_start.place(x=210, y=68)
    entry_drive_hour_start.delete(0, tk.END)

    # Hour - end
    drive_hour_end = tk.StringVar()
    entry_drive_hour_end = tk.Entry(
        f_data_insertion,
        width=cfg['entry']['w'],
        bg=cfg['entry']['bg'],
        fg=cfg['entry']['fg'],
        textvariable=drive_hour_end
    )
    entry_drive_hour_end.place(x=210, y=118)
    entry_drive_hour_end.delete(0, tk.END)


    # odometer - start
    drive_odometer_start = tk.StringVar()
    entry_drive_odometer_start = tk.Entry(
        f_data_insertion,
        width=cfg['entry']['w'],
        bg=cfg['entry']['bg'],
        fg=cfg['entry']['fg'],
        textvariable=drive_odometer_start
    )
    entry_drive_odometer_start.place(x=210, y=168)
    entry_drive_odometer_start.delete(0, tk.END)

    # odometer - end
    drive_odometer_end = tk.StringVar()
    entry_drive_odometer_end = tk.Entry(
        f_data_insertion,
        width=cfg['entry']['w'],
        bg=cfg['entry']['bg'],
        fg=cfg['entry']['fg'],
        textvariable=drive_odometer_end
    )
    entry_drive_odometer_end.place(x=210, y=218)
    entry_drive_odometer_end.delete(0, tk.END)

    # +-----------------
    # |   Drop-downs
    # +-----------------
    urban = tk.StringVar(f_data_insertion)
    urban.set(yes_no[0])
    w = tk.OptionMenu(
        f_data_insertion,
        urban,
        *yes_no
    )
    w.configure(width=12)
    w.place(x=210, y=268)

    non_urban = tk.StringVar(f_data_insertion)
    non_urban.set(yes_no[0])
    w = tk.OptionMenu(
        f_data_insertion,
        non_urban,
        *yes_no
    )
    w.configure(width=12)
    w.place(x=210, y=318)

    # +-----------------
    # |   Buttons
    # +-----------------
    # Back
    b_back = create_button("S", f_data_insertion, "חזרה")
    b_back.configure(command=lambda: run_ui())
    b_back.place(x=20, y=370)

    # Insert
    b_insert = create_button("S", f_data_insertion, "הזן")
    b_insert.configure(command=lambda: insert_data(
        {
            "date": entry_drive_date.get(),
            "urban": urban.get(),
            "time_end": entry_drive_hour_end.get(),
            "non_urban": non_urban.get(),
            "time_start": entry_drive_hour_start.get(),
            "odometer_end": entry_drive_odometer_end.get(),
            "odometer_start": entry_drive_odometer_start.get()
        }
    ))
    b_insert.place(x=20, y=320)

    # +-----------------
    # |   Initial data
    # +-----------------
    entry_drive_date.insert(0, get_date())


def run_ui():
    # Set the main frame's title.
    root.title = "מעקב שעות נהיגה של שחר"

    # Set the main frame.
    f_main = tk.Frame(
        root,
        bg = cfg['frame']['bg'],
        width=cfg['frame']['w'],
        height=cfg['frame']['h']
    )
    f_main.tkraise()
    f_main.location(400, 200)
    f_main.pack_propagate(False)
    f_main.grid(row=0, column=0, sticky="nesw")

    # +-------------------
    # |   Labels
    # +-------------------
    tk.Label(
        f_main,
        text="מעקב שעות נהיגה של שחר",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 30)
    ).place(x=50, y=30)

    tk.Label(
        f_main,
        text="Yaron Golan, yargolan@gmail.com",
        bg=cfg['label']['bg'],
        fg=cfg['label']['fg'],
        font=("TkHeadingFont", 10)
    ).place(x=320, y=390)

    # +-------------------
    # |   Buttons
    # +-------------------
    # Data
    b_data = create_button("M", f_main, "הזנת נתונים")
    b_data.place(x=250, y=120)
    b_data.configure(command=lambda: ui_insert_data())

    # Report
    b_report = create_button("M", f_main, "הפקת דוח")
    b_report.place(x=50, y=120)
    b_report.configure(command=lambda: generate_report())


    # Run the app
    root.mainloop()



if __name__ == '__main__':

    # Run the UI part.
    try:
        run_ui()
    except KeyboardInterrupt as ke:
        print("Interrupted by the user...")
