"""
This is the solution for the test: https://leetcode.com/problems/repeated-substring-pattern/

Problem:
Given a non-empty string check if it can be constructed by taking a substring
of it and appending multiple copies of the substring together.
You may assume the given string consists of lowercase English letters
only and its length will not exceed 10000.

Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.

Example 2:

Input: "aba"
Output: False

Example 3:

Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
"""


class Solution:
    # noinspection PyPep8Naming,PyMethodMayBeStatic
    def repeatedSubstringPattern(self, s: str) -> bool:
        if not s:
            return False

        length = len(s)

        if length == 1:
            return False

        pattern_max_length = int(length / 2) + (0 if length % 2 else 1)
        # we do want analyze the pattern of length 1, even if we have only 3 characters
        # (max_length would be 1)
        end_loop = 2 if pattern_max_length < 2 else pattern_max_length
        for pattern_length in range(1, end_loop):

            # length of the string has to be a multiple of the pattern length
            if length % pattern_length != 0:
                continue

            # now check this pattern, and in the beginning we will just assume it is
            # one, till be find a glitch
            cursor_in_pattern = 0
            is_a_pattern = True
            for cursor in range(pattern_length, length):
                if s[cursor] != s[cursor_in_pattern]:
                    is_a_pattern = False
                    break

                # we want to loop indefinitely in the pattern buffer
                # (N first characters of the string)
                cursor_in_pattern = (cursor_in_pattern + 1) % pattern_length

            if is_a_pattern:
                return True

        return False
