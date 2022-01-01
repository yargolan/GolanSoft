
import random
import GameFinish
from Point import Point

DEBUG = True


def get_mines_amount(size):
    return round(size * size / 5)


class OldBoard:
    def __init__(self):
        self.size = 5
        self.board = []
        self.mines_left = get_mines_amount(self.size)
        self.init_board()




    def print_it(self, reveal_mines=False):

        mines_left = 0

        # Print header.
        print()
        for i in range(self.size):
            print(f" | {i}", end="")
        print(" |")
        print("-+", end="")
        print("-" * self.size * 4)

        # Walk over the board
        for r in range(self.size):
            print(f"{r}|", end="")
            for c in range(self.size):

                current_cell = self.board[r][c]

                if current_cell == "*":
                    mines_left += 1
                    if reveal_mines:
                        current_cell = "*"
                    else:
                        current_cell = "?"

                print(f" {current_cell} |", end="")
            print()
        print(f"There are {mines_left} mines left.")
        print()


    def is_cleaned(self):
        is_done = True

        for r in range(self.size):
            if "?" in self.board[r]:
                is_done = False
                break
        return is_done


    def is_solved(self):
        return self.mines_left == 0


    def is_mine(self, point):
        return self.board[point.get_x()][point.get_x()] == "*"


    def mark_as_mine(self, point):
        self.board[point.get_x()][point.get_y()] = "#"


    def is_in_range(self, point):
        return 0 <= point.get_x() < self.size and 0 <= point.get_y() < self.size


    def clear_around(self, point):

        current_cell_mines_around = 0

        if self.is_mine(point):
            GameFinish.boom()
        else:

            for i in range(point.get_x()-1, point.get_x()+2):
                for j in range(point.get_y()-1, point.get_y()+2):
                    p2 = Point(j, i)
                    if DEBUG:
                        print(f"Checking point [{i}/{j}]")
                    if self.is_in_range(p2):
                        print("in range = yes")
                        if self.is_mine(p2):
                            current_cell_mines_around += 1
                    else:
                        print("in range = no")

        self.board[point.get_x()][point.get_y()] = current_cell_mines_around
