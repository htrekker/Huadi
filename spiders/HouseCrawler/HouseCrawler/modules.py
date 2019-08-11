import base64
from fontTools.ttLib import TTFont


def save_font_file(base64_str, file_path='font.ttf'):
    content = base64.decodebytes(bytes(base64_str, 'utf-8'))
    with open(file_path, 'wb') as f:
        f.write(content)


def interprate(base64_str, numbers, file_path='font.ttf'):
    save_font_file(base64_str)
    font = TTFont("font.ttf")
    c = font['cmap'].tables[0].ttFont.tables['cmap'].tables[0].cmap
    num_str = ''
    for number in numbers:
        if number is 46:
            num_str = num_str + '.'
        elif number is 23460:
            pass
        else:
            num = int(c[number][5:15])
            print(num)
            num_str = num_str + str(num-1)

    return float(num_str)
