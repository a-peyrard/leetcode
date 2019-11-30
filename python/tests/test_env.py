#!/usr/bin/env python
# coding=utf-8

"""
Sample file to ensure at least one test is running properly
"""
from unittest import TestCase


class TestEnv(TestCase):
    def test_equals(self):
        # GIVEN
        a = 1

        # THEN
        self.assertEqual(1, a)
