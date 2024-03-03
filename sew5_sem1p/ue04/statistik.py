import argparse
import logging
import os
from collections import Counter
from logging.handlers import RotatingFileHandler
from subprocess import Popen, PIPE
import matplotlib.pyplot as plt
import numpy as np


def get_logger(args: argparse.Namespace) -> logging.Logger:
    """
    logger
    """
    logger = logging.getLogger('my_logger')
    if args.verbose:
        logger.setLevel(logging.DEBUG)
    elif args.quiet:
        logger.setLevel(logging.WARNING)
    else:
        logger.setLevel(logging.INFO)
    if not os.path.exists("logging"):
        os.mkdir("logging")
    file_handler = RotatingFileHandler("./logging/statistic.log", maxBytes=10000, backupCount=5)
    file_handler.setFormatter(logging.Formatter('[%(asctime)s] %(levelname)s %(message)s'))
    stream_handler = logging.StreamHandler()
    stream_formatter = logging.Formatter('%(levelname)s - %(message)s')
    stream_handler.setFormatter(stream_formatter)
    logger.addHandler(file_handler)
    logger.addHandler(stream_handler)
    logger.info("Logging turned on " + str(logger.level))
    return logger


def main(args: argparse.Namespace) -> None:
    logger = get_logger(args)
    try:
        #getting git data
        git_log = ["git", "-C", args.filename, "log", "--pretty=format:%ad", "--date=format-local:%a-%H-%M"]
        process = Popen(git_log, stdout=PIPE, stderr=PIPE, text=True)
        out, err = process.communicate()

        logger.debug("Data from command")
        time_window = 0.5
        weekdays = ["", "mon", "tue", "wed", "thu", "fri", "sat", "sun", ""]

        grouped_data = Counter()
        nbrOfCommits = 0
        for i in out.splitlines():
            cur = i.split("-")
            grouped_data[
                (cur[0].lower(), (np.floor((int(cur[1]) + int(cur[2]) / 60) / time_window) * time_window))] += 1
            nbrOfCommits += 1

        logger.debug("Sorted Data")

        min_size = 100
        additional_size = 250

        data = {"x": [], "y": [], "sizes": []}
        for day, time in grouped_data:
            data["x"].append(time)
            data["y"].append(weekdays.index(day))
            data["sizes"].append(min_size + additional_size * grouped_data[(day, time)])
        plt.figure(figsize=(10, 8))

        plt.ylabel('Weekdays')
        plt.scatter(data['x'], data['y'], s=data['sizes'], alpha=0.5)
        plt.yticks(range(len(weekdays)), labels=weekdays)
        plt.xticks(range(0, 25, 4))

        plt.xlabel('Time in hours')
        plt.title(f'Valerie Hirsch: {nbrOfCommits} commits')
        plt.grid(True, which="major", axis="y", linestyle="-", linewidth=1.5, color='black')
        logger.debug("Data set")
        plt.savefig("Statistik_Hirsch.png", dpi=72)
        logger.info("Finished")
        plt.show()

    except:
        logger.error("There was an error")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Creates a graph with your git data")
    parser.add_argument("filename", help="file containing git data", default="./", nargs='?')
    group = parser.add_mutually_exclusive_group()
    group.add_argument("-v", "--verbose", action="store_true", help="log everything")
    group.add_argument("-q", "--quiet", action="store_true", help="log only errors")
    args = parser.parse_args()
    main(args)