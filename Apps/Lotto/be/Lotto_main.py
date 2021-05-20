
import csv
import sys
import wget
from lotto_modules import AppConfig, Prints



app_config            = AppConfig.AppConfig()
winning_numbers       = {}
winning_strong_number = {}



def download_draws_results():
    try:
        wget.download(app_config.url, app_config.results_file)

        #
        # s = requests.Session()
        # s.proxies = {
        #     "http": "http://emea-chain.proxy.att.com:8080",
        #     "https": "http://emea-chain.proxy.att.com:8080",
        # }
        # 
        # proxies = {
        #     "http": "http://yg356h:G0lan12345@emea-chain.proxy.att.com:8080",
        #     "https": "http://yg356h:G0lan12345@emea-chain.proxy.att.com:8080",
        # }
        # data = requests.get(app_config.url)
        # 
        # with open(app_config.results_file, 'wb')as file:
        #     file.write(data.content)
    except OSError:
        Prints.exit_with_error("connection's timeout expired")

    # try:
    #     urllib.request.urlretrieve(url = app_config.url, filename = app_config.results_file)
    # except OSError as e:
    #     Prints.exit_with_error("Cannot download the file:\n" + str(e))



def parse_draw_results():

    # Read the draws results file
    with open(app_config.results_file) as c:
        csv_lines = list(csv.reader(c, delimiter = ',', quotechar = '|'))

    for line in csv_lines:

        current_draw_number = line[0]

        if int(current_draw_number) < app_config.initial_draw:
            break


        for index in range(2, 7):
            number = int(line[index])
            if number not in winning_numbers.keys():
                winning_numbers[number] = 0

            winning_numbers[number] += 1

        # Do the same for the 'strong number'
        number = int(line[8])
        if number not in winning_strong_number.keys():
            winning_strong_number[number] = 0

        winning_strong_number[number] += 1
    # pprint(winning_numbers)


def print_most_selected_numbers():

    # Get the most winnable numbers.
    most_winning_numbers = []

    while len(most_winning_numbers) < app_config.numbers_to_draw + 1:

        maximal = max(winning_numbers.keys(), key=(lambda k: winning_numbers[k]))

        most_winning_numbers.append(maximal)

        del winning_numbers[maximal]

    # Sort the winning numbers
    most_winning_numbers = sorted(most_winning_numbers)



    selected = []
    for option in range(0, app_config.numbers_to_draw + 1):
        the_list = []
        for j in range(0, app_config.numbers_to_draw + 1):
            if option != j:
                the_list.append(most_winning_numbers[j])
        selected.append(the_list)

    for i in range(0, len(selected)):
        print(selected[i])




def main(action):

    if action == "download":
        download_draws_results()
    elif action == "guess":

        # Parse the previous draws results.
        parse_draw_results()

        # Print the most selected numbers.
        print_most_selected_numbers()
    else:
        Prints.exit_with_error(f"The action '{action}' is invalid.")


    # # Print the numbers one should use.
    # print("")
    # print("Selected numbers are:")
    # for i in guessed_numbers:
    #     print(i)
    #
    # print("")
    # print(f"The 'strong' number is: {strong_number}")



if __name__ == '__main__':
    if len(sys.argv) != 2:
        Prints.exit_with_usage(sys.argv[0])
    else:
        main(sys.argv[1])




"""







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
"""
