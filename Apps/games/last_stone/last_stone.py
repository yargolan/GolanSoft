
from random import randint

# Hard coded info.
PLAYER_USER           = 1
PLAYER_COMPUTER       = 2
INITIAL_STONES_AMOUNT = 30


def generate_random(max_num):
    return randint(1, max_num)



def main(stones_amount):

    # Set the beginner.
    current_player = generate_random(2)


    while stones_amount > 0:

        print(f"There are {stones_amount} left.")

        if current_player == 1:
            play_turn_user()
            current_player += 1
        else:
            play_turn_computer()
            current_player -= 1







    print()

    if current_player == 1:
        print("You won !")
    else:
        print("Computer won !")



def play_turn_computer():
    return generate_random(3)






# Main
if __name__ == "__main__":
    main(INITIAL_STONES_AMOUNT)








"""

def generate_random():
    return randint(1, 3)


def main():
    stones = WHITE_STONES_AMOUNT

    while stones > 0:

        # Computer's turn.
        print()
        computer_amount = generate_random()
        print(f"Computer took {computer_amount} stones.")

        stones -= computer_amount
        print(f"There are {stones} left.")

        user_amount = int(input("How many will you take (1..3) ? "))

        if 0 < user_amount < 4:
            if user_amount <= stones:
                stones -= user_amount
                print(f"There are {stones} left.")
            else:
                print(f"You cannot take more stones ({user_amount}) than there are ({stones})")

"""

