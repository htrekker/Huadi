# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class HouseItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()

    location = scrapy.Field()
    price = scrapy.Field()
    square = scrapy.Field()
    name = scrapy.Field()
    type = scrapy.Field()
    floor = scrapy.Field()
    city = scrapy.Field()

    pass
