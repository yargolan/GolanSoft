
import csv
import json


app_config       = {}
draw_results     = []



def init_app():
    with open("config.json") as config_file:
        global app_config
        config = json.load(config_file)
        for key in config.keys():
            app_config[key] = config[key]



def read_results_file():
    with open(app_config['draws_results_url']) as results:
        csv_lines = list(csv.reader(results, delimiter = ',', quotechar = '|'))

    for line in csv_lines:

        # Verify that the current draw is relevant.
        current_draw_date = line[1]
        current_line_year = int(current_draw_date.split("/")[-1])
        if current_line_year < app_config['minimal_year']:
            break

        current_draw_numbers = line[2:8]

        # Insert current draw results into the main dict.
        current_draw = {"id": line[0], "numbers": current_draw_numbers, "strong_number": line[8]}

        draw_results.append(current_draw)



def get_max_number(numbers_list):

    maximal_value = 0
    maximal_index = 0

    for key in numbers_list.keys():

        current_number = numbers_list[key]

        if current_number > maximal_value:
            maximal_value = current_number
            maximal_index = key

    return maximal_index



def calculate_most_selected_numbers():

    results = []
    full_list = {}

    for draw in draw_results:

        for number in draw['numbers']:

            if number not in full_list:
                full_list[number] = 0

            full_list[number] += 1


    for index in range(0, 6):

        max_index = get_max_number(full_list)

        results.append(max_index)

        del full_list[str(max_index)]

    return sorted(results)



def guess_strong_number():

    strong_number_dict = {}

    for draw in draw_results:

        current_strong_number = draw['strong_number']

        if current_strong_number not in strong_number_dict:
            strong_number_dict[current_strong_number] = 0

        strong_number_dict[current_strong_number] += 1


    max_instances     = 0
    max_strong_number = 0


    for number in strong_number_dict.keys():

        current_number_instances = int(strong_number_dict[number])

        if current_number_instances > max_instances:
            max_instances = current_number_instances
            max_strong_number = number

    return max_strong_number



if __name__ == '__main__':

    # Read the application config file.
    init_app()


    # Read the draws results file
    read_results_file()


    # Calculate the most selected numbers.
    guessed_numbers = calculate_most_selected_numbers()


    # Guess the 'strong' number
    strong_number = guess_strong_number()


    # Print the numbers one should use.
    print("")
    print("Selected numbers are:")
    for i in guessed_numbers:
        print(i)

    print("")
    print(f"The 'strong' number is: {strong_number}")
