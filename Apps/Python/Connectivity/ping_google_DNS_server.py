#!/usr/bin/env python3

import os
import sys
import json
import time
import subprocess
from pathlib import Path
from datetime import datetime


# Config data for the application
data = {}


def main():

    # load configuration
    load_config()


    # Verify that the script is not running yet
    is_running = is_already_running()
    if is_running:
        sys.exit(0)


    # Ping Google's DNS server.
    result = ping_server()

    if not result:

        retries = 0

        while not result:

            # Increment the amount of retries.
            retries += 1

            # Log the failure
            log_failure(retries)

            # Wait 2 seconds ...
            time.sleep(2)

            # Retry to ping it
            result = ping_server()


def load_config():

    here = os.path.dirname(os.path.realpath(__file__))

    config_file_full_path = f"{here}/config.json"


    with open(config_file_full_path) as config_file:
        config = json.load(config_file)
        data["script_name"]    = config["script_name_per_minute"]
        data["google_server"]  = config["google_server"]
        data["pings_log_file"] = f"{config['root_dir']}/{config['pings_log_file']}"


def is_already_running():

    # Get the current script's pid.
    pid = str(os.getpid())

    # Get the full list of process IDs for scripts with the same name.
    command = f"ps -ef | grep -v grep | grep {data['script_name']}"
    raw_processes = list(os.popen(command))

    filtered_list = []

    for raw_process in raw_processes:
        process_id = str(raw_process).split()[1]
        if process_id != pid:
            filtered_list.append(process_id)


    # Return the amount of process IDs
    return len(filtered_list) > 0


def ping_server():

    # Set the ping command.
    ping_command = f"/sbin/ping -c 1 {data['google_server']}"

    # Set the shell command wrapper.
    process = subprocess.Popen(ping_command, stdout = subprocess.PIPE, stderr = subprocess.DEVNULL, shell = True)

    # Launch the shell command:
    process.communicate()

    # Get the command's exit status.
    exit_status = process.returncode

    return exit_status == 0


def get_last_modified(log_file):

    if not os.path.isfile(log_file):
        Path(log_file).touch()

    raw_file_date = time.ctime(os.path.getmtime(log_file))

    file_date_array = raw_file_date.split()

    # Add a 'zero' in case that the date is lower than 10
    dom = file_date_array[2]

    day_of_month = int(dom)

    if day_of_month < 10:
        dom = "0" + dom

    file_date = f"{file_date_array[4]}/{file_date_array[1]}/{dom}"

    # Convert the date from string to number
    file_date = file_date.replace("Jan", "1")
    file_date = file_date.replace("Feb", "2")
    file_date = file_date.replace("Mar", "3")
    file_date = file_date.replace("Apr", "4")
    file_date = file_date.replace("May", "5")
    file_date = file_date.replace("Jun", "6")
    file_date = file_date.replace("Jul", "7")
    file_date = file_date.replace("Aug", "8")
    file_date = file_date.replace("Sep", "9")
    file_date = file_date.replace("Oct", "10")
    file_date = file_date.replace("Nov", "11")
    file_date = file_date.replace("Dec", "12")

    return file_date


def log_failure(retries):

    log_file = data["pings_log_file"]

    # "Touch" the file, if absent.
    if not os.path.exists(log_file):
        open(log_file, 'a').close()
        os.chmod(log_file, 0o1232)  # 0666 in decimal

    # Get the formatted 'last modified' date for the log file.
    last_modified = get_last_modified(log_file)

    # Get the current date
    current_date = datetime.now().strftime("%Y/%m/%d")

    with open(log_file, "a") as log:
        if not current_date == last_modified:
            log.write("-" * 60)
            log.write("\n")

        now = datetime.now().strftime("%Y/%m/%d_%H:%M:%S")
        log.write(f"{now} - Try #{retries}\n")


if __name__ == "__main__":
    main()
