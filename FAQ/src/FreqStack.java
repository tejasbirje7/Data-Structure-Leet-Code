import java.util.*;
/*

Example Input / Output:
FreqStack freqStack = new FreqStack();
freqStack.push(5); // stack: [5]
freqStack.push(7); // stack: [5, 7]
freqStack.push(5); // stack: [5, 7, 5]
freqStack.push(7); // stack: [5, 7, 5, 7]
freqStack.push(4); // stack: [5, 7, 5, 7, 4]
freqStack.push(5); // stack: [5, 7, 5, 7, 4, 5]
System.out.println(freqStack.pop()); // 5 (most frequent)
System.out.println(freqStack.pop()); // 7 (tie but closer to top)
System.out.println(freqStack.pop()); // 5
System.out.println(freqStack.pop()); // 4

KEY POINTS EXPLAINED
Goal:
Maintain a stack that can:
Push values normally.
Pop the most frequent element.
In case of a tie, pop the most recently pushed element.

Data Structures Used:
freqMap → Maps a number to its frequency. Example: {5: 3, 7: 2, 4: 1}
groupStack → Maps frequency to a stack of numbers with that frequency.
Example: {1: [4], 2: [7], 3: [5]}
maxFreq → Tracks the highest frequency currently in the stack.

Push Logic:
Update the number’s frequency in freqMap.
Add it to the stack in groupStack corresponding to that frequency.
Update maxFreq if needed.

Pop Logic:
Retrieve the stack for maxFreq.
Pop the top value (most recent at that frequency).
Decrement its frequency in freqMap.
If no elements remain at maxFreq, reduce maxFreq.

Time Complexity:
push() → O(1)
pop() → O(1)
 */
public class FreqStack {

    // Tracks how many times each value appears
    private Map<Integer, Integer> freqMap;

    // Maps frequency -> stack of values with that frequency
    private Map<Integer, Stack<Integer>> groupStack;

    // Tracks the current highest frequency
    private int maxFreq;

    public FreqStack() {
        freqMap = new HashMap<>();
        groupStack = new HashMap<>();
        maxFreq = 0;
    }

    // Push value into stack
    public void push(int val) {
        // Update frequency of val
        int valCount = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, valCount);

        // If this is a new max frequency, prepare a stack for it
        if (valCount > maxFreq) {
            maxFreq = valCount;
            groupStack.put(valCount, new Stack<>());
        }

        // Add value to stack for this frequency
        groupStack.get(valCount).push(val);
    }

    // Pop most frequent element
    public int pop() {
        // Get the most recent element with max frequency
        int res = groupStack.get(maxFreq).pop();

        // Reduce its frequency in freqMap
        freqMap.put(res, freqMap.get(res) - 1);

        // If no more elements at this frequency, reduce maxFreq
        if (groupStack.get(maxFreq).isEmpty()) {
            maxFreq--;
        }

        return res;
    }

    // MAIN method for testing
    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5); // stack: [5]
        freqStack.push(7); // stack: [5, 7]
        freqStack.push(5); // stack: [5, 7, 5]
        freqStack.push(7); // stack: [5, 7, 5, 7]
        freqStack.push(4); // stack: [5, 7, 5, 7, 4]
        freqStack.push(5); // stack: [5, 7, 5, 7, 4, 5]

        System.out.println(freqStack.pop()); // Expected: 5
        System.out.println(freqStack.pop()); // Expected: 7
        System.out.println(freqStack.pop()); // Expected: 5
        System.out.println(freqStack.pop()); // Expected: 4
    }
}
