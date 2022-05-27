
import json


class AppConfig:
    """Configuration for the application"""
    with open("config.json") as c:
        config = json.load(c)

    url             = config['draw_result_url']
    minimal_year    = config['minimal_year']
    results_file    = config['draw_results_file_name']
    initial_draw    = config['initial_draw_number']
    numbers_to_draw = config['amount_of_numbers_to_draw']