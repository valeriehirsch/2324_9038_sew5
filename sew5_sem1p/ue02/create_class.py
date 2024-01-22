import argparse
import random
import sys
from typing import Generator, List, Tuple

import openpyxl
from openpyxl import load_workbook
import logging
import logging.handlers

logger = logging.getLogger(__name__)
handler = logging.handlers.RotatingFileHandler("create_class.log", maxBytes=10000, backupCount=5)
formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')
handler.setFormatter(formatter)

stream_handler = logging.StreamHandler(sys.stdout)
stream_handler.setFormatter(formatter)

logger.addHandler(handler)
logger.addHandler(stream_handler)

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

def rename(s: str) -> str:
    """
    Escape special character in a string
    :param s:
    :return:
    """
    return s.replace('"', '\\"').replace("'", "\\'").replace("`", "\\`")

def create_user(i: List[str], file, pwd: str) -> None:
    """
    Writes commands to create a user into file
    :param i:
    :param file:
    :param pwd:
    :return:
    """
    logger.info(f"Creating user: {i[0]}")
    if verbose:
        print(f"echo creating user: {i[0]}", file=file)
    command1 = (
        f"getent passwd k{replace_umlaut(str(i[0]).lower())} > /dev/null && echo 'User " 
        f"{replace_umlaut(str(i[0]).lower())} already exists. Aborting.' && exit 1 || true"
    )
    command2 = f"groupadd {replace_umlaut(str(i[0]).lower())}"

    command3 = (
        f"useradd -d /home/klassen/{replace_umlaut(str(i[0]).lower())} "
        f"-c k{replace_umlaut(str(i[0]).lower())} "
        f"-m -g {replace_umlaut(str(i[0]).lower())} "
        f"-G cdrom,plugdev,sambashare -s /bin/bash k{replace_umlaut(str(i[0]).lower())}"
    )

    command4 = f"echo {replace_umlaut(str(i[0]).lower())}:{rename(pwd)} | chpasswd"

    print(command1, file=file)
    print(command2, file=file)
    print(command3, file=file)
    print(command4, file=file)

def delete_user(i: List[str], file) -> None:
    """
    Writes commands to delete a user into file
    :param i:
    :param file:
    :return:
    """
    logger.info(f"Deleting user: {i[0]}")
    if verbose: print(f"echo deleting user: {i[0]}", file=file)
    command = "userdel -r k" + replace_umlaut(str(i[0]).lower())
    print(command, file=file)

def generate_password(class_name: str, room: str, kv: str) -> str:
    """
    generates a password
    :param class_name:
    :param room:
    :param kv:
    :return:
    """
    logger.info("Generating password")
    chars = "1%&(),._-=^#"
    random_chars = [random.choice(chars) for _ in range(3)]
    password = f"{class_name}{random_chars[0]}{room}{random_chars[1]}{kv}{random_chars[2]}"
    return password



def create_credentials() -> Tuple[openpyxl.workbook.workbook.Workbook, openpyxl.worksheet.worksheet.Worksheet]:
    """
    Creates an excel sheet for storing credentials
    :return:
    """
    logger.info("Creating credentials sheet")
    workbook = openpyxl.Workbook()
    sheet = workbook.active

    sheet["A1"] = "Username"
    sheet["B1"] = "Password"
    return workbook, sheet
def add_credentials(sheet: openpyxl.worksheet.worksheet.Worksheet, name: str, row: int, pwd: str) -> None:
    """
    Adds credentials to the excel sheet
    :param sheet:
    :param i:
    :param row:
    :param pwd:
    :return:
    """
    logger.info(f"Adding credentials for user: {name}")
    sheet[f"A{row}"] = name
    sheet[f"B{row}"] = pwd

def save_credentials(workbook: openpyxl.workbook.workbook.Workbook) -> None:
    """
    Saves credentials to the excel sheet
    :param workbook:
    :return:
    """
    logger.info("Saving credentials to file")
    workbook.save("user_credentials.xlsx")












