
import sys
import pymongo
import requests
import traceback



def log_traceback(ex):
    tb_lines = traceback.format_exception(ex.__class__, ex, ex.__traceback__)
    tb_text = ''.join(tb_lines)
    print(tb_text)
    sys.exit("The docker cannot be reached.")



def verify_docker():
    proxies = {
        "http": None,
        "https": None,
        "no_proxy": "localhost"
    }

    try:
        r = requests.get("http://localhost:27017", proxies = proxies)
        if r.status_code != 200:
            sys.exit("The docker cannot be reached.")
    except Exception as e:
        log_traceback(e)



def validate_db_structure():

    mongo_client = pymongo.MongoClient("mongodb://localhost:27017/")

    camping_db = mongo_client["camping"]

    dbs_list = mongo_client.list_database_names()

    print(mongo_client.list_database_names())

    if "vehicles" not in dbs_list:

        # Create the collection
        vehicles_collection = camping_db["vehicles"]
    # print(camping_db.list_collection_names())



def main():

    # Verify that the docker is up and running.
    verify_docker()


    # Validate the DB structure.
    # validate_db_structure()




if __name__ == '__main__':
    main()
