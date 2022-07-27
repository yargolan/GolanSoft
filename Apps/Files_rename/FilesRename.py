import os
import tkinter as tk


# Set the root TK object
from tkinter import filedialog, scrolledtext


root = tk.Tk()


def main():

    # Set main window properties.
    root.title("Files rename")
    root.eval("tk::PlaceWindow . center")
    root.geometry("700x400+600+200")
    root.resizable(False, False)

    # Load the main frame
    f_main_frame = tk.Frame(root, width = 700, height = 600, bg = "#385fae")
    f_main_frame.location(400, 100)
    f_main_frame.tkraise()
    f_main_frame.pack_propagate(False)
    f_main_frame.grid(row = 0, column = 0, sticky = "nesw")

    # Labels - Folder name
    tk.Label(
        f_main_frame,
        text = "Folder name",
        bg = "#385fae",
        fg = "#aad5d5",
        font = ("TkHeadingFont", 20)
    ).place(x = 20, y = 20)

    # Labels - Prefix
    tk.Label(
        f_main_frame,
        text = "prefix",
        bg = "#385fae",
        fg = "#aad5d5",
        font = ("TkHeadingFont", 20)
    ).place(x = 20, y = 60)

    # Labels - Postfix
    tk.Label(
        f_main_frame,
        text = "postfix",
        bg = "#385fae",
        fg = "#aad5d5",
        font = ("TkHeadingFont", 20)
    ).place(x = 20, y = 100)

    # Folder path
    ta_folder_name = tk.StringVar()
    folder_name_entry = tk.Entry(f_main_frame, width = 45, textvariable = ta_folder_name)
    folder_name_entry.place(x = 150, y = 20)

    # Prefix
    prefix = tk.StringVar()
    prefix_entry = tk.Entry(f_main_frame, width = 20, textvariable = prefix)
    prefix_entry.place(x = 150, y = 60)

    # Postfix
    postfix = tk.StringVar()
    postfix_entry = tk.Entry(f_main_frame, width = 20, textvariable = postfix)
    postfix_entry.place(x = 150, y = 100)

    # Scrolled text
    st = scrolledtext.ScrolledText(
        f_main_frame,
        bg = "#385fae",
        width = 44,
        height = 10,
        font = 'TkDefaultFont 14'
    )
    st.place(x=150, y=150)

    # Browse button
    tk.Button(
        f_main_frame,
        fg = "#385fae",
        bg = "#FFFFFF",
        text = 'browse ...',
        font = ("TkHeadingFont", 16),
        width = 8,
        cursor = "hand1",
        activebackground = "#173BE2",
        activeforeground = "#FFFFFF",
        command = lambda: ui_search_for_files(folder_name_entry, st)
    ).place(x=590, y=22)

    # Apply button
    tk.Button(
        f_main_frame,
        fg = "#385fae",
        bg = "#FFFFFF",
        text = 'Apply !',
        font = ("TkHeadingFont", 16),
        width = 8,
        cursor = "hand1",
        activebackground = "#173BE2",
        activeforeground = "#FFFFFF",
        command = lambda: ui_search_for_files(folder_name_entry, st)
    ).place(x=20, y=300)

    # Run the app
    root.mainloop()


def ui_search_for_files(folder_entry, files_text_area):
    folder_name = filedialog.askdirectory(
        title = 'Select folder name',
        initialdir = str(os.curdir),
    )
    folder_entry.delete(0, 'end')
    folder_entry.insert(0, folder_name)

    # Get yourself into the needed folder.
    os.chdir(folder_name)

    # Insert the files into the text area.
    files = os.listdir('.')
    for file in sorted(files):
        if file.startswith("."):
            continue
        current_item_full_name = os.sep.join([folder_name, file])
        if os.path.isfile(current_item_full_name):
            files_text_area.insert(tk.INSERT, f"{file}\n")


if __name__ == '__main__':
    main()
