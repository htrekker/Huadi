from PIL import Image
import pytesseract
import re
import requests

cached_urls = set()

cached_code = {"images/da2eb29246dbe091142bac5e9df36d33s.png": "0427859361"}


def get_code(file_path):
    if file_path in cached_code:
        return cached_code[file_path]
    try:
        image = Image.open(file_path)
    except FileNotFoundError:
        print('image does not exits...')

    # 解决tesseract识别出现的问题，将图片扩大
    x, y = image.size
    p = Image.new('RGBA', (x + 20, y + 20), (236, 236, 236))
    p.paste(image, (10, 10, x + 10, y + 10), image)
    code = pytesseract.image_to_string(p)

    return code


# 下载图片并将图片保存到与其hash相同的文件中
def get_image(url):
    print(url)
    path = 'images/' + re.findall('/([\\da-z]*\\.png)', url)[0]

    # 判断内容是否存在
    if url not in cached_urls:
        cached_urls.add(url)

        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.87 Safari/537.36',
        }

        response = requests.get("http://" + url, headers=headers)

        if response.status_code is 200:
            with open(path, 'wb') as image1:
                image1.write(response.content)
        else:
            raise Exception

    return path


# 通过html获取price
def get_price(prices, code):
    actual_price = 0
    num = 1

    # print(prices)
    for i in prices:
        position = re.findall('(\\d{1,3}\\.?\\d?)px', i)
        index = int(float(position[0]) / 21.4)
        actual_price += int(code[index]) * (10 ** (len(prices) - num))
        num += 1

    return actual_price


def interpret(prices):
    url = re.findall('url\\(//(static\\d\\..*\\.png)\\)', prices[0])[0]

    if url in cached_code:
        return get_price(prices, cached_code[url])
    else:
        code = get_code(get_image(url))
        cached_code[url] = code
        return get_price(prices, code)
