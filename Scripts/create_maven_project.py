#!/usr/bin/python3 -u

import os
from re import A
import sys
from this import d


def create_project(data):

    project_dir = os.sep.join([data['root_dir'], data['artifact_id']])
    
    # Verify that the file system tree exists.
    src_path = os.sep.join([project_dir, "src/main/java", data['group_id'].lower().replace("\\.", os.sep)])
    os.makedirs(src_path, exist_ok=True)

    test_path = os.sep.join([project_dir, "src/test/java", data['group_id'].lower().replace("\\.", os.sep)])
    os.makedirs(test_path, exist_ok=True)


def get_user_data():
    data = {}
    needed_arguments = ["root_dir", "artifact_id", "group_id", "version"]

    if len(sys.argv) == 5:
        for arg in sys.argv[1:]:
            if "=" in arg:
                key, value = arg.split("=")
                if key in needed_arguments:
                    data[key] = value
            else:
                print("Invalid argument -", arg)
                continue
    else:
        data['version']     = input("Enter the version     : ")
        data['root_dir']    = input("Enter the root dir    : ")
        data['group_id']    = input("Enter the group ID    : ")
        data['artifact_id'] = input("Enter the artifact ID : ")

    # Verify the data
    for arg in needed_arguments:
        if not arg in data:
            print("Missing argument: ", arg)
            sys.exit(1)

    return data


if __name__ == '__main__':
    data = get_user_data()
    create_project(data)
