# -*- coding: utf-8 -*-
import scrapy
import re
from HouseCrawler.items import HouseItem


class LianjiaspiderSpider(scrapy.Spider):
    name = 'LianjiaSpider'
    start_urls = [
        'https://nj.lianjia.com/zufang/',
        # 'https://sh.lianjia.com/zufang/pg28/',
        # 'https://wh.lianjia.com/zufang/pg28/',
        # 'https://tj.lianjia.com/zufang/pg28/'
    ]

    def clean_info(self, info_list):
        clean_list = []
        for info in info_list:
            temp = info.replace('\n', '').replace(' ', '').replace('/', '').replace('-', '')
            if temp is not '':
                clean_list.append(temp)
        return clean_list

    def parse(self, response):
        item = HouseItem()

        city = re.findall('https://(\\D{2,3})\\.', response.url)
        if len(city) is 1:
            item['city'] = city[0]

        content_list = response.xpath(
            '//*[@id="content"]/div[1]/div[1]//div[@class="content__list--item"]')

        for content_item in content_list:
            infos = content_item.xpath('div[@class="content__list--item--main"]/p[1]/a//text()').extract()[
                0].replace('\n', '').split('·')
            item['type'] = infos[0].replace(' ', '')
            item['name'] = infos[1].split(' ')[0]
            info_list = self.clean_info(
                content_item.xpath('div[@class="content__list--item--main"]/p[2]//text()').extract())
            # print(info_list)
            item['location'] = info_list[0] + " " + info_list[1] + " " + info_list[2]
            item['square'] = info_list[3]
            for i in info_list:
                temp = re.findall('\\D+(\\d{0,2}\\D)\\D$', i)
                if len(temp) is not 0:
                    item['floor'] = temp[0]
            item['price'] = content_item.xpath('./div[@class="content__list--item--main"]/span/em//text()').extract()[0]
            # print(item)
            yield item

        for next_url in range(28, 100):
            if next_url:
                next_url = 'https://' + re.findall('http.*(\\D{2}\\.lianjia\\.com)/', response.url)[
                    0] + "/zufang/pg" + str(next_url) + "/"
                yield response.follow(next_url, callback=self.parse)
