#!/usr/bin/env python3

import csv
import sys
import json

from classes.draw import Draw
from classes.number import Number


data            = {}
previous_draws  = []
guessed_numbers = []


def print_usage(name):
    message = f"Usage: {name} [--action] Action\n"
    message += "Action can be either:\n"
    message += "\t-  parse_results: Update the local DB with a new draw results.\n"
    message += "\t-  guess_numbers: Try to figure out which are the next winning numbers.\n"
    sys.exit(message)



def parse_arguments(args):

    arguments = {}

    if len(args) != 3:
        print_usage(args[0])

    if args[1] == "-a" or args[1] == "--action":
        arguments["action"] = args[2]
    else:
        print_usage(args[0])

    return arguments



def parse_results():

    try:
        with open("lotto_draws.csv", "r") as results_csv_file:
            csv_lines = list(csv.reader(results_csv_file, delimiter = ',', quotechar = '|'))
    except IOError as e:
        sys.exit("Cannot read the 'lotto_draws.csv' file: \n" + str(e))

    for line in csv_lines:

        print(line[0])

        # Get the draw id.
        draw_id = line[0]

        # Get the draw date.
        draw_date = line[1]

        # Ignore draws before minimal year (from the configuration)
        if int(str(draw_date).split("/")[-1]) >= 2000:

            # get the winning numbers
            numbers = line[2:7]

            current_draw = Draw(draw_id, draw_date, numbers)

            # Set the winning number into the DB.
            previous_draws.append(current_draw)

    # Dump the parsed data into a file.
    with open(data['lotto_results_file'], "w") as csv:

        for draw in previous_draws:

            line = f"{draw.get_draw_id()}, {draw.get_draw_date()}, {draw.get_draw_numbers()}"

            csv.write(line + "\n")




def init_app():

    # Load the app configuration
    with open("./app_config.json") as config_file:
        config = json.load(config_file)
        data["starting_year"]      = config["starting_year"]
        data["lotto_web_site_url"] = config["lotto_web_site_url"]
        data["lotto_results_file"] = config["lotto_results_file"]



def guess_numbers():
    pass



def main():

    # init the application
    init_app()

    # parse arguments
    args = parse_arguments(sys.argv)

    action = args.get("action")

    if action == "parse_results":
        parse_results()
    elif action == "guess_numbers":
        guess_numbers()
    else:
        sys.exit(f"Cannot handle {action}.")



if __name__ == "__main__":
    main()


"""

# def guess_numbers():
# 
#     for current_number in range (50):
#         print(f"{current_number} - {winning_numbers[current_number]} times.")



#
    #
    #
    #     for i in range(6):
    #
    #         current_winning_number = winning_numbers[current_number]
    #         current_guessed_number = guessed_numbers[i]
    #
    #         if current_winning_number > current_guessed_number:
    #             j = 5
    #             while j > 0:
    #                 guessed_numbers[j] = guessed_numbers[j-1]
    #                 j -= 1
    #
    #             guessed_numbers[0] = current_number
    #     print("Guessed numbers :", guessed_numbers)
    #

"""