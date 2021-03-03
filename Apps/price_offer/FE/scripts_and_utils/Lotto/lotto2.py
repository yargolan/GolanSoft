
from bs4 import BeautifulSoup
import requests


base_url     = "https://www.pais.co.il"
full_url     = f"{base_url}/lotto/archive.aspx"
no_proxy    = "http://emea-chain.proxy.att.com:8080"
http_proxy   = "http://emea-chain.proxy.att.com:8080"
https_proxy  = "http://emea-chain.proxy.att.com:8080"


proxyDict = {"http": http_proxy, "https": https_proxy}


def main():
    r = requests.get(url, proxies = proxyDict)
    print(type(r))


if __name__ == '__main__':
    main()
