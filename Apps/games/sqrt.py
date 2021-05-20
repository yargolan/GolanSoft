import sys
from math import sqrt


if __name__ == '__main__':

    number = int(input("Insert the number you wish to calculate its square root: "))
    guess  = int(input("Insert the number start calculating from: "))
    if number < 1:
        sys.exit("The number must be > 1")

    calculated = sqrt(number)

    sqrt = 0

    if abs(guess*guess - number