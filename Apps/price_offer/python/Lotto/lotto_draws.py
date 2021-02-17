import csv
import json
import os
import sys
from lotto_modules import Prints





def get_user_action():

    if len(sys.argv) == 2:
        action = sys.argv[1]
        return action.lower()
    else:
        Prints.print_usage(sys.argv[0])



def load_draw_results_file():
    default_path = "/Users/yg356h/Downloads/Lotto.csv"
    path = input("\nFull path for the results file (Enter = /Users/yg356h/Downloads/Lotto.csv): ")
    if path is None or path == "":
        path = default_path


    if not os.path.isfile(path):
        Prints.exit_with_error(f"The file '{path}' doesn't exist.")


    with open(path) as results_csv_file:
        results_lines = list(csv.reader(results_csv_file, delimiter = ','))


    minimal_draw_number = app_config['minimal_draw_number']
    filtered_draws_file = app_config['filtered_draws_file']

    draws   = []
    draw_id = 9999
    while len(results_lines) > 0 and draw_id > minimal_draw_number:
        current_line = results_lines.pop(0)

        # Get the draw id.
        draw_id = int(current_line[0])

        # Get the draw date.
        draw_date = current_line[1]

        # Get the draw numbers.
        draw_numbers = current_line[2:8]

        # Add a zero for numbers lower than 10
        for option in range(0, len(draw_numbers)):
            if int(draw_numbers[option]) < 10 and "0" not in draw_numbers[option]:
                draw_numbers[option] = "0" + draw_numbers[option]

        # Get the 'Strong number'
        strong_number = current_line[8]

        # Gather the all into an object
        current_draw = {"id": draw_id, "date": draw_date, "numbers": draw_numbers, "strong_number": strong_number}

        draws.append(current_draw)

    # Dump into a file
    with open(filtered_draws_file, 'w') as outfile:
        json.dump(draws, outfile, indent=2)



def guess_next_numbers():

    # Read the filtered draws file.
    filtered_draws_file = app_config['filtered_draws_file']
    with open(filtered_draws_file) as draw_file:
        draws_list = json.load(draw_file)


    # Sum how many times each number won.
    previous_draws = {}
    for draw in draws_list:
        for number in draw['numbers']:
            if number not in previous_draws:
                previous_draws[number] = 0
            previous_draws[number] += 1


    # Get the most winnable numbers.
    most_winning_numbers = []

    while len(most_winning_numbers) < 7:

        maximal = max(previous_draws.keys(), key=(lambda k: previous_draws[k]))

        del previous_draws[maximal]
        most_winning_numbers.append(maximal)

    # Sort the winning numbers
    most_winning_numbers = sorted(most_winning_numbers)
    return most_winning_numbers



def print_guessed_numbers(numbers):

    selected = []

    for option in range(0, 7):

        the_list = []

        for j in range(0, 7):

            if option != j:
                the_list.append(numbers[j])

        selected.append(the_list)

    return selected



if __name__ == '__main__':

    with open("config.json") as config:
        app_config = json.load(config)


    # Check what action does the user wishes to perform
    user_action = get_user_action()

    if user_action == 9:
        sys.exit(0)
    elif user_action == "load":
        load_draw_results_file()
    elif user_action == "guess":

        winning_numbers = guess_next_numbers()

        # print the winning numbers.
        most_selected_number = print_guessed_numbers(winning_numbers)

        for i in range(0, len(most_selected_number)):
            current_option = most_selected_number[i]
            print(current_option)

    else:
        Prints.exit_with_error(f"Invalid option '{user_action}' received.")
