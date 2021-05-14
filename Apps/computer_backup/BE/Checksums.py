
import os
import hashlib



def get_checksum(path, hash_type='md5'):

    func = getattr(hashlib, hash_type)()

    f = os.open(path, (os.O_RDWR | os.O_BINARY))

    for block in iter(lambda: os.read(f, 2048*func.block_size), b''):
        func.update(block)
    os.close(f)

    return func.hexdigest()
