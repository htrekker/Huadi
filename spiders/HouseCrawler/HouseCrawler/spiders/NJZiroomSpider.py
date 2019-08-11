# -*- coding: utf-8 -*-
import scrapy
import re
from HouseCrawler.items import HouseItem
from HouseCrawler.spiders import PriceHandler


class NjziroomspiderSpider(scrapy.Spider):
    name = 'NJZiroomSpider'
    # allowed_domains = ['./spiders']
    start_urls = ['http://nj.ziroom.com/z/nl/z3.html?p=50']

    def parse(self, response):
        item = HouseItem()

        content_list = response.xpath('//*[@id="houseList"]//li[@class="clearfix"]')

        count = 0
        item['city'] = 'nj'
        for content_item in content_list:
            item['name'] = content_item.xpath('./div[@class="txt"]/h3/a//text()').extract()
            info_list = content_item.xpath('./div[@class="txt"]/div[@class="detail"]/p[1]//text()').extract()
            cleaned_list = self.clean_info(info_list)
            item['square'] = cleaned_list[0]
            item['floor'] = cleaned_list[1]
            item['type'] = '整租'
            item['location'] = content_item.xpath(
                './div[@class="txt"]/div[@class="detail"]/p[2]/span//text()').extract()

            price_url = re.findall(
                'var ROOM_PRICE\\s?=\\s\\{\\D{5,10}//(static8.ziroom.com/phoenix/pc/images/price/[\\D\\d]{0,40}\\.png)',
                response.text)
            # print(price_url)
            code = PriceHandler.get_code(PriceHandler.get_image(price_url[0]))
            prices = re.findall('(\\[\\d?,?\\d,\\d,\\d\\][,\\]])', response.text)
            item['price'] = self.make_list(prices[count], code)
            count = count + 1
            yield item

        url = response.xpath('//*[@id="page"]/a[@class="next"]//@href').extract()
        # print("next url=>"+url[0])
        if len(url) is not 0:
            yield response.follow("http:"+url[0], callback=self.parse)

    def make_list(self, price, code):
        actual_price = 0
        count = 1
        indexes = re.findall('(\\d)[,\\]]', price)
        for index in indexes:
            tag = int(index)
            actual_price = actual_price + (int(code[tag]) * (10 ** (4 - count)))
            count = count + 1
        return actual_price

    def clean_info(self, info_list):
        clean_list = []
        for info in info_list:
            temp = info.replace('\n', '').replace(' ', '').replace('|', '')
            if temp is not '':
                clean_list.append(temp)
        return clean_list
