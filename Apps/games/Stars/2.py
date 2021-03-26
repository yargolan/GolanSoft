#!/usr/bin/env python3


def get_amount():
    answer = input("How many line do you want to have: ")
    return int(answer)


def draw_triangular(needed_lines):
    if needed_lines <= 0:
        return
    elif needed_lines == 1:

        amount_of_stars  = 1
        amount_of_spaces = 0

        current_line = " " * amount_of_spaces + "*" * amount_of_stars

        print(current_line)
    else:

        for i in range(1, needed_lines + 1):
            amount_of_spaces = needed_lines - 1
            amount_of_stars  = i * 2 - 1
            current_line = " " * amount_of_spaces + "*" * amount_of_stars
            print(current_line)
            needed_lines -= 1


# Get the amount from the user
user_value = get_amount()

# Draw the triangular
draw_triangular(user_value)
