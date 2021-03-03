#!/usr/bin/env python3


def get_amount():
    answer = input("How many line do you want to have: ")
    answer = int(answer)
    if answer > 1 and answer % 2 == 0:
            answer += 1
    return answer


def draw_diamond(needed_lines):
    if needed_lines <= 0:
        return
    elif needed_lines == 1:

        amount_of_stars = 1
        amount_of_spaces = 0

        current_line = " " * amount_of_spaces + "*" * amount_of_stars

        print(current_line)
    else:
        draw_asc(needed_lines)
        draw_dec(needed_lines)


def draw_asc(needed_lines):
    for i in range(1, needed_lines + 1):
        amount_of_stars  = i * 2 - 1
        amount_of_spaces = needed_lines - 1
        current_line = " " * amount_of_spaces + "*" * amount_of_stars
        print(current_line)
        needed_lines -= 1


def draw_dec(needed_lines):

    amount_of_spaces = 0

    for j in range(needed_lines - 1, 0, -1):
        amount_of_stars  = j * 2 - 1
        amount_of_spaces += 1
        current_line = " " * amount_of_spaces + "*" * amount_of_stars
        print(current_line)


# Get the amount from the user
user_value = get_amount()

# Draw the triangular
draw_diamond(user_value)
