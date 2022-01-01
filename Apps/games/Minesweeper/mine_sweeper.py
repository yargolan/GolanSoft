import sys
from Board import Board
from Point import Point



def get_user_choice():

    choice = -1

    while choice < 0 or choice > 3:
        print()
        print("Enter your choice:")
        print("0. Give up")
        print("1. Guess a spot")
        print("2. Mark as mine")
        print("3. Show the board")
        choice = int(input("?: ").replace(" ", ""))
    return choice


def main():

    # Set the board
    play_board = Board()

    board_solved = False

    while not board_solved:

        user_choice = get_user_choice()

        if user_choice == 0:
            play_board.print_it(reveal_mines=True)
            sys.exit(0)
        elif user_choice == 1:
            raw = input("Location: ")
            raw = raw.replace(" ", "")
            user_point = Point(int(raw.split(",")[0]), int(raw.split(",")[1]))
            play_board.clear_around(user_point)
            play_board.print_it(False)
            print(f"There are {play_board.get_mines_left()} mines left.")
            board_solved = play_board.is_solved()
        elif user_choice == 2:
            raw = input("Location: ")
            raw = raw.replace(" ", "")
            user_point = Point(int(raw.split(",")[0]), int(raw.split(",")[1]))
            play_board.set_mine_at(user_point)
            play_board.print_it(False)
            print(f"There are {play_board.get_mines_left()} mines left.")
            board_solved = play_board.is_solved()
        elif user_choice == 3:
            play_board.print_it(True)
        else:
            board_solved = False


if __name__ == '__main__':

    main()

    # user_choice = get_user_choice()
    # print(user_choice)
    # board = Board()
    # while not board.is_solved():
    #     board.print_it()
    #
    #     user_choice = get_user_choice()
    #
    #     if user_choice == 0:
    #         board.print_it(reveal_mines=True)
    #         sys.exit(0)
    #     elif user_choice == 1:
    #         raw = input("Location: ")
    #         guess_r = int(raw.split(",")[0])
    #         guess_c = int(raw.split(",")[1])
    #         if 0 <= guess_c < board.size and 0 <= guess_r < board.size:
    #             p = Point(guess_r, guess_c)
    #             if board.is_mine(p):
    #                 GameFinish.boom()
    #                 board.print_it(reveal_mines=True)
    #                 sys.exit(1)
    #             else:
    #                 p = Point(guess_c, guess_r)
    #                 board.clear_around(p)
    #     elif user_choice == 2:
    #         raw = input("Location: ")
    #         guess_r = int(raw.split(",")[0])
    #         guess_c = int(raw.split(",")[1])
    #         p = Point(guess_r, guess_c)
    #         if 0 <= p.get_x() < board.size and 0 <= p.get_y() < board.size:
    #             board.mark_as_mine(p)
    #     else:
    #         print("Illegal choice")
