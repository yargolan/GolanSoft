
import json
import Draw
import codecs

cfg = {}


def get_max_times(all_numbers):
    max_index = "0"
    max_times = 0
    for item in sorted(all_numbers):
        if all_numbers[item] > max_times:
            max_times = all_numbers[item]
            max_index = item
    return max_index, max_times


def get_most_selected_numbers(raw_data, initial_draw):

    # Collect the draws from the minimal draw and on
    relevant_draws = []
    for draw in raw_data:
        current_draw = Draw.Draw(draw)
        if int(current_draw.get_id()) < initial_draw:
            break
        relevant_draws.append(current_draw)

    # extract the winning numbers from each (relevant) draw
    max_winning_numbers = {}
    for relevant_draw in relevant_draws:
        for number in relevant_draw.get_winning_numbers():
            str_num = str(number)
            if str_num not in max_winning_numbers:
                max_winning_numbers[str_num] = 0

            # Increment it
            max_winning_numbers[str_num] += 1

    # Get the most winning numbers
    most_winning_numbers = []
    for i in range(6):
        max_number, times = get_max_times(max_winning_numbers)
        most_winning_numbers.append(int(max_number))
        del max_winning_numbers[max_number]

    return sorted(most_winning_numbers)


def main():

    # Load the main configuration
    with open("config.json") as c:
        global cfg
        cfg = json.load(c)

    # Read the full draws data
    with codecs.open(cfg['results_file'], 'r', encoding='utf-8', errors='ignore') as csv_file:
        raw_data = csv_file.readlines()

    # Get the most selected numbers
    selected = get_most_selected_numbers(raw_data=raw_data[1:], initial_draw=cfg['initial_draw'])


    # Compare vs last 10 draws
    print("|Draw #|  Winning numbers  |     My numbers    | Hits|")
    print("+------+-------------------+-------------------+-----+")
    for draw in raw_data[1:20]:
        hits = 0
        d = Draw.Draw(draw)
        for selected_number in selected:
            if selected_number in d.get_winning_numbers():
                hits += 1

        w1 = " "
        for n in d.get_winning_numbers():
            if n < 10:
                w1 += "0" + str(n) + " "
            else:
                w1 += str(n) + " "
        w2 = " "
        for n in selected:
            if n < 10:
                w2 += "0" + str(n) + " "
            else:
                w2 += str(n) + " "
        print("| {} |{}|{}| {}/6 |".format(d.get_id(), w1, w2, hits))


if __name__ == '__main__':
    main()
