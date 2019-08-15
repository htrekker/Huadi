import scrapy
from Rent58.items import Rent58Item
from Rent58.modules import interprate
import re
# import time
# import random
#
# time.sleep(random.random()*3)

class RentSpider(scrapy.Spider):
    name = 'Rent58'
    allowed_domains = ['58.com']
    # 房源数据爬取城市列表：北京，天津，成都，上海，广州，深圳，重庆，杭州，南京，武汉
    start_urls = ['https://bj.58.com/chuzu/','https://tj.58.com/chuzu/','https://cd.58.com/chuzu/',
                  'https://sh.58.com/chuzu/','https://gz.58.com/chuzu/','https://sz.58.com/chuzu/',
                  'https://cq.58.com/chuzu/','https://hz.58.com/chuzu/','https://nj.58.com/chuzu/',
                  'https://wh.58.com/chuzu/']

    def parse(self, response):
        item = Rent58Item()

        base64_strs: list = re.findall('base64,([\\D\\d/+=]+)\'\\)\s?format', response.text)

        # 房屋出租，58同城租房第一类
        rooms = response.xpath('//li[@class="house-cell"]')
        for each in rooms:
            item['city'] = response.url[8:10]

            # item['floor'] =

            item['location'] = each.xpath('div[@class="des"]/p[@class="infor"]/a/text()').extract()[0]

            # 有的房屋信息无小区名称，故使用try来跳过错误
            try:
                item['name'] = each.xpath('div[@class="des"]/p[@class="infor"]/a[@target="_blank"]/text()').extract()[0]
            except IndexError:
                pass

            # square消除所有空格后文本信息为XXX\xa0\xa0\xa0\xa0\num，要取得num，split以\xa0分割后取第5个字符串，再去掉㎡符号便于数字解码
            square = each.xpath('div[@class="des"]/p[@class="room"]/text()').extract()[0].replace(' ','').split('\xa0')[4].split('㎡')[0]
            # 面积数字解码
            base64_str = ''
            if len(base64_strs) >= 1:
                base64_str = base64_strs[0]
            tag = []
            for char in square:
                tag.append(ord(char))
            square = interprate(base64_str, tag)
            item['square'] = square

            # 租金数字解码
            price = each.xpath('div[@class="list-li-right"]/div[@class="money"]/b/text()').extract()[0]
            base64_str = ''
            if len(base64_strs) >= 1:
                base64_str = base64_strs[0]
            tag = []
            for char in price:
                tag.append(ord(char))
            price = interprate(base64_str, tag)
            item['price'] = price

            # type消除所有空格和换行后，将单间替换为合租以符合信息格式，文本信息为type|xxx，只取|前的type，split以|分割后取第一个字符串
            item['type'] = each.xpath('div[@class="des"]/h2/a/text()').extract()[0].replace(' ','').replace('\n','').replace('单间','合租').split('|')[0]
            yield item


        # 公寓出租，58同城租房第二类
        apartments = response.xpath('//li[@class="apartments"]')
        for each in apartments:

            item['city'] = response.url[8:10]

            # item['floor'] =

            item['location'] = each.xpath('div[@class="des"]/p[@class="infor"]/text()').extract()[0].split(' ')[0]

            # 有的房屋信息无小区名称，故使用try来跳过错误
            try:
                item['name'] = each.xpath('div[@class="des"]/p[@class="infor"]/text()').extract()[0].split(' ')[2]
            except IndexError:
                pass

            # square消除所有空格后文本信息为XXX\xa0\xa0\xa0\xa0\num，要取得num，split以\xa0分割后取第5个字符串
            item['square'] = each.xpath('div[@class="des"]/p[@class="room"]/text()').extract()[0].replace(' ', '').split('\xa0')[4]

            # 租金解码
            price = each.xpath('div[@class="list-li-right"]/div[@class="money"]/b/text()').extract()[0]
            base64_str = ''
            if len(base64_strs) >= 1:
                base64_str = base64_strs[0]
            tag = []
            for char in price:
                tag.append(ord(char))
            price = interprate(base64_str, tag)
            item['price'] = price

            # type消除所有空格和换行后，将单间替换为合租以符合信息格式，文本信息为type|xxx，只取|前的type，split以|分割后取第一个字符串
            item['type'] = each.xpath('div[@class="des"]/h2/a/text()').extract()[0].replace(' ', '').replace('\n', '').replace('单间','合租').split('|')[0]
            yield item

        # 翻页
        next_link = response.xpath('//li[@id="pager_wrap"]/div[@class="pager"]/a[@class="next"]/@href').extract()[0]
        print(next_link)
        if next_link:
            yield scrapy.Request(next_link,self.parse)