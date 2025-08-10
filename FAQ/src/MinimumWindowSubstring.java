import java.util.*;

/*
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every
character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

Example 1
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The smallest substring containing 'A', 'B', and 'C' is "BANC".

Example 2
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the valid window.

Example 3
Input: s = "a", t = "aa"
Output: ""
Explanation: There aren’t enough 'a' characters in s to match t.

2️⃣ KEY POINTS EXPLAINED
Goal → Find the smallest substring of s containing all characters in t (including duplicates).

Data structures used:
need → Frequency map of required characters in t.
window → Frequency map of characters in current sliding window.

Two pointers (Sliding Window):
right expands the window to include characters.
left shrinks the window when all requirements are met to find the smallest possible.

Condition to shrink → When have == need.size() (all required chars met with correct counts).

Result → Keep track of smallest valid window using minLen and start.
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        // Edge case: If t is longer than s, it's impossible to find a valid window
        if (s.length() < t.length()) return "";

        // Step 1: Build frequency map for all required characters in t
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // Step 2: Initialize the sliding window variables
        Map<Character, Integer> window = new HashMap<>(); // Tracks character counts in current window
        int have = 0;                                     // How many unique characters currently meet the need count
        int needSize = need.size();                       // Total unique characters we must match
        int left = 0;                                     // Left boundary of the sliding window
        int minLen = Integer.MAX_VALUE;                   // Tracks smallest valid window length
        int start = 0;                                    // Tracks start index of the smallest window

        // Step 3: Expand the window by moving `right`
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // Add current char to the window count
            window.put(c, window.getOrDefault(c, 0) + 1);

            // If current char is in `need` and count matches exactly, we "have" one more requirement fulfilled
            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
                have++;
            }

            // Step 4: Try shrinking the window from the left while it still satisfies all requirements
            while (have == needSize) {
                // Update smallest window if found smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                // Prepare to remove the leftmost char from window
                char remove = s.charAt(left);
                window.put(remove, window.get(remove) - 1);

                // If removing breaks the requirement for a needed char, reduce `have`
                if (need.containsKey(remove) && window.get(remove) < need.get(remove)) {
                    have--;
                }

                // Move left pointer forward to shrink window
                left++;
            }
        }

        // Step 5: Return the smallest window found, or empty string if none found
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();

        // Example 1
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC")); // Expected: BANC

        // Example 2
        System.out.println(solution.minWindow("a", "a")); // Expected: a

        // Example 3
        System.out.println(solution.minWindow("a", "aa")); // Expected: ""
    }
}
