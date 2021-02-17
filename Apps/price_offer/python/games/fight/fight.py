#!/usr/bin/env python3
#encoding: utf-8
import random

cards = {}



def create_cards_dictionary():

    card_number = 1

    for symbol in card_signs:

        for card_number in range(1, 14):

            current_card = ""

            if card_number in card_values:
                current_card = "{}{}".format(card_unicode_symbols.get(symbol), str(card_values.get(card_number)))
            else:
                current_card = "{}{}".format(card_unicode_symbols.get(symbol), str(card_number))

            cards[card_number] = current_card

            card_number += 1



def main():

    # Create the main cards dictionary.
    create_cards_dictionary()

    index = random.randint(1,53)

    print (cards.get(index))


if __name__ == "__main__":

    card_signs           = ["Spade", "Heart", "Diamond", "Club"]
    card_values          = {1: "A", 11: "J", 12: "Q", 13: "K"}
    card_unicode_symbols = {"Spade": "\u2660", "Heart": "\u2665", "Diamond": "\u2666", "Club": "\u2663"}

    main()