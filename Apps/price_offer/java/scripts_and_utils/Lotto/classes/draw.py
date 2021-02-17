class Draw:

    def __init__(self, draw_id, draw_date, numbers):
        self.draw_id   = draw_id
        self.numbers   = numbers
        self.draw_date = draw_date


    def get_draw_id(self):
        return self.draw_id


    def get_draw_date(self):
        return self.draw_date


    def get_draw_numbers(self):
        return self.numbers
