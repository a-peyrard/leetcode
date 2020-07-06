package org.teutinc.leetCode.easy;

/*
Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.

Example 1:

Input: A = "ab", B = "ba"
Output: true

Example 2:

Input: A = "ab", B = "ab"
Output: false

Example 3:

Input: A = "aa", B = "aa"
Output: true

Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true

Example 5:

Input: A = "", B = "aa"
Output: false
 */

public class BuddyString {
    public static final int A_LOWER = (int) 'a';

    public boolean buddyStrings(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        final char[] aChars = a.toCharArray();
        final char[] bChars = b.toCharArray();


        boolean hasMultipleOccurrenceOfCharacters = false;
        final int[] occurrences = new int[26];
        boolean alreadyMadeSubstitution = false;
        boolean lookingForSubstitution = false;
        char substituted = 'a';
        char substitute = 'a';
        for (int i = 0; i < aChars.length; i++) {
            int charIndex = aChars[i] - A_LOWER;
            occurrences[charIndex]++;
            if (occurrences[charIndex] > 1) {
                hasMultipleOccurrenceOfCharacters = true;
            }

            if (aChars[i] != bChars[i]) {
                if (alreadyMadeSubstitution) {
                    return false;
                } else if (lookingForSubstitution) {
                    if (aChars[i] == substituted && bChars[i] == substitute) {
                        lookingForSubstitution = false;
                        alreadyMadeSubstitution = true; // we found a substitution
                    } else {
                        return false; // this is not a possible substitution
                    }
                } else {
                    substitute = aChars[i];
                    substituted = bChars[i];
                    lookingForSubstitution = true;
                }
            }
        }

        return !lookingForSubstitution && (alreadyMadeSubstitution || hasMultipleOccurrenceOfCharacters);
    }
}
