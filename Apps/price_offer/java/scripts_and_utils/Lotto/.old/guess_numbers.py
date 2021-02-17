#!/usr/bin/env python3

import csv
import json



data = {}



def init_app():

    # Load the app configuration
    with open("./app_config.json") as config_file:
        config = json.load(config_file)
        data["results_file"] = config["lotto_results_file"]



def guess_number():

    with open(data["results_file"], "r") as results_file:

        csv_lines = list(csv.reader(results_file, delimiter = ','))

        guessed_number = []

        # init the array
        for i in range(0, 46):
            guessed_number.append(0)

        for line in csv_lines:

            current_numbers = line[1:7]

            for number in current_numbers:
                guessed_number[int(number)] += 1

        # Print it
        print(guessed_number[1:len(guessed_number)])




def main():

    # init the application
    init_app()

    # Try to guess the next numbers
    guess_number()



if __name__ == '__main__':
    main()





'''
import os
import re
import sys
import csv
from pathlib import Path
import json

import requests


data = {}





def get_weekly_numbers():

    # Verify that there are no proxy server defined
    proxies = {"http": "", "https": ""}

    # Get the current winning numbers page.
    r = requests.get(data["lotto_web_site_url"], proxies=proxies)

    # Verify that the page was loaded correctly.
    if r.status_code != 200:
        sys.exit(f"Cannot get the page. Error = {r.status_code}")

    # get the draw number
    draw_number = re.findall("=\\d\\d\\d\\d", r.text)[0]
    draw_number = str(draw_number).replace("=", "").replace("'", "")

    # Get the relevant part of the page
    parse = re.findall(r"<div>\d+</div>", r.text)
    raw_winning_numbers = parse[0:6]


    numbers_list = []
    for part in raw_winning_numbers:
        current_number = part.replace("<", " ").replace(">", " ").split()[1]
        numbers_list.append(current_number)

    return draw_number, numbers_list



def main():

    # Init the application
    init_app()


    # get the draw number and the winning numbers
    draw_number, winning_numbers = get_weekly_numbers()


    # Read the previous draw results file
    results_file = data["lotto_results_file"]

    with open(results_file, 'r', newline = '') as results:
        csv_lines = list(csv.reader(results, delimiter = ',', quotechar = '|'))

    for line in csv_lines:
        current_draw_number = line[0]
        if current_draw_number != draw_number:
            with open(results_file, "a") as the_results_file:
                the_results_file.seek(0)
                new_line = draw_number + "," + ",".join(winning_numbers)
                the_results_file.write(new_line + "\n")



'''
