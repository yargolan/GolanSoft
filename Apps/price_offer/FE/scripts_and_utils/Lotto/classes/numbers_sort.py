import json

from classes.number import Number



most_winning_numbers = []



def bubble_sort(numbers_list):



    while len(numbers_list) > 0:

        current_index   = 0
        current_maximal = Number(0, 0)

        for index in range(1, int(maximal_number) + 1):

            if str(index) not in numbers_list:
                continue

            current_index = index

            current_instances = numbers_list.get(str(index))

            if current_instances is None:
                continue


            if current_instances > current_maximal.get_instances():
                current_maximal = Number(index, current_instances)

        most_winning_numbers.append(current_index)
        numbers_list.pop(str(current_index))

    return most_winning_numbers
