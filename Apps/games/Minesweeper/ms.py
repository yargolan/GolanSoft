import time

import GameFinish
import HC
import sys
import Cell
import random


# Hardcoded information
DEBUG_MODE = True
BOARD_SIZE = 6

# Global variables
initial_cell = Cell.Cell(0, 0)
board = []
mines_left = round(BOARD_SIZE * BOARD_SIZE / 5)
total_mines = mines_left


def main(cell):

    # Init the board itself
    init_board()

    # Set the mines on the board
    init_mines()

    # Get the user's command.
    try:

        while not is_board_solved():
            user_command = get_user_command()
            if user_command == 0:
                print_the_board(reveal_mines=True)
                sys.exit(0)
            elif user_command == 1:
                cell = get_cell()
                guess_a_spot(cell)
            elif user_command == 2:
                cell = get_cell()
                mark_as_mine(cell)
            elif user_command == 3:
                cell = get_cell()
                clear_marked_cell(cell)
            else:
                sys.exit("Invalid choice !")
            print_the_board()
    except LookupError as e:
        print_the_board(reveal_mines=True)
        print(f"{cell.get_x()}/{cell.get_y()} is out-of-range !")
        print(e)


def is_board_solved():
    return mines_left == 0


def guess_a_spot(point):
    cell_content = board[point.get_x()][point.get_y()]
    if cell_content == HC.MINE:
        GameFinish.boom()
        time.sleep(3)
        print_the_board(reveal_mines=True)
        sys.exit(1)

    # Walk the cells around the selection and see how many mines are there.
    mines_around = 0
    for c in range(point.get_x()-1, point.get_x()+2):
        for r in range(point.get_y()-1, point.get_y()+2):

            if verify_in_range(c, r):
                if DEBUG_MODE:
                    print(f"DEBUG: {c}/{r}  =  [{board[c][r]}]")
                if board[c][r] == HC.MINE or board[c][r] == HC.MARK:
                    mines_around += 1
            else:
                if DEBUG_MODE:
                    print(f"{r}/{c} is out of range")

    board[point.get_x()][point.get_y()] = mines_around


def mark_as_mine(c):
    content = board[c.get_x()][c.get_y()]
    if content == HC.MINE:
        global mines_left
        mines_left -= 1

    board[c.get_x()][c.get_y()] = HC.MARK


def clear_marked_cell(c):
    if board[c.get_x()][c.get_y()] != HC.EMPTY:
        board[c.get_x()][c.get_y()] = HC.EMPTY


def get_cell():

    x = ""
    y = ""
    in_range = verify_in_range(0, 0)

    while type(x) != int and type(y) != int and in_range:
        coordinates = input("Enter the cell coordinates : ")
        coordinates.replace(" ", "")

        if coordinates.__contains__(","):
            y = int(coordinates.split(",")[0])
            x = int(coordinates.split(",")[1])
            in_range = verify_in_range(y, x)

    c = Cell.Cell(x, y)
    return c


def verify_in_range(a, b):
    return 0 <= a < BOARD_SIZE and 0 <= b < BOARD_SIZE


def get_user_command():
    user_choice = 99

    while 0 < user_choice > 3:
        print("0. Give up")
        print("1. Guess a spot")
        print("2. Mark a mine")
        print("3. Clean a mark")
        raw = input("Your choice : ")
        raw = raw.replace(",", "")
        raw = raw.replace(" ", "")
        user_choice = int(raw)


    return user_choice


def init_board():
    for r in range(BOARD_SIZE):
        line = []
        for c in range(BOARD_SIZE):
            line.append(HC.EMPTY)
        board.append(line)


def init_mines():
    amount_of_mines_set = 0

    while amount_of_mines_set < total_mines:
        r = random.randint(0, BOARD_SIZE - 1)
        c = random.randint(0, BOARD_SIZE - 1)
        if board[r][c] == HC.EMPTY:
            board[r][c] = HC.MINE
            if DEBUG_MODE:
                print(f"I've put a mine at {r}/{c}")
            amount_of_mines_set = amount_of_mines_set + 1


def print_the_board(reveal_mines=False):
    # Print header.
    print()
    for i in range(BOARD_SIZE):
        print(f" | {i}", end="")
    print(" |")
    print("-+", end="")
    for i in range(BOARD_SIZE):
        print("---+", end="")
    print()

    # Walk over the board
    for r in range(BOARD_SIZE):
        print(f"{r}|", end="")

        for c in range(BOARD_SIZE):
            current_cell = board[r][c]

            if current_cell == HC.MINE:
                if reveal_mines:
                    current_cell = HC.MINE
                else:
                    current_cell = HC.EMPTY

            print(f" {current_cell} |", end="")
        print()
    print(f"There are {mines_left} mines left.")
    print()


if __name__ == '__main__':
    main(initial_cell)
