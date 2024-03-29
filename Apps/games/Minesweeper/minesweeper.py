import sys
from BoardOld import BoardOld
from Point import Point



def get_user_choice():

    choice = -1

    while choice < 0 or choice > 4:
        print()
        print("Enter your choice:")
        print("0. Give up")
        print("1. Guess a spot")
        print("2. Mark as mine")
        print("3. Clear cell")
        print("4. Show the board")
        raw = input("?: ")
        if raw.__contains__(" "):
            raw = raw.replace(" ", "")
        choice = int(raw)
    return choice


def main():

    # Set the board
    play_board = BoardOld()
    original_board = play_board

    board_solved = False

    while not board_solved:

        user_choice = get_user_choice()

        if user_choice == 0:
            play_board.print_it(reveal_mines=True)
            sys.exit(0)
        elif user_choice == 1:
            raw = input("Location: ")
            raw = raw.replace(" ", "")
            col = int(raw.split(",")[0])
            row = int(raw.split(",")[1])
            user_point = Point(row, col)
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
            raw = input("Location: ")
            raw = raw.replace(" ", "")
            user_point = Point(int(raw.split(",")[0]), int(raw.split(",")[1]))
            play_board.unset_mine_at(user_point)
            play_board.print_it(False)
        elif user_choice == 4:
            play_board.print_it(True)
        else:
            board_solved = False


if __name__ == '__main__':

    main()
