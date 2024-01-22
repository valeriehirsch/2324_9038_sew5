from typing import Generator, List, Tuple

import openpyxl
from openpyxl import load_workbook

verbose = False
quiet = False


def read_file(path: str) -> Generator:
    """
    Reads an Excel File and yiela rows
    :param path:
    :return:
    """
    wb = load_workbook(path, read_only=True)
    ws = wb[wb.sheetnames[0]]
    for row in ws.iter_rows(min_row=2):
        if all(cell.value is None for cell in row):
            continue
        yield [row[0].value, row[1].value, row[2].value]

def replace_umlaut(s: str) -> str:
    """
    Replaces umlauts
    :param s:
    :return:
    """
    replacements = {
        'ä': 'ae',
        'ö': 'oe',
        'ü': 'ue',
        'Ä': 'Ae',
        'Ö': 'Oe',
        'Ü': 'Ue',
        'ß': 'ss'
    }
    for umlaut, replacement in replacements.items():
        s = s.replace(umlaut, replacement)
    return s
















