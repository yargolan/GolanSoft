
import csv
import numpy
from scipy import stats
import matplotlib.pyplot as plt
from statistics import mode


class LottoDraws:

    def __init__(self):
        self.draws_file = None


    def read_draws_file(self, draws_file):
        self.draws_file = draws_file


    def get_results_file(self):
        return self.draws_file


    def get_all_numbers(self, minimal_draw_number):
        all_drawn_numbers = []
        all_drawn_strong_numbers = []

        with open(self.get_results_file()) as results_file:
            reader = csv.reader(results_file)
            for row in reader:
                draw_number = int(row[0])
                if draw_number < minimal_draw_number:
                    break

                # Regular numbers
                current_draw_numbers = row[2:8]
                for number in current_draw_numbers:
                    number = number.replace("0", "")
                    all_drawn_numbers.append(int(number))

                # "Strong" number
                all_drawn_strong_numbers.append(row[8])

        return all_drawn_numbers, all_drawn_strong_numbers


    def get_most_selected_number(self, minimal_draw_number):

        most_selected_numbers = []

        all_drawn_numbers, all_drawn_strong_numbers = self.get_all_numbers(minimal_draw_number)

        for number in range(6):
            most_selected_number = mode(all_drawn_numbers)
            most_selected_numbers.append(most_selected_number)
            all_drawn_numbers = [i for i in all_drawn_numbers if i != most_selected_number]

        most_selected_strong_number = mode(all_drawn_strong_numbers)

        return sorted(most_selected_numbers), most_selected_strong_number


    def show_hist_numbers(self, minimal_draw_number):
        all_drawn_numbers = self.get_all_numbers(minimal_draw_number)
        plt.hist(all_drawn_numbers, 40)
        plt.show()
