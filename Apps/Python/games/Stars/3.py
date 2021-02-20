#!/usr/bin/env python3


def get_amount():
    answer = input("How many line do you want to have: ")
    return int(answer)


def draw_triangular(needed_lines):
    if needed_lines <= 0:
        return
    elif needed_lines == 1:
        print("*")
    else:

        # Acs
        for i in range(1, needed_lines + 1):
            current_line = "+" * i
            print(current_line)

        # Dec
        for j in range(needed_lines-1, 0, -1):
            current_line = "+" * j
            print(current_line)


# Get the amount from the user
user_value = get_amount()

# Draw the triangular
draw_triangular(user_value)
