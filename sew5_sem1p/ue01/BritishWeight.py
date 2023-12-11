class BritishWeight:
    """
    >>> print(BritishWeight(1, 15))
    2 st 1 lb
    >>> print(BritishWeight(0,5))
    5 lb
    >>> a = BritishWeight(2,2)
    >>> b = BritishWeight(0, 6)
    >>> print(a+b)
    2 st 8 lb
    >>> print(BritishWeight(1,7) + BritishWeight(0,16))
    2 st 9 lb
    """
    pounds = 0

    def __init__(self, stones : int, pounds : int):
        """
        Kontruktor der direkt kürzt
        :param stones:
        :param pounds: 14 pounds sidn 1 stone
        """
        self.pounds = stones * 14 + pounds


    def __str__(self):
        """
        :return: toString
        """
        stones = self.pounds // 14
        pounds = self.pounds % 14
        if self.pounds < 14:
            return f"{self.pounds} lb"
        else:
            return f"{stones} st {pounds} lb"


    def __add__(self, other):
        """
        :param other: andere Länge
        :return: addition
        """
        if isinstance(other, BritishWeight):
            return BritishWeight(0, self.pounds + other.pounds)
        else:
            raise TypeError("Invalid Input")


