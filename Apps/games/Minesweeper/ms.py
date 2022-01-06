import GameFinish
import HC
import re
import sys
import Cell
import random


# Hardcoded information
DEBUG_MODE = True
BOARD_SIZE = 6

# Global variables
board = []
total_mines = round(BOARD_SIZE * BOARD_SIZE / 5)


def main():

    # Init the board itself
    init_board()

    # Set the mines on the board
    init_mines()

    # Get the user's command.
    while not is_board_solved():
        user_command = get_user_command()
        if user_command == 0:
            print_the_board(reveal_mines = True)
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


def is_board_solved():
    return False


def guess_a_spot(cell):
    if board[cell.get_x()][cell.get_y()] == HC.MINE:
        GameFinish.boom()
        print_the_board(reveal_mines = True)
        sys.exit(1)

    # Walk the cells around the selection and see how many mines are there.
    mines_around = 0
    for c in range(cell.get_x()-1, cell.get_x()+2):
        for r in range(cell.get_y()-1, cell.get_y()+2):
            if c < 0 or c > BOARD_SIZE or r < 0 or r > BOARD_SIZE:
                if DEBUG_MODE:
                    print(f"{c}/{r} is out of range")
                continue

            print(f"DEBUG: {c}/{r}  =  [{board[c][r]}]")
            if board[c][r] == HC.MINE or board[c][r] == HC.MARK:
                mines_around += 1

    board[cell.get_x()][cell.get_y()] = mines_around


def mark_as_mine(cell):
    board[cell.get_x()][cell.get_y()] = HC.MARK


def clear_marked_cell(cell):
    if board[cell.get_x()][cell.get_y()] != HC.EMPTY:
        board[cell.get_x()][cell.get_y()] = HC.EMPTY


def get_cell():

    x = ""
    y = ""
    while type(x) != int and type(y) != int:
        print()
        coordinates = input("Enter the cell coordinates : ")
        coordinates.replace(" ", "")

        if coordinates.__contains__(","):
            y = int(coordinates.split(",")[0])
            x = int(coordinates.split(",")[1])

    cell = Cell.Cell(x, y)
    return cell


def get_user_command():
    user_choice = 99

    while 0 < user_choice > 3:
        print("0. Give up")
        print("1. Guess a spot")
        print("2. Mark a mine")
        print("3. Clean a mark")
        user_choice = int(input("Your choice : "))

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
                print(f"I've put a mine at {c}/{r}")
            amount_of_mines_set = amount_of_mines_set + 1


def print_the_board(reveal_mines=False):
    # Print header.
    print()
    for i in range(BOARD_SIZE):
        print(f" | {i}", end = "")
    print(" |")
    print("-+", end = "")
    for i in range(BOARD_SIZE):
        print("---+", end = "")
    print()

    # Walk over the board
    for r in range(BOARD_SIZE):
        print(f"{r}|", end = "")

        for c in range(BOARD_SIZE):
            current_cell = board[r][c]

            if current_cell == HC.MINE:
                if reveal_mines:
                    current_cell = HC.MINE
                else:
                    current_cell = HC.EMPTY

            print(f" {current_cell} |", end = "")
        print()
    print()


if __name__ == '__main__':
    main()
