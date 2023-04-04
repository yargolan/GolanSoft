
from Lotto_draws import LottoDraws


def main():
    lotto_draws = LottoDraws()
    lotto_draws.read_draws_file("Lotto.csv")
    # max_numbers, strongest_number = lotto_draws.get_most_selected_number(2510)
    # print(max_numbers)
    # print("Strong number :", strongest_number)

    lotto_draws.show_hist_numbers(3000)


if __name__ == '__main__':
    main()
