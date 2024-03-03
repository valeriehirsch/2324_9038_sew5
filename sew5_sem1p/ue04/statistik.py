import argparse
import logging
import os
from collections import Counter
from logging.handlers import RotatingFileHandler
from subprocess import Popen, PIPE
import matplotlib.pyplot as plt
import numpy as np



def main(args: argparse.Namespace) -> None:

    try:
        git_log = ["git", "-C", args.filename, "log", "--pretty=format:%ad", "--date=format-local:%a-%H-%M"]
        process = Popen(git_log, stdout=PIPE, stderr=PIPE, text=True)
        out, err = process.communicate()



        time_window = 0.5  # 1/4 hour

        weekdays = ["", "mon", "tue", "wed", "thu", "fri", "sat", "sun", ""]

        grouped_data = Counter()
        nbrOfCommits = 0
        for i in out.splitlines():
            cur = i.split("-")
            grouped_data[
                (cur[0].lower(), (np.floor((int(cur[1]) + int(cur[2]) / 60) / time_window) * time_window))] += 1
            nbrOfCommits += 1



        min_size = 50
        additional_size = 25

        data = {"x": [], "y": [], "sizes": []}
        for day, time in grouped_data:
            data["x"].append(time)
            data["y"].append(weekdays.index(day))
            data["sizes"].append(min_size + additional_size * grouped_data[(day, time)])
        plt.figure(figsize=(10, 8))

        plt.ylabel('Weekday')
        plt.scatter(data['x'], data['y'], s=data['sizes'], alpha=0.5)
        plt.yticks(range(len(weekdays)), labels=weekdays)
        plt.xticks(range(0, 25, 4))

        plt.xlabel('Time')
        plt.title(f'Valerie Hirsch: {nbrOfCommits} commits')
        plt.grid(True, which="major", axis="y", linestyle="-", linewidth=2, color='black')
        plt.xlabel('Weekday')


        plt.savefig("statistic_new.png", dpi=72)


    except:
        print("hallo")



if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Calculate number of ways through a labyrinth")
    parser.add_argument("filename", help="file containing the labyrinth to solve", default="./", nargs='?')
    group = parser.add_mutually_exclusive_group()
    group.add_argument("-v", "--verbose", action="store_true", help="log everything")
    group.add_argument("-q", "--quiet", action="store_true", help="log only errors")
    args = parser.parse_args()
    main(args)