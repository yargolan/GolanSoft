
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
        self.mines_left = round(BOARD_SIZE * BOARD_SIZE / 5)
        self.set_mines()


    def init_board(self):
        for r in range(BOARD_SIZE):
            line = []
            for c in range(BOARD_SIZE):
                line.append(" ")
            self.board.append(line)


    def set_mines(self):

        mines_set = 0

        while mines_set < self.mines_left:

            r = random.randint(0, BOARD_SIZE - 1)
            c = random.randint(0, BOARD_SIZE - 1)
            if self.board[r][c] == " ":
                self.board[r][c] = "*"
                mines_set = mines_set + 1


    def print_it(self, reveal_mines=False):

        # mines_left = 0

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
                    # mines_left += 1
                    if reveal_mines:
                        current_cell = "*"
                    else:
                        current_cell = " "

                print(f" {current_cell} |", end="")
            print()
        # print(f"There are {mines_left} mines left.")
        print()
        

    def is_solved(self):
        return self.mines_left == 0


    def is_mine(self, point):
        x = self.board[point.get_x()][point.get_y()]
        return x == "*"


    def clear_around(self, point):

        current_cell_mines_around = 0

        if self.is_mine(point):
            GameFinish.boom()
            self.print_it(reveal_mines=True)
            print(f"There are {self.mines_left} mines left.")
            sys.exit(1)
        else:
            for i in range(point.get_x()-1, point.get_x()+2):
                for j in range(point.get_y()-1, point.get_y()+2):
                    current_cell = Point(j, i)
                    if is_in_range(current_cell):
                        cell_value = str(self.board[current_cell.get_x()][current_cell.get_y()])
                        print(f"{i}/{j} - " + "{{" + cell_value + "}}")
                        if self.is_mine(current_cell):
                            current_cell_mines_around += 1

        self.board[point.get_x()][point.get_y()] = current_cell_mines_around


    def get_mines_left(self):
        return self.mines_left


    def set_mine_at(self, user_point):
        self.board[user_point.get_x()][user_point.get_y()] = "?"


# Static
def is_in_range(point):
    return 0 <= point.get_x() < BOARD_SIZE and 0 <= point.get_y() < BOARD_SIZE
