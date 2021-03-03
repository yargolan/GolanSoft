
import json
from pprint import pprint


def set_initial_data(client):
    with open("db/vehicles.json") as v:
        vehicles = json.load(v)

    db_name = "4wd_trips_database"

    my_db = client[db_name]

    col_vehicles = my_db['Vehicles']

    for make in vehicles.get('make'):
        col_vehicles.

        # data = {"name": "Peter", "address": "Low street 27"}
        #
        # x = col_participants.insert_one(data)
