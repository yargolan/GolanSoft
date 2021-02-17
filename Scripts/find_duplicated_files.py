
import os
import sys
import hashlib



def calculate_checksum(filename, block_size=65536):

    md5_hash = hashlib.md5()

    with open(filename, "rb") as file:
        for block in iter(lambda: file.read(block_size), b""):
            md5_hash.update(block)
    return md5_hash.hexdigest()



def print_duplications(data):

    for entry in data:

        if len(data[entry]) > 1:
            for place in data[entry]:
                print(place)

            print("Checksum", entry)
            print("")



def get_root_dir(arguments):

    # Check if there is a root dir as a parameter
    if len(arguments) == 2:
        root_dir = arguments[1]
    else:
        root_dir = input("Insert the root folder: ")

    return root_dir



def main(args):

    # Get the root dir
    root_dir = get_root_dir(args)


    # Validate the root dir
    if not os.path.isdir(root_dir):
        sys.exit(f"The given root folder ({root_dir}) is invalid.")


    # Read the files recursively and check for duplication
    duplications = search_for_duplications(starting_point=root_dir)


    # Print the duplicated files.
    print_duplications(duplications)



def search_for_duplications(starting_point):

    data = {}

    # r=root, d=directories, f = files
    for r, d, f in os.walk(starting_point):
        for file in f:
            current_file = os.path.join(r, file)

            # Verify files only
            if os.path.isdir(current_file):
                continue

            file_checksum = calculate_checksum(current_file)

            if file_checksum in data:
                places = data[file_checksum]
                places.append(current_file)
            else:
                places = [current_file]
                data[file_checksum] = places

    return data



if __name__ == '__main__':
    main(sys.argv)
