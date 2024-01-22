import argparse
import time


class LabyrinthSolver:
    """
    >>> solver = LabyrinthSolver("l2.txt", 5, 5, False, 0, 0)
    >>> solver.solve_labyrinth()
    nbr of solutions: 486
    """

    border_char = '#'
    visited_char = 'X'
    target_char = 'A'

    def __init__(self, filename, x_start, y_start, should_print, delay):
        """
        Konstruktor
        :param filename: gibt file an
        :param x_start: Spalten Start
        :param y_start: Zeilen Start
        :param should_print: boolean
        :param delay: sets delay
        """
        self.labyrinth = self.load_labyrinth(filename)
        self.x_start = x_start
        self.y_start = y_start
        self.should_print = should_print
        self.delay = delay
        self.solutions = set()

    def load_labyrinth(self, filename):
        """
        Lies lab aus
        :param filename: filename
        """
        with open(filename, 'r') as file:
            labyrinth = [line.strip() for line in file]
        return labyrinth

    def search_all(self, zeile, spalte, lab):
        """
        Sucht alle möglichen Ausgänge
        :param zeile: in welcher Zeile befinde ich mich gerade
        :param spalte: in welche Spalte befinde ich mich gerade
        :param lab: welches lab löse ich (inkl. fortschritt)
        """
        if zeile < 0 or spalte < 0 or zeile >= len(lab) or spalte >= len(lab[0]):
            return 0
        if lab[zeile][spalte] == LabyrinthSolver.target_char:
            if self.should_print:
                self.print_solution(lab)
                time.sleep(self.delay / 1000)
            return 1
        if lab[zeile][spalte] == LabyrinthSolver.border_char or lab[zeile][spalte] == LabyrinthSolver.visited_char:
            return 0
        amount = 0
        old = lab[zeile][spalte]
        lab[zeile] = lab[zeile][:spalte] + LabyrinthSolver.visited_char + lab[zeile][spalte + 1:]

        amount += self.search_all(zeile + 1, spalte, lab)
        amount += self.search_all(zeile, spalte + 1, lab)
        amount += self.search_all(zeile - 1, spalte, lab)
        amount += self.search_all(zeile, spalte - 1, lab)
        lab[zeile] = lab[zeile][:spalte] + old + lab[zeile][spalte + 1:]
        return amount

    def solve_labyrinth(self):
        """
        Löst das Labyrinth
        """
        start_time = time.time()

        amount = self.search_all(self.y_start, self.x_start, self.labyrinth)
        elapsed_time = (time.time() - start_time) * 1000

        print(f"nbr of solutions: {amount}")


    def print_solution(self, solution):
        """
        Gibt Labyrinth aus
        :param solution: Lab als input
        """
        s = ""
        for i in solution:
            s += str(i) + '\n'
        print(s)



