import HC
import random



class Board:

    def __init__(self):

        # Set the board's size
        self.board_size = 5

        # Set the amount of mines to set into the board.
        self.total_mines = round(self.board_size * self.board_size / 9)
        self.mines_left = self.total_mines

        print()
        print("+-----------------------+")
        print(f"|  Board size  : {self.board_size} / {self.board_size}  |")
        print(f"|  Mines amount: {self.total_mines}      |")
        print("+-----------------------+")
        print()

        # Set the board
        self.board = []

        # Init the board.
        self.init_board()

        # Set the mines
        self.init_mines()


    def get_mines_left(self):
        return self.mines_left



    def is_board_solved(self):
        return self.mines_left == 0



    def get_total_mines(self):
        return self.total_mines



    def init_board(self):
        for r in range(self.board_size):
            line = []
            for c in range(self.board_size):
                line.append(HC.EMPTY)
            self.board.append(line)



    def init_mines(self):

        amount_of_mines_set = 0

        while amount_of_mines_set < self.total_mines:
            r = random.randint(0, self.board_size - 1)
            c = random.randint(0, self.board_size - 1)
            if self.board[r][c] == HC.EMPTY:
                self.board[r][c] = HC.MINE
                amount_of_mines_set = amount_of_mines_set + 1



    def print_it(self, reveal_mines=False):

        # Print header.
        print()
        for i in range(self.board_size):
            print(f" | {i}", end="")
        print(" |")
        print("-+", end="")
        for i in range(self.board_size):
            print("---+", end="")
        print()

        # Walk over the board
        for r in range(self.board_size):
            print(f"{r}|", end="")

            for c in range(self.board_size):
                current_cell = self.board[r][c]

                if current_cell == "*":
                    if reveal_mines:
                        current_cell = "*"
                    else:
                        current_cell = " "

                print(f" {current_cell} |", end="")
            print()



    def is_in_range(self, current_cell):
        x = current_cell.get_x()
        y = current_cell.get_y()
        return 0 <= x < self.board_size and 0 <= y < self.board_size



    def get_content(self, some_cell):
        return self.board[some_cell.get_x()][some_cell.get_y()]



    def set_content(self, cell, content):
        current_content = self.board[cell.get_x()][cell.get_y()]
        if current_content == HC.MINE:
            self.mines_left -= 1

        self.board[cell.get_x()][cell.get_y()] = content
