from scrapy import cmdline
import requests
from lxml import etree
import re

cmdline.execute("scrapy crawl LianjiaSpider".split())

# response = requests.get('http://hz.ziroom.com/z/p10/', headers={
#     'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.87 Safari/537.36'})
# if response.status_code is 200:
#     print(response.text)
#     html = etree.HTML(response.text)
#     refs = html.xpath('//div[@id="page"]/a[@class="next"]//@href')
#     print(refs)
# else:
#     print('error')
url = 'https://sz.lianjia.com/zufang/'
print(url[0:len(url)-8])



