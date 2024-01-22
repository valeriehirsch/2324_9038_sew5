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

    def __init__(self, filename, x_start, y_start, should_print, print_time, delay):
        self.labyrinth = self.load_labyrinth(filename)
        self.x_start = x_start
        self.y_start = y_start
        self.should_print = should_print
        self.print_time = print_time
        self.delay = delay
        self.solutions = set()

    def load_labyrinth(self, filename):
        """ reads the labyrinth from the file"""
        with open(filename, 'r') as file:
            labyrinth = [line.strip() for line in file]
        return labyrinth

   