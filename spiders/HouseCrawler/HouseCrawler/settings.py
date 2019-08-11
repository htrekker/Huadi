# -*- coding: utf-8 -*-

# Scrapy settings for HouseCrawler project
#
# For simplicity, this file contains only settings considered important or
# commonly used. You can find more settings consulting the documentation:
#
#     https://docs.scrapy.org/en/latest/topics/settings.html
#     https://docs.scrapy.org/en/latest/topics/downloader-middleware.html
#     https://docs.scrapy.org/en/latest/topics/spider-middleware.html

BOT_NAME = 'HouseCrawler'

SPIDER_MODULES = ['HouseCrawler.spiders']
NEWSPIDER_MODULE = 'HouseCrawler.spiders'

# Crawl responsibly by identifying yourself (and your website) on the user-agent
# USER_AGENT = 'HouseCrawler (+http://www.yourdomain.com)'

# Obey robots.txt rules
ROBOTSTXT_OBEY = True

# Configure maximum concurrent requests performed by Scrapy (default: 16)
# CONCURRENT_REQUESTS = 32

# Configure a delay for requests for the same website (default: 0)
# See https://docs.scrapy.org/en/latest/topics/settings.html#download-delay
# See also autothrottle settings and docs
# DOWNLOAD_DELAY = 3
# The download delay setting will honor only one of:
# CONCURRENT_REQUESTS_PER_DOMAIN = 16
# CONCURRENT_REQUESTS_PER_IP = 16

# Disable cookies (enabled by default)
COOKIES_ENABLED = False

# Disable Telnet Console (enabled by default)
# TELNETCONSOLE_ENABLED = False

# Override the default request headers:
# DEFAULT_REQUEST_HEADERS = {
#   'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
#   'Accept-Language': 'en',
# }

DEFAULT_REQUEST_HEADERS = {
    # 'Cookie': 'CURRENT_CITY_NAME=%E5%8C%97%E4%BA%AC; PHPSESSID=shga3lsup6mkrik8ideojipkp2; Hm_lvt_038002b56790c097b74c818a80e3a68e=1564538335; Hm_lpvt_038002b56790c097b74c818a80e3a68e=1564538335; _ga=GA1.2.2002734086.1564538336; _gid=GA1.2.1227992381.1564538336; Hm_lvt_4f083817a81bcb8eed537963fc1bbf10=1564538336; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216c45c0829e169-03d2265f36cb79-a7f1a3e-1327104-16c45c0829f2cf%22%2C%22%24device_id%22%3A%2216c45c0829e169-03d2265f36cb79-a7f1a3e-1327104-16c45c0829f2cf%22%2C%22props%22%3A%7B%7D%7D; gr_user_id=203f5f25-f0ea-4152-8363-bcf8ee99031b; gr_session_id_8da2730aaedd7628=2dc9525f-6518-4f7c-8311-4bee157e2687; gr_session_id_8da2730aaedd7628_2dc9525f-6518-4f7c-8311-4bee157e2687=true; CURRENT_CITY_CODE=330100; _csrf=KTauDrvsIVmVmzPtwhXvSstupsBdjz6R; Hm_lpvt_4f083817a81bcb8eed537963fc1bbf10=1564538386',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.87 Safari/537.36'
}

# Enable or disable spider middlewares
# See https://docs.scrapy.org/en/latest/topics/spider-middleware.html
# SPIDER_MIDDLEWARES = {
#    'HouseCrawler.middlewares.HousecrawlerSpiderMiddleware': 543,
# }

# Enable or disable downloader middlewares
# See https://docs.scrapy.org/en/latest/topics/downloader-middleware.html
DOWNLOADER_MIDDLEWARES = {
    'HouseCrawler.middlewares.UserAgentDownloaderMiddleWare': 300
}

# Enable or disable extensions
# See https://docs.scrapy.org/en/latest/topics/extensions.html
# EXTENSIONS = {
#    'scrapy.extensions.telnet.TelnetConsole': None,
# }

# Configure item pipelines
# See https://docs.scrapy.org/en/latest/topics/item-pipeline.html
# ITEM_PIPELINES = {
#    'HouseCrawler.pipelines.ZiroomPricePipeline': 300,
# }

# Enable and configure the AutoThrottle extension (disabled by default)
# See https://docs.scrapy.org/en/latest/topics/autothrottle.html
# AUTOTHROTTLE_ENABLED = True
# The initial download delay
# AUTOTHROTTLE_START_DELAY = 5
# The maximum download delay to be set in case of high latencies
# AUTOTHROTTLE_MAX_DELAY = 60
# The average number of requests Scrapy should be sending in parallel to
# each remote server
# AUTOTHROTTLE_TARGET_CONCURRENCY = 1.0
# Enable showing throttling stats for every response received:
# AUTOTHROTTLE_DEBUG = False

# Enable and configure HTTP caching (disabled by default)
# See https://docs.scrapy.org/en/latest/topics/downloader-middleware.html#httpcache-middleware-settings
# HTTPCACHE_ENABLED = True
# HTTPCACHE_EXPIRATION_SECS = 0
# HTTPCACHE_DIR = 'httpcache'
# HTTPCACHE_IGNORE_HTTP_CODES = []
# HTTPCACHE_STORAGE = 'scrapy.extensions.httpcache.FilesystemCacheStorage'

FEED_URI = 'file:///f:/test.csv'
FEED_FORMAT = 'csv'
FEED_ENCODING = 'utf-8-sig'

# DUPEFILTER_CLASS = 'scrapy.dupefilters.BaseDupeFilter'

FEED_EXPORTERS = {
    'csv': 'HouseCrawler.MyProjectCsvItemExporter.MyProjectCsvItemExporter',
}  # 这里假设你的project名字为my_project

FIELDS_TO_EXPORT = [
    'location',
    'price',
    'square',
    'name',
    'type',
    'floor',
    'city',

]

CSV_DELIMITER = ','
