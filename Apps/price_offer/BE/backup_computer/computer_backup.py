import os
import sys
import hashlib
from pathlib import Path
from shutil import copyfile



def get_checksum(filename, hash_factory=hashlib.md5, chunk_num_blocks=128):
    h = hash_factory()
    with open(filename,'rb') as f:
        while chunk := f.read(chunk_num_blocks*h.block_size):
            h.update(chunk)
    return h.digest()



def same_checksum(source_file, target_file):
    source_md5 = get_checksum(source_file)
    target_md5 = get_checksum(target_file)
    return source_md5 == target_md5



def run_backup(source, target):

    if os.path.isfile(source):

        # Check if we have same target file.
        if os.path.isfile(target):

            # Verify checksums.
            if same_checksum(source, target):
                return

        else:
            # Verify that the path for the target file's parent exists.
            path = Path(target)
            os.makedirs(path.parent, exist_ok=True)

            # Copy the file.
            copyfile(source, target)

    elif os.path.isdir(source):
        files_list = os.listdir(source)
        for item in files_list:
            new_source = f"{source}/{item}"
            new_target = f"{target}/{item}"
            run_backup(new_source, new_target)
    else:
        sys.exit("WTF ??")




if __name__ == '__main__':
    if len(sys.argv) == 3:
        run_backup(sys.argv[1], sys.argv[2])
