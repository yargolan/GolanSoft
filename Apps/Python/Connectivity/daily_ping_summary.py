#!/usr/bin/env python3

import os
import json
from datetime import datetime


data = {}



def load_config():

    here = os.path.dirname(os.path.realpath(__file__))

    config_file_full_path = f"{here}/config.json"

    with open(config_file_full_path) as config_file:
        config = json.load(config_file)

        data["log_file"]       = "/".join((config["root_dir"], config["pings_log_file"]))
        data["script_name"]    = config["script_name_daily"]
        data["log_file_daily"] = "/".join((config["root_dir"], config['log_file_daily']))



def get_today_date():
    return datetime.now().strftime("%Y/%m/%d")



def main():

    # Set the daily failed ping amount.
    daily_amount = 0

    # load configuration
    load_config()


    # Get today's date.
    today_date = get_today_date()


    # Read the "per minute" log file
    with open(data["log_file"], "r") as log_file:
        lines = log_file.readlines()
        for line in lines:
            date_in_log = line.split("_")[0]

            if date_in_log == today_date:
                daily_amount += 1


    # Write the failed amount to the log.
    with open(data["log_file_daily"], "a") as log:
        log.write(f"Daily failed pings for '{today_date}' : {daily_amount}\n")



if __name__ == "__main__":
    main()
