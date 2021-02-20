
import json
import pymongo
from pymongo import MongoClient


def print_it(something):

    print("")
    print("+--------------------------------------------")
    print("Type:")
    print(type(something))

    print("")
    print("Value:")
    print(something)
    print("+--------------------------------------------")
    print("")


def main():

    # Read the DB file
    with open("data.json") as raw_db:
        json_db = json.load(raw_db)

    # print_it(json_db)

    # Convert the raw DB into the mongo
    my_client = MongoClient("mongodb://localhost:27017")
    db = my_client.my_db
    db_makes = db.makes

    for item in json_db:
        make_id = db_makes.insert_one(item).inserted_id
        print(make_id)


    #
    # v1 = {"make": "Jeep", "model": "Rubicon"}
    #
    # v_id = vehicles.insert_one(v1).inserted_id
    #
    # vehicles.count_documents({})




if __name__ == '__main__':
    main()
