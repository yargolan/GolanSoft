#!/usr/bin/env python3

import sys


index = 0
board = {}


def main():
    """ Init the board """
    init_board()

    """ Select the initial start point """
    initial_x, initial_y = get_initial_start_point()

    """ Print the board before we begin """
    print_board()

    """ Fill the board, starting from the stating point """
    fill_board(initial_x, initial_y, index)
    print_board()


def init_board():

    for row in range(0, 8):
        for column in range(0, 8):
            board[row, column] = 0

    return board


def get_initial_start_point():
    return 0, 0


def print_board():
    for row in range(0, 8):
        for column in range(0, 8):
            current_cell = board[row, column]
            if current_cell < 10:
                print(f"0{current_cell}", end=" ")
            else:
                print(current_cell, end=" ")
        print()
    print()


def fill_board(x, y, current_index):

    """ Verify that the current cell is not out-of-bounds """
    if x >= 8 or y >= 8 or x < 0 or y < 0:
        print(f"{x} {y} : Place is out-of-bounds.")
        return

    # Verify that the current location is "free"
    if is_place_taken(x, y):
        print(f"{x} {y} : Place is occupied.")
        return
    else:
        print(f"{x} {y} : Place is free.")
        current_index = get_next_index(current_index)
        board[x, y] = current_index

    is_valid = is_valid_board()

    while not is_valid:

        # Try to move x --> 1, y --> 1
        fill_board(x+1, y+2, current_index)

        # Try to move x --> 2, y --> 1
        fill_board(x+2, y+1, current_index)

        # Try to move x --> -2, y --> -1
        fill_board(x-2, y-1, current_index)

        # Try to move x --> -1, y --> -2
        fill_board(x-1, y-2, current_index)


        # Reduce the index


def is_valid_board():
    return 64 in board


def is_place_taken(x, y):
    return board[x, y] != 0


def free_place(x, y):
    board[x, y] = 0


def get_next_index(i):
    i += 1
    return i


def print_success_message():
    print()
    print("===============")
    print(" S U C C E S S")
    print("===============")
    print()
    print_board()
    sys.exit(0)


# ------------------------------------------------------------
if __name__ == '__main__':
    main()
