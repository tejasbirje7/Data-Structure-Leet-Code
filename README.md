# Data-Structure-Leet-Code

LeetCode solutions in Java, grouped by topic. Each solution is written to double as revision notes, so a problem can be re-learned from its file alone without opening LeetCode.

---

## 1. Repository layout

| Folder | What's in it |
|---|---|
| `ArraysAndStrings/` | Array and string problems |
| `Backtracking/` | Backtracking problems |
| `FAQ/` | Frequently asked interview problems from mixed topics (some also appear in a topic folder) |
| `HashMap/` | Hash map and counting problems |
| `Intervals/` | Interval merging and scheduling problems |
| `LinkedList/` | Linked list problems |
| `Math/` | Math and number problems |
| `SlidingWindow/` | Sliding window problems |
| `Stack/` | Stack problems |
| `TwoPointers/` | Two pointer problems |
| `out/` | IntelliJ build output. Not source, so ignore it. |

Conventions:

- Each topic folder is an IntelliJ module (`<Topic>.iml`) with its sources in `src/`.
- One problem per file, one public class per file.
- Files are named after the problem, in PascalCase, not after its number (e.g. a problem called "Valid Parentheses" lives in `ValidParentheses.java`).
- A follow-up or harder variant of a problem gets a numeric suffix (`ProblemName2.java`).
- A second approach to the same problem gets the approach name as a suffix (`ProblemNameCyclic.java`, `ProblemNameStack.java`). Each approach file has its own full header, and the files list each other under RELATED.
- Every class has a `main` method with a sample input, so it can be run on its own.

---

## 2. Finding a problem

**By topic:** open the matching folder from the table above.

**By name:** search file names, ignoring case:

```bash
find . -path ./out -prune -o -iname "*keyword*.java" -print
```

**By LeetCode number:** every revision header starts with `LeetCode <number>. <Title>`, so search for that line:

```bash
grep -rl --include='*.java' --exclude-dir=out "LeetCode <number>\." .
```

Older solutions may not have a header yet. If the number search finds nothing, search by name before deciding the problem isn't covered.

---

## 3. Running a solution

From IntelliJ, open the repo and run the class's `main`.

From the terminal, compile into a temporary folder so no `.class` files end up in the repo:

```bash
javac -d /tmp/lc <Topic>/src/<ProblemName>.java
java -cp /tmp/lc <ProblemName>
```

---

## 4. Revision notes: the class header

Each solution carries its explanation in **one block comment directly above the class declaration**.

### 4.1 Rules

- **Easy and point-wise.** Write every section as short bullet points, one idea per point, in simple words. Avoid paragraphs: points are faster to scan during revision and easier to remember.
- **One place only.** All the explanation goes in the class header. Don't add comments above methods or scatter them through the code. The code stays clean, and the header reads like a single page of notes.
- **Code stays unchanged.** Writing or updating the notes never changes the solution's logic.
- **Same order every time.** Use the sections below in this exact order, so every file reads the same way during revision.
- **One worked example.** Pick one input and trace it fully. A single complete trace teaches more than several partial ones.
- **Plain text, monospace-friendly.** Use upper-case section titles and two-space indents. Tables are drawn with `|`, `-` and `+` so they line up in any editor. Never write `*/` inside the comment.
- **Check the trace against the code.** Run `main` on the dry-run input and confirm the printed result matches the last row of the table.

### 4.2 Sections, in order

The header opens with the title line and the problem link, then follows these sections.

#### Title line and link

```
LeetCode <number>. <Problem Title>
https://leetcode.com/problems/<problem-slug>/
```

This first line is what makes the number search in section 2 work, so keep the format exact.

#### PROBLEM

The problem in your own words, as two to four short points:

- the input,
- what to return,
- any constraint that shapes the solution (sorted input, in place, O(1) extra space, values in a known range, and so on).

Skip the story and keep only what you need to solve it.

#### EXAMPLE

The official example from LeetCode, copied as is: `Input`, `Output` and `Explanation`. It shows exactly what LeetCode will check.

#### HOW TO THINK

**The most important section.** Explains how to reach the solution, not just what it is. Write it as one short, simple point for each of these, not as a paragraph:

- **The clue:** which words in the problem point to the technique (e.g. "sorted", "in place", "contiguous subarray", "all combinations").
- **The technique:** which pattern applies (two pointers, sliding window, hash map, stack, backtracking, and so on) and why it fits.
- **The rule that stays true:** what the variables mean at every step of the loop (e.g. "everything before index `k` is already final"). If you remember this rule, you can rebuild the code.
- **The decision:** what the loop checks at each step, and what it does in each case.
- **The return value:** what to return, and any off-by-one detail (index vs count).

#### STEPS

The algorithm in three to six numbered lines, close to the code but in plain words: setup, loop, the check inside the loop, and the return. It should be possible to write the code from this list alone.

#### DRY RUN

A full trace of one input as a table, one row per loop step. Put the input in the section title. Typical columns:

```
 DRY RUN   input = [ ... ]

  step | value(s) read | value compared against | action | pointer/state after | result so far
 ------+---------------+------------------------+--------+---------------------+--------------
   0   |               |                        |        |                     |
   1   |               |                        |        |                     |
```

- Pick columns that show the variables from HOW TO THINK changing.
- Use short actions such as `skip`, `write`, `push`, `pop`, `expand`, `shrink`.
- Say whether a column shows the value before or after the step's action.
- End with one line giving the return value and the final state.
- Choose an input that hits every branch of the loop at least once.

#### EDGE CASES

A small table of tricky inputs, the expected result, and one short reason each:

```
  <input>      -> <result>   <why>
```

Cover the usual suspects: the smallest valid input, everything the same, everything different, empty or null input, and sizes at the limits. If the code would give a wrong answer on an input the problem's constraints rule out, write that down too, so it isn't a surprise later.

#### COMMON MISTAKES

Two to four bullets about bugs that are easy to write for this problem, each with the fix: off-by-one in the return value, the order of increment vs write, comparing against the wrong element, or a shortcut that breaks a constraint (extra memory when O(1) is required). Don't list alternative solutions that are also correct.

#### COMPLEXITY

Time and extra space, with one short reason if it isn't obvious (e.g. "each element is visited at most twice").

#### RELATED

Other problems that use the same idea, ideally ones already in this repo: give the LeetCode number, the class name and where it lives, plus one line on what changes compared with this problem.

### 4.3 Template

Copy this above the class and fill it in:

```java
/**
 * LeetCode <number>. <Problem Title>
 * https://leetcode.com/problems/<problem-slug>/
 *
 * PROBLEM
 *   - Input: <...>
 *   - Return: <...>
 *   - Constraints: <the ones that shape the solution>
 *
 * EXAMPLE
 *   Input: <...>
 *   Output: <...>
 *   Explanation: <...>
 *
 * HOW TO THINK
 *   - Clue: <the words in the problem that point to the technique>
 *   - Technique: <the pattern, and why it fits>
 *   - Rule that stays true: <what the variables mean at every step>
 *   - Decision: <what the loop checks each step, and what each case does>
 *   - Return: <what to return, and any off-by-one detail>
 *
 * STEPS
 *   1. <Setup>
 *   2. <Loop>
 *   3. <Check inside the loop and what each branch does>
 *   4. <Return>
 *
 * DRY RUN   input = <...>
 *
 *    <col> | <col> | <col> | action | <col>
 *   -------+-------+-------+--------+-------
 *     ...  |  ...  |  ...  |  ...   |  ...
 *
 *   Returns <...>. Final state: <...>.
 *
 * EDGE CASES
 *   <input>   -> <result>   <why>
 *
 * COMMON MISTAKES
 *   - <Mistake>: <fix>.
 *
 * COMPLEXITY
 *   O(<...>) time, O(<...>) extra space.
 *
 * RELATED
 *   LeetCode <number> (<ClassName>, <Topic>/): <what changes compared with this problem>.
 */
public class <ProblemName> {
```

### 4.4 Checklist before committing a new solution

- [ ] File named after the problem, in the right topic folder.
- [ ] `main` runs the dry-run input.
- [ ] Header sits directly above the class, with every section in order.
- [ ] Every section is short, simple bullet points, with no paragraphs.
- [ ] Title line follows `LeetCode <number>. <Title>` exactly.
- [ ] Dry-run result matches what `main` prints.
- [ ] No explanation comments anywhere else in the file.
