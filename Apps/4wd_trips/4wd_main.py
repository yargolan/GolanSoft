
import json
import pymongo
from db import init_db



config = {}
debug_mode = False



def init_database():

    mongo_client = pymongo.MongoClient("mongodb://localhost:27017/")

    db_name = "4wd_trips_database"

    my_db = mongo_client[db_name]

    if debug_mode:
        mongo_client.drop_database(db_name)


    db_list = mongo_client.list_database_names()
    if db_name in db_list:
        if debug_mode:
            print("The database exists.")
    else:
        if debug_mode:
            print(f"Create the database '{db_name}'.")
        init_db.set_initial_data(mongo_client)





        # col_routes       = my_db['Routes']
        # col_participants = my_db['Participants']
        #
        # data = {"name": "Peter", "address": "Low street 27"}
        #
        # x = col_participants.insert_one(data)
        #
        # print(x.inserted_id)
        #
        # if debug_mode:
        #     print(my_db.list_collection_names())
        #

def main():

    # init the application.
    with open('config.json') as c:
        global config
        config = json.load(c)

    global debug_mode
    debug_mode = True if config.get('debug').lower() == "true" else False

    # init the database.
    init_database()



if __name__ == '__main__':
    main()







"""
Commands for saveL

# print the list of DBs.
print(mongo_client.list_database_names())

# Delete a DB
mongo_client.drop_table('table_name)
"""