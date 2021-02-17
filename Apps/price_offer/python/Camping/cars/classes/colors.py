
TAB = '    '
END = '\033[0m'
RED = '\033[91m'
BLUE = '\033[94m'
BOLD = '\033[1m'
GREEN = '\033[92m'
YELLOW = '\033[93m'
UNDERLINE = '\033[4m'


def blue(message):
    return BLUE + message + END


def bold_blue(message):
    return BOLD + BLUE + message + END


def green(message):
    return GREEN + message + END


def bold_green(message):
    return BOLD + GREEN + message + END


def yellow(message):
    return YELLOW + message + END


def bold_yellow(message):
    return BOLD + YELLOW + message + END


def red(message):
    return RED + message + END


def bold_red(message):
    return BOLD + RED + message + END
