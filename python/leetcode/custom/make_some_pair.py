"""
Not a leetcode problem, even if it could be one...

The goal was to find some pairs for Xmas gifts, with some constraints:
one person can not do a gift for its spouse.

So this is a brute force way of finding a result.
"""

import logging
import sys
from random import randrange
from typing import Tuple, List, Optional, Dict, Set


def find_pairs(people: List[str],
               constraints: Dict[str, Set[str]]) -> Optional[List[Tuple[str, str]]]:
    already_drawn = set()
    pairs = []

    for person in people:
        logging.debug("looking a pair for %s", person)
        constraint = constraints.get(person, set())
        match = find_match(
            people,
            # a person can not match itself, nor anyone already drawn, nor any of the initial constraints
            constraint.union(already_drawn, {person})
        )
        if not match:
            logging.warning(
                "unable to find a match for %s, already drawn %s, people %s, constraint %s",
                person,
                already_drawn,
                people,
                constraint
            )
            return None

        already_drawn.add(match)
        pairs.append((person, match))

    return pairs


def find_match(people: List[str],
               forbidden_matches: Set[str]) -> Optional[str]:
    number_of_people = len(people)
    match_index = randrange(number_of_people)

    # we will get an index, and if this person is not a match, as it might
    # been in the forbidden match
    for idx in range(number_of_people):
        possible_match = people[(match_index + idx) % number_of_people]
        if possible_match not in forbidden_matches:
            return possible_match
        else:
            logging.debug("the match %s is forbidden, so let's try to loop", possible_match)

    return None


def main():
    people = [
        "Anna",
        "Augustin",
        "Fanny",
        "Julien",
        "Patricia",
        "Nicolas",
        "Gaetan",
        "Agnes",
        "Sam"
    ]

    constraints = {
        "Anna": {"Augustin"},
        "Augustin": {"Anna"},
        "Fanny": {"Julien"},
        "Julien": {"Fanny"},
        "Patricia": {"Nicolas"},
        "Nicolas": {"Patricia"},
        "Gaetan": {"Agnes"},
        "Agnes": {"Gaetan"},
    }

    try_count = 0
    while try_count < MAX_TRY:
        logging.debug("== Attempt no %d", try_count)
        result = find_pairs(people, constraints)
        if result:
            logging.info(u"🎉 One result has been found:\n%s", result)
            return

        logging.debug("unable to find a result")
        try_count += 1

    logging.info(u"too many tries without any result found 😞")


MAX_TRY = 10


logging.basicConfig(
    stream=sys.stdout,
    level=logging.DEBUG,
    format="%(asctime)s - %(name)s - %(levelname)s - %(module)s : "
           "%(lineno)d - %(message)s"
)


if __name__ == "__main__":
    main()
