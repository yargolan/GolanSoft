"""Small app to keep track over Shahar's driving hours"""

import re
import os
import sys
import json
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
YES_NO = ["yes", "no"]
DATA_FILE = "drives.json"

# Set the root TK object
root = tk.Tk()


def generate_report():
    """Generate a HTML report from the data stored"""

    # Read the drives data
    with open(DATA_FILE, "r", encoding="utf-8") as data_file:
        data = json.load(data_file)
    drives_data = data['drives']

    # Read the report template file
    with open("template.html", "r", encoding="utf-8") as template_file:
        template = template_file.readlines()

    # Create the report
    report = []
    data['total_time'] = 0
    data['total_distance'] = 0
    for line in template:
        if line.strip() == "<!--PH-->":
            for drive in drives_data:

                # Add the total time and distance.
                data['total_time'] += int(drive['driven_time'])
                data['total_distance'] += (drive['driven_distance'])

                # Convert the needed data
                drive['driven_time'] = convert_minutes_to_time(drive['driven_time'])
                drive['driven_distance'] = str(drive['driven_distance'])

                # Set the data into the report.
                t_d = "</td>\n"
                tab_td = "\t\t<td>"
                report.append("\t<tr>\n")
                report.append(tab_td + drive['date'] + t_d)
                report.append(tab_td + drive['time_start'] + t_d)
                report.append(tab_td + drive['time_end'] + t_d)
                report.append(tab_td + drive['driven_time'] + t_d)
                report.append(tab_td + drive['odometer_start'] + t_d)
                report.append(tab_td + drive['odometer_end'] + t_d)
                report.append(tab_td + drive['driven_distance'] + t_d)
                report.append(tab_td + drive['urban'] + t_d)
                report.append(tab_td + drive['non_urban'] + t_d)
                report.append("\t</tr>\n")

            # Add the total values
            data['total_time'] = convert_minutes_to_time(data['total_time'])
            data['total_distance'] = str(data['total_distance'])
            report.append("\t<tr>\n")
            report.append("\t\t<td colspan=\"3\"><b>Total time</b></td>\n")
            report.append("\t\t<td><b>" + data['total_time'] + "</b></td>\n")
            report.append("\t\t<td colspan=\"2\"><b>Total distance</b></td>\n")
            report.append("\t\t<td><b>" + data['total_distance'] + "</b></td>\n")
            report.append("\t\t<td colspan=\"2\">Yaron Golan, yargolan@gmail.com</td>\n")
            report.append("\t</tr>\n")
        else:
            report.append(line)


    # Write the report
    with open("report.html", "w", encoding="utf-8") as report_file:
        report_file.writelines(report)
    print("The report created successfully.")


def validate_inserted_data(current_drive):
    """Validate that all needed ata exists"""

    regex_time = re.compile('^\\d{2}:\\d{2}$')
    regex_odometer = re.compile('^\\d{1,6}$')

    try:
        if "date" not in current_drive:
            raise KeyError("No date provided.")
        if "time_start" not in current_drive:
            raise KeyError("No end time provided.")
        if "time_end" not in current_drive:
            raise KeyError("No end time provided.")
        if "odometer_start" not in current_drive:
            raise KeyError("No end odometer provided.")
        if "odometer_end" not in current_drive:
            raise KeyError("No start odometer provided.")
        if "urban" not in current_drive:
            raise KeyError("No urban flag provided.")
        if "non_urban" not in current_drive:
            raise KeyError("No non urban flag provided.")
        if current_drive['date'] is None or current_drive['date'] == "":
            raise KeyError("Invalid date provided.")
        if current_drive['time_end'] is None or current_drive['time_end'] == "":
            raise KeyError("Invalid end time provided.")
        if current_drive['time_start'] is None or current_drive['time_start'] == "":
            raise KeyError("Invalid start time provided.")
        if current_drive['odometer_end'] is None or current_drive['odometer_end'] == "":
            raise KeyError("Invalid end odometer read provided.")
        if current_drive['odometer_start'] is None or current_drive['odometer_start'] == "":
            raise KeyError("Invalid start odometer read provided.")
        if regex_time.match(current_drive['time_start']) is None:
            raise KeyError("Start time has wrong format.")
        if regex_time.match(current_drive['time_end']) is None:
            raise KeyError("End time has wrong format.")
        if regex_odometer.match(current_drive['odometer_start']) is None:
            raise KeyError("Odometer start has wrong format.")
        if regex_odometer.match(current_drive['odometer_end']) is None:
            raise KeyError("Odometer end has wrong format.")
    except KeyError as key_error:
        return key_error


def insert_data(current_drive):
    """Set the drive data"""

    # validate
    status = validate_inserted_data(current_drive)
    if status is not None or status != "":
        sys.exit(str(status))


    # calculate the amount of kilometers driven
    kilometers_driven = int(current_drive['odometer_end']) - int(current_drive['odometer_start'])
    if kilometers_driven <= 0:
        print("Odometer end cannot be lower the start.")
        return
    current_drive['driven_distance'] = int(kilometers_driven)

    # Calculate the amount of time spent.
    current_drive['driven_time'] = calculate_time_driven(
        current_drive['time_start'],
        current_drive['time_end']
    )

    # convert the boolean flags into strings.
    current_drive['urban'] = str(current_drive['urban'])
    current_drive['non_urban'] = str(current_drive['non_urban'])

    # Add the current drive to the list.
    add_session(current_drive)

    # tkinter.messagebox.showinfo("", "Added successfully.")
    run_ui()


def add_session(current_session):
    """Add the current drive session"""
    if os.path.isfile(DATA_FILE):
        with open(DATA_FILE, "r", encoding="utf-8") as data_base:
            data = json.load(data_base)
    else:
        data = {"drives": []}

    # Add current session
    data['drives'].append(current_session)

    # Write the file
    with open(DATA_FILE, "w", encoding="utf-8") as data_base:
        json.dump(data, data_base, indent = 2)


def add_drive_time(current_time, added_time):
    """Accumulate the drive time"""
    current = convert_time_to_minutes(current_time)
    added_time = convert_time_to_minutes(added_time)
    total = current + added_time
    return total


def convert_time_to_minutes(some_time):
    """Get time in format hh:mm, return in minutes"""
    return 60 * int(some_time.split(":")[0])  + int(some_time.split(":")[1])


def convert_minutes_to_hours(minutes):
    """get amount of minutes, return in HH:MM format"""
    converted = {'hours': 0, 'minutes': 0}
    while minutes >= 60:
        converted['hours'] += 1
        minutes -= 60
    converted['minutes'] += minutes
    return str(converted['hours']) + ":" + str(converted['minutes'])


def calculate_time_driven(start, end):
    """ Calculate how much time driven"""
    time_end = convert_time_to_minutes(end)
    time_start = convert_time_to_minutes(start)
    return time_end - time_start


def convert_minutes_to_time(minutes):
    """Get time in minutes, return in hh:mm format"""
    total_time = [0, 0]
    if minutes < 60:
        total_time[1] = minutes
    else:
        while minutes >= 60:
            total_time[0] += 1
            minutes -= 60
        total_time[1] = minutes

    if total_time[0] == 0:
        total_time[0] = "00"
    else:
        if 0 < total_time[0] < 10:
            total_time[0] = f"0{total_time[0]}"

    if total_time[1] == 0:
        total_time[1] = "00"
    else:
        if 0 < total_time[1] < 10:
            total_time[1] = f"0{total_time[0]}"

    return f"{total_time[0]}:{total_time[1]}"


def get_date():
    """Get the formatted date of today"""
    today = date.today()
    return today.strftime("%d/%m/%Y")


def ui_insert_data():
    """Set the UI widget"""
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
    urban.set(YES_NO[0])
    dd_urban = tk.OptionMenu(
        f_data_insertion,
        urban,
        *YES_NO
    )
    dd_urban.configure(width=12)
    dd_urban.place(x=210, y=268)

    non_urban = tk.StringVar(f_data_insertion)
    non_urban.set(YES_NO[0])
    dd_non_urban = tk.OptionMenu(
        f_data_insertion,
        non_urban,
        *YES_NO
    )
    dd_non_urban.configure(width=12)
    dd_non_urban.place(x=210, y=318)

    # +-----------------
    # |   Buttons
    # +-----------------
    # Back
    b_back = create_button("S", f_data_insertion, "חזרה")
    b_back.configure(command=run_ui)
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
    """Run the main UI widget"""
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
    b_data.configure(command=ui_insert_data)

    # Report
    b_report = create_button("M", f_main, "הפקת דוח")
    b_report.place(x=50, y=120)
    b_report.configure(command=generate_report)


    # Run the app
    root.mainloop()


if __name__ == '__main__':

    # Run the UI part.
    try:
        run_ui()
    except KeyboardInterrupt as ke:
        print("Interrupted by the user...")
