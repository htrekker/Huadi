# -*- coding: utf-8 -*-

from scrapy.exceptions import DropItem

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


class HousecrawlerPipeline(object):
    def process_item(self, item, spider):
        return item



class ZiroomPricePipeline(object):

    def process_item(self, item, spider):
        attrs = item['name'].split('·')
        if len(attrs) is 2:
            item['name'] = attrs[1]
            item['type'] = attrs[0]
        else:
            print('item drop...')
            raise DropItem

        attrs = item['square'].split(' | ')
        if len(attrs) is 2:
            item['square'] = attrs[0]
            item['floor'] = attrs[1]
            return item
        else:
            print('item drop...')
            raise DropItem
