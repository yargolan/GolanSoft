
import requests
from bs4 import BeautifulSoup

url = "https://pais.co.il/lotto"
url = "https://www.google.com"


def main():

    r = requests.get(url, proxies= {})
    print(r.status_code)
    print(r.text)
    print(r.content)
    # soup = BeautifulSoup(r.text, 'html.parser')
    # print(soup.prettify())




if __name__ == '__main__':
    main()
