import tkinter as tk


def create_button(button_type, ui_frame, text):
    buttons = {
        "S": {
            "h": 1,
            "w": 12
        },
        "M": {
            "h": 2,
            "w": 10
        },
        "L": {
            "h": 4,
            "w": 12
        }
    }

    if button_type == 'S':
        b = tk.Button(
            ui_frame,
            fg="#8c1aff",
            bg="#ffecb3",
            text=text,
            font=("TkHeadingFont", 16),
            width=9,
            height=1,
            cursor="hand2",
            activebackground="#AAAAAA",
            activeforeground="#000000"
        )
    elif button_type == 'M':
        b = tk.Button(
            ui_frame,
            fg="#8c1aff",
            bg="#ffecb3",
            text=text,
            font=("TkHeadingFont", 20),
            width=10,
            height=2,
            cursor="hand1",
            activebackground="#AAAAAA",
            activeforeground="#000000"
        )
    elif button_type == 'L':
        b = tk.Button(
            ui_frame,
            fg="#8c1aff",
            bg="#ffecb3",
            text=text,
            font=("TkHeadingFont", 20),
            width=12,
            height=4,
            cursor="hand1",
            activebackground="#AAAAAA",
            activeforeground="#000000"
        )
    else:
        b = tk.Button(
            ui_frame,
            fg="#8c1aff",
            bg="#ffecb3",
            text=text,
            font=("TkHeadingFont", 28),
            width=8,
            height=1,
            cursor="hand2",
            activebackground="#AAAAAA",
            activeforeground="#000000",
        )

    b.configure(width=buttons[button_type]['w'])
    b.configure(height=buttons[button_type]['h'])

    return b
