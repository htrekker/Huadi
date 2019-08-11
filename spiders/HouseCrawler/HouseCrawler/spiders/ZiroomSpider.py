import scrapy
from HouseCrawler.items import HouseItem
from HouseCrawler.spiders.PriceHandler import interpret
import re
from HouseCrawler import settings
from HouseCrawler.spiders import NJZiroomSpider

class ZiroomSpider(scrapy.Spider):
    name = "ZiroomSpider"

    start_urls = [
        # 'http://hz.ziroom.com/z/p10/',
        # 'http://sh.ziroom.com/z/p9/',
        # 'http://sz.ziroom.com/z/p45/'
        # 'http://cd.ziroom.com/z/p14/'
        # 'http://wh.ziroom.com/z/',
        # 'http://gz.ziroom.com/z/',
        # 'http://tj.ziroom.com/z/'
        'http://nj.ziroom.com/z/'
    ]

    def parse(self, response):
        item = HouseItem()

        city = re.findall('http://(\\D{2,3})\\.', response.url)
        if len(city) is 1:
            item['city'] = city[0]

        info_boxes = response.xpath('//div[@class="Z_list-box"]//div[@class="info-box"]')

        print(len(info_boxes))
        for info in info_boxes:
            item['square'] = info.xpath('./div[@class="desc"]/div[1]//text()').get()
            locations = info.xpath('./div[@class="desc"]/div[2]//text()').extract()
            item['location'] = locations[0].replace("\n", "").replace("\t", "").replace(" ", "")
            prices = info.xpath('./div[@class="price"]/span[@class="num"]//@style').extract()
            item['price'] = interpret(prices)
            names = info.xpath('./h5/a//text()').extract()
            item['name'] = names[0]
            yield item

        urls = response.xpath('//div[@id="page"]/a[@class="next"]//@href').extract()
        print(urls)
        if len(urls) >= 1:
            print('next url: http:' + urls[0])
            yield response.follow("http:" + urls[0], callback=NJZiroomSpider.NjziroomspiderSpider.parse)
        else:
            if response.xpath('//object'):
                yield response.follow(response.url, callback=self.parse)


