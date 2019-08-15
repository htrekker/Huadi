# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class Rent58Item(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    # 城市
    city = scrapy.Field()
    # 楼层
    floor = scrapy.Field()
    # 位置
    location = scrapy.Field()
    # 小区名称
    name = scrapy.Field()
    # 房屋面积
    square = scrapy.Field()
    # 租金
    price = scrapy.Field()
    # 租房类型
    type = scrapy.Field()
