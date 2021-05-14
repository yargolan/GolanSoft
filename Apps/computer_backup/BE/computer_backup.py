import os
import sys
import json
from shutil import copy2
from Checksums import get_checksum


def run_backup(file):

    # Read the profile file
    with open(file) as pf:
        validated_file = json.load(pf)

    # Get the target root folder
    root_target_folder = validated_file['target_folder']

    # Verify that the target root folder exists.
    os.makedirs(root_target_folder, exist_ok = True)

    # Get the list of items to backup.
    items = validated_file['items']

    for item in items:
        print(f"Backing up item: {item}")
        backup_item(item, root_target_folder)
        print("ok.\n")



def backup_item(source_item, target_folder):

    if os.path.isfile(source_item):
        target_file_full_path = "/".join([target_folder, source_item])
        target_file_full_path = target_file_full_path.replace("//", "/")

        if os.path.isfile(target_file_full_path):

            # Check sizes
            source_file_size = os.path.getsize(source_item)
            target_file_size = os.path.getsize(target_file_full_path)
            if source_file_size != target_file_size:
                copy2(source_item, target_file_full_path)
                return

            # Check checksums.
            source_checksum = get_checksum(source_item)
            target_checksum = get_checksum(target_file_full_path)
            if source_checksum != target_checksum:
                copy2(source_item, target_file_full_path)
                return
    else:
        #backup_item(source_item, target_file_full_path)



def print_usage():
    print()
    print(f"Usage: {sys.argv[0]}  <profile file>")
    print()
    sys.exit(2)



if __name__ == '__main__':
    if len(sys.argv) == 1:
        profile = input("Insert the profile file full path: ")
    else:
        profile_file = sys.argv[1]
        if profile_file == "-h" or profile_file == "--h":
            print_usage()
        elif profile_file == "-help" or profile_file == "--help":
            print_usage()
        else:
            print("Profile file:", profile_file)

        # Verify that the file exists.
        if not os.path.isfile(profile_file):
            print(f"The profile file provided ({profile_file}) is invalid.")
            print_usage()

        # Run the backup
        run_backup(profile_file)

