#!/usr/bin/env python3


def get_amount():
    answer = 0

    while answer < 3:
        answer = input("How many line do you want to have  (At least 3):  ")
        answer = int(answer)

    if answer % 2 == 0:
        answer += 1
    return answer


def draw_butterfly_tie(needed_lines):
    if needed_lines == 1:
        print("*")
        return

    print()
    print(f"Drawing {needed_lines} lines.")
    print()

    """ Upper part """
    draw_asc(needed_lines)

    """ <Middle> part """
    print("*" * needed_lines)


    """ Lower part """
    draw_dec(needed_lines)


def draw_asc(needed_lines):
    amount_of_spaces = needed_lines - 2

    for amount_of_stars in range(0, needed_lines // 2):
        amount_of_stars += 1

        current_line = "{}{}{}".format("*" * amount_of_stars, " " * amount_of_spaces, "*" * amount_of_stars)
        print(current_line)
        amount_of_spaces -= 2


def draw_dec(needed_lines):
    amount_of_spaces = 1

    for amount_of_stars in range((needed_lines // 2), 0, -1):
        current_line = "{}{}{}".format("*" * amount_of_stars, " " * amount_of_spaces, "*" * amount_of_stars)
        print(current_line)
        amount_of_spaces += 2


def main():
    # Get the amount from the user
    user_value = get_amount()

    # Draw the triangular
    draw_butterfly_tie(user_value)


if __name__ == "__main__":
    main()
