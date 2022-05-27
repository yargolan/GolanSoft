#!/usr/bin/env python3

import re
import csv
import sys
import json
from classes.draw import Draw
from classes.number import Number


data            = {}
previous_draws  = []
guessed_numbers = {}


def init_app():

    # Load the app configuration
    with open("./app_config.json") as config_file:
        config = json.load(config_file)
        data["starting_year"]          = config["starting_year"]
        data["maximal_number"]         = config["maximal_number"]
        data["numbers_to_display"]     = config["numbers_to_display"]
        data["lotto_web_site_url"]     = config["lotto_web_site_url"]
        data["previous_draws_results"] = config["previous_draws_results"]
        data["relevant_draws_results"] = config["relevant_draws_results"]


def guess_numbers():

    # read the previous winning numbers file.
    try:
        with open(data.get("relevant_draws_results", "r")) as results_csv_file:
            csv_lines = list(csv.reader(results_csv_file, delimiter = ',', quotechar = '|'))
    except IOError as e:
        sys.exit("Cannot read the 'lotto_draws.csv' file: \n" + str(e))


    for line in csv_lines:

        # Generate a 'Number' object from each number.
        line_numbers = line[2:8]

        for number in line_numbers:

            # Strip the '0' prefix
            if number.startswith("0"):
                m = re.search(r"(0)(\d)", number)
                number = m.group(2)

            amount = 0

            # Override the amount if the number is already in the list.
            if number in guessed_numbers.keys():
                amount = guessed_numbers.get(number)

            # Increment the amount of instances for the given number.
            amount += 1

            # Store the number in the list.
            guessed_numbers[number] = amount

    sorted_numbers = get_most_winning_numbers(guessed_numbers)

    for n in sorted_numbers:
        print(f"The number {n.get_value()} has {n.instances} instances")



def get_most_winning_numbers(numbers_list):

    most_winning_numbers = []

    while len(most_winning_numbers) < data["numbers_to_display"]:

        maximal_number = Number(0, 0)

        for key in numbers_list.keys():

            # Get the instances amount for this key
            instances = numbers_list.get(key)

            if instances > maximal_number.get_instances():
                maximal_number = Number(key, instances)

        # Add the current maximal number to the list
        most_winning_numbers.append(maximal_number)

        # Remove it from the list
        numbers_list.pop(maximal_number.get_value())

    return most_winning_numbers


def parse_results():

    # Read the full results file.
    try:
        with open(data.get("previous_draws_results"), "r") as results_csv_file:
            csv_lines = list(csv.reader(results_csv_file, delimiter = ',', quotechar = '|'))
    except IOError as e:
        sys.exit("Cannot read the 'lotto_draws.csv' file: \n" + str(e))


    for line in csv_lines:

        # Get the draw date.
        draw_date = line[1]

        # Ignore draws before minimal year (from the configuration).
        starting_year = data.get("starting_year")
        current_year  = int(str(draw_date).split("/")[-1])
        if current_year >= starting_year:

            # Get the draw id.
            draw_id = line[0]

            # Get the winning numbers for this draw.
            winning_numbers = line[2:8]

            # Set the current draw.
            current_draw = Draw(draw_id, draw_date, winning_numbers)

            # Add the current draw into the list of draws.
            previous_draws.append(current_draw)



def parse_arguments(user_args):

    arguments = {}

    if len(user_args) != 3:
        print_usage(user_args[0])

    if user_args[1] == "-a" or user_args[1] == "--action":
        arguments["action"] = user_args[2]
    else:
        print_usage(user_args[0])

    return arguments



def print_usage(name):
    message = f"Usage: {name} [--action] Action\n"
    message += "Action can be either:\n"
    message += "\t-  parse_results: Update the local DB with a new draw results.\n"
    message += "\t-  guess_numbers: Try to figure out which are the next winning numbers.\n"
    sys.exit(message)



def create_filtered_draws_file():

    with open(data.get("relevant_draws_results"), "w+") as file:

        for draw in previous_draws:

            line = f"{draw.get_draw_id()},{draw.get_draw_date()}"

            for number in draw.get_draw_numbers():
                line += "," + number

            file.write(f"{line}\n")



if __name__ == "__main__":

    # init the application
    init_app()


    # parse arguments
    args = parse_arguments(sys.argv)
    action = args.get("action")
    if action == "parse_results":

        # Parse the results.
        parse_results()

        # Dump the relevant results into a file.
        create_filtered_draws_file()

    elif action == "guess_numbers":
        guess_numbers()
    else:
        sys.exit(f"Cannot handle {action}.")

