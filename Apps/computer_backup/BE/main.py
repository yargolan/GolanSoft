
import os
import sys
import hashlib
from pathlib import Path
from shutil import copy2



def tree_walk(source, target):

    if os.path.isdir(source):

        sub_items = os.listdir(source)

        for sub_item in sub_items:
            sub_item_full_path = "/".join([source, sub_item])
            tree_walk(sub_item_full_path, target)


    elif os.path.isfile(source):
        
        target_item = "/".join([target, source])
        
        if os.path.isfile(target_item):

            # Check sizes
            source_file_size = os.path.getsize(source)
            target_file_size = os.path.getsize(target_item)
            if source_file_size != target_file_size:
                backup_file(source, target_item)
                return
            
            # Check checksums.
            source_checksum = get_hash(source)
            target_checksum = get_hash(target_item)
            if source_checksum != target_checksum:
                backup_file(source, target_item)
                return
        else:
            backup_file(source, target_item)

    else:
        print(f"Unknown item - '{source}'")



def get_hash(path, hash_type='md5'):

    func = getattr(hashlib, hash_type)()

    f = os.open(path, (os.O_RDWR | os.O_BINARY))

    for block in iter(lambda: os.read(f, 2048*func.block_size), b''):
        func.update(block)
    os.close(f)

    return func.hexdigest()



def backup_file(s, t):

    t = t.replace("//", "/")

    # Get the target file's parent folder
    path = Path(t)
    parent_folder = path.parent

    # Verify that the target folder exists.
    os.makedirs(parent_folder, exist_ok = True)

    # Copy the source file into the target folder.
    copy2(s, t)


def main(source_folder, target_folder):

    # Verify the source item.
    if not os.path.isdir(source_folder) and not os.path.isfile(source_folder):
        sys.exit(f"Invalid source item - '{source_folder}'.")

    tree_walk(source_folder, target_folder)



if __name__ == '__main__':
    if len(sys.argv) == 3:
        s_folder = sys.argv[1]
        t_folder = sys.argv[2]
        main(s_folder, t_folder)
    else:
        sys.exit(f"Usage: {sys.argv[0]}  <Source folder / file>  <Target folder>")
