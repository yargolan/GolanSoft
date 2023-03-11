import unittest

import shahar


class TestStringMethods(unittest.TestCase):

    def test_convert_minutes_to_hours(self):
        self.assertEqual(shahar.convert_minutes_to_hours(30), "0:30")
        self.assertEqual(shahar.convert_minutes_to_hours(90), "1:30")

    def test_convert_hours_to_minutes(self):
        self.assertEqual(5, shahar.convert_time_to_minutes("00:05"))
        self.assertEqual(65, shahar.convert_time_to_minutes("01:05"))


if __name__ == '__main__':
    unittest.main()
