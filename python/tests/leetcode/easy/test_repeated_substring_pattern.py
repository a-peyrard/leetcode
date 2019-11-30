from unittest import TestCase

from python.leetcode.easy.repeated_substring_pattern import Solution


class TestRepeatedSubstringPattern(TestCase):
    def test_it_should_return_false_for_empty_strings(self):
        # GIVEN
        value = ""

        # WHEN
        output = Solution().repeatedSubstringPattern(value)

        # THEN
        self.assertFalse(output)

    def test_it_should_return_false_for_string_of_length_one(self):
        # GIVEN
        value = "a"

        # WHEN
        output = Solution().repeatedSubstringPattern(value)

        # THEN
        self.assertFalse(output)

    def test_it_should_return_true_for_string_using_only_one_character(self):
        # GIVEN
        value = "aaaaaa"

        # WHEN
        output = Solution().repeatedSubstringPattern(value)

        # THEN
        self.assertTrue(output)

    def test_it_should_return_true_for_string_using_only_one_character_again(self):
        # GIVEN
        value = "zzz"

        # WHEN
        output = Solution().repeatedSubstringPattern(value)

        # THEN
        self.assertTrue(output)

    def test_it_should_return_true_for_string_using_only_two_characters(self):
        # GIVEN
        value = "abababab"

        # WHEN
        output = Solution().repeatedSubstringPattern(value)

        # THEN
        self.assertTrue(output)

    def test_it_should_return_true_for_string_using_only_three_characters(self):
        # GIVEN
        value = "abcabcabc"

        # WHEN
        output = Solution().repeatedSubstringPattern(value)

        # THEN
        self.assertTrue(output)

    def test_it_should_return_true_for_string_using_n_characters(self):
        # GIVEN
        value = "augustinaugustinaugustin"

        # WHEN
        output = Solution().repeatedSubstringPattern(value)

        # THEN
        self.assertTrue(output)

    def test_it_should_return_false_for_simple_string_not_using_a_pattern(self):
        # GIVEN
        value = "aba"

        # WHEN
        output = Solution().repeatedSubstringPattern(value)

        # THEN
        self.assertFalse(output)

    def test_it_should_return_false_for_simple_string_not_using_a_pattern_again(self):
        # GIVEN
        value = "ab"

        # WHEN
        output = Solution().repeatedSubstringPattern(value)

        # THEN
        self.assertFalse(output)

    def test_it_should_return_false_for_complex_string_not_using_a_pattern(self):
        # GIVEN
        value = "augustinaugusti"

        # WHEN
        output = Solution().repeatedSubstringPattern(value)

        # THEN
        self.assertFalse(output)