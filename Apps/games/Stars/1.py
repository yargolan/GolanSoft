#!/usr/bin/env pyhton3

import time

times = 10


for i in range(0,times):

    max_spaces = 10
    index = 0

    while index < max_spaces:
        line = " " * index
        line += "* "
        print(line)
        time.sleep(0.02)
        index += 1

    while index > 0:
        line = " " * index
        line += "*"
        print(line)
        index -= 1
        time.sleep(0.02)







# while max_spaces > index:
#     max_spaces -= 1


# while index < max_spaces:
#     line = " " * index
#     line += "*"
#     print(line)
#     index += 1

