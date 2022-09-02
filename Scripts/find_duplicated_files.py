
import os
import sys
import hashlib


data = {}


def calculate_checksum(filename, block_size=65536):
    md5_hash = hashlib.md5()
    with open(filename, "rb") as file:
        for block in iter(lambda: file.read(block_size), b""):
            md5_hash.update(block)
    return md5_hash.hexdigest()


def main(root_dir):

    # Validate the root dir
    if not os.path.isdir(root_dir):
        sys.exit(f"The given root folder ({root_dir}) is invalid.")

    # r=root, d=directories, f = files
    print("Searching for files...")
    for r, d, f in os.walk(root_dir, followlinks=False):
        for file in f:
            current_file = os.path.join(r, file)

            # Verify files only
            if os.path.isdir(current_file):
                continue

            print(".", end="")
            file_checksum = calculate_checksum(current_file)

            if file_checksum in data:
                places = data[file_checksum]
                places.append(current_file)
            else:
                places = [current_file]
                data[file_checksum] = places
    print()


def print_report():
    duplications = {}
    for item in data:
        if len(data[item]) > 1:
            duplications[item] = data[item]

    if duplications:
        for item in duplications:
            print(f"{item} : {duplications[item]}")
    else:
        print("No duplications found.")


if __name__ == '__main__':
    if len(sys.argv) == 2:
        main(sys.argv[1])
        print_report()
    else:
        print("Usage:\n" + sys.argv[0] + "  <Root dir>")
