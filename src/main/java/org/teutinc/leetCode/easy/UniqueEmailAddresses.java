package org.teutinc.leetCode.easy;

import org.teutinc.leetCode.util.TestRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
    Every email consists of a local name and a domain name, separated by the @ sign.

    For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

    Besides lowercase letters, these emails may contain '.'s or '+'s.

    If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

    If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

    It is possible to use both of these rules at the same time.

    Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?
 */
public class UniqueEmailAddresses {
    static class Email {
        private final String email;

        Email(String email) {
            this.email = email;
        }

        @Override
        public boolean equals(Object o) {
            final char[] w1 = this.email.toCharArray();
            final char[] w2 = ((Email) o).email.toCharArray();

            int read2 = 0;
            int read1 = 0;
            for ( ; read1 < w1.length; read1++) {
                while(w1[read1] == '.') {
                    read1++;
                }
                while(w2[read2] == '.') {
                    read2++;
                }
                boolean inPlus = false;
                if (w1[read1] == '+') {
                    while(w1[read1] != '@') {
                        read1++;
                    }
                    inPlus = true;
                }
                if (w2[read2] == '+') {
                    while(w2[read2] != '@') {
                        read2++;
                    }
                    inPlus = true;
                }
                if (inPlus) {
                    break;
                }

                if (w1[read1] != w2[read2]) {
                    return false;
                }
                read2++;
            }

            for (; read1 < w1.length; read1++) {
                if (w1[read1] != w2[read2]) {
                    return false;
                }
                read2++;
            }

            return read2 == w2.length;
        }

        @Override
        public int hashCode() {
            int h = 0;
            boolean beforeDomain = true;
            boolean afterPlus = false;
            for (char v : this.email.toCharArray()) {
                if (beforeDomain) {
                    if (v == '@') {
                        beforeDomain = false;
                    } else if (v == '+') {
                        afterPlus = true;
                        continue;
                    } else if (v == '.' || afterPlus) {
                        continue;
                    }
                }
                h = 31 * h + (v & 0xff);
            }
            return h;
        }
    }

    private static int numUniqueEmails(String[] emails) {
        Set<Email> uniques = new HashSet<>();
        for (String email : emails) {
            uniques.add(new Email(email));
        }
        return uniques.size();
    }

    static class Test implements TestRunner.RunnableTest<Integer> {
        private final String[] input;
        private final int output;

        Test(String[] input, int output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public Integer run() {
            return numUniqueEmails(input);
        }

        @Override
        public String inputs() {
            return Arrays.toString(input);
        }

        @Override
        public Integer expect() {
            return output;
        }
    }

    public static void main(String[] args) {
        TestRunner.basic()
                  .run(
                          new Test(new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"}, 2),
                          new Test(new String[]{"test.email@leetcode.coma", "test.email@leetcode.comb"}, 2),
                          new Test(new String[]{"test.email@leetcode.com", "test.email@leetcode.comb"}, 2),
                          new Test(new String[]{"test.email@leetcode.coma", "test.email@leetcode.com"}, 2),
                          new Test(new String[]{"augustin@leetcode.com", "augustin@leetcode.com"}, 1),
                          new Test(new String[]{"augustin@leetcode.com", "augustin@leet.com"}, 2),
                          new Test(new String[]{"augustin@e.com", "augustin@leet.com"}, 2),
                          new Test(new String[]{"augustin@e.com", "a@t.com"}, 2)
                  );
    }
}
