
import random
import sys

import GameFinish
from Point import Point


BOARD_SIZE = 5


class Board:
    
    def __init__(self):

        # Set the board
        self.board = []

        # Init the board.
        self.init_board()
        
        # Set the mines
        self.total_mines = round(BOARD_SIZE * BOARD_SIZE / 5)
        self.mines_left  = self.total_mines
        self.init_mines()

        # Tell the user about the board's size
        print()
        print("Board size =", BOARD_SIZE)
        print()


    def init_board(self):
        for r in range(BOARD_SIZE):
            line = []
            for c in range(BOARD_SIZE):
                line.append(" ")
            self.board.append(line)


    def init_mines(self):

        amount_of_mines_set = 0

        while amount_of_mines_set < self.total_mines:
            r = random.randint(0, BOARD_SIZE - 1)
            c = random.randint(0, BOARD_SIZE - 1)
            if self.board[r][c] == " ":
                self.board[r][c] = "*"
                amount_of_mines_set = amount_of_mines_set + 1


    def print_it(self, reveal_mines=False):

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
                current_cell = self.board[r][c]

                if current_cell == "*":
                    if reveal_mines:
                        current_cell = "*"
                    else:
                        current_cell = " "

                print(f" {current_cell} |", end="")
            print()


    def is_solved(self):
        return self.mines_left == 0


    def is_mine(self, point):
        x = self.board[point.get_x()][point.get_y()]
        return x == "*" or x == "?"


    def clear_around(self, point):

        current_cell_mines_around = 0

        if self.is_mine(point):
            GameFinish.boom()
            self.print_it(reveal_mines=True)
            print(f"There are {self.mines_left} mines left.")
            sys.exit(1)


        c = point.get_x()
        r = point.get_y()

        for i in range(r-1, r+2):
            for j in range(c-1, c+2):
                print(f"Checking point {i}, {j}")
                current_cell = Point(i, j)
                if is_in_range(current_cell):
                    cell_value = str(self.board[i][j])
                    print(f"{i}/{j} - " + "{{" + cell_value + "}}")
                    if self.is_mine(current_cell):
                        current_cell_mines_around += 1
                else:
                    print(f"{i}/{j} - " + "{{out-of-range}}")

        print(f"There are {current_cell_mines_around} mines around here")
        self.board[r][c] = current_cell_mines_around


    def get_mines_left(self):
        return self.mines_left


    def set_mine_at(self, user_point):
        cell_content = self.board[user_point.get_x()][user_point.get_y()]
        if cell_content == "*" or cell_content == "#":
            self.board[user_point.get_x()][user_point.get_y()] = "#"
            if self.mines_left < self.total_mines:
                self.mines_left -= 1
        else:
            self.board[user_point.get_x()][user_point.get_y()] = "?"


    def unset_mine_at(self, user_point):
        cell_content = self.board[user_point.get_x()][user_point.get_y()]
        if cell_content == "#":
            self.mines_left += 1
        self.board[user_point.get_x()][user_point.get_y()] = " "


# Static
def is_in_range(point):
    return 0 <= point.get_x() < BOARD_SIZE and 0 <= point.get_y() < BOARD_SIZE
