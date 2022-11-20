
class Draw:
    def __init__(self, raw_data):
        raw_data = raw_data
        split_text = raw_data.split(",")
        self.draw_id = split_text[0]
        self.draw_date = split_text[1]
        self.numbers = split_text[2:8]
        self.strong_number = split_text[9]

        # Convert the winning numbers to int.
        self.winning_numbers = []
        for num in self.numbers:
            if num.startswith("0"):
                num = num.replace("0", "")
            self.winning_numbers.append(int(num))
        del self.numbers


    def get_id(self):
        return self.draw_id


    def get_date(self):
        return self.draw_date


    def get_winning_numbers(self):
        return self.winning_numbers


    def get_strong_number(self):
        return self.strong_number
