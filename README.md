# ADVENT OF CODE 2020

This is the set of all my 2021 Advent of Code solutions. (At some point later they will be cleaned). They are all produced in java, using my (very awful) workflow tools and hacks - I significantly improved and rewrote these since last year.

#  Table of Contents
1. [Project Structure](#Project-Structure)
2. [Lore](#Lore)
3. [Problem Ratings](#Problem-Ratings)
4. [Solutions](#Solutions)
5. [Visualizations](#Visualizations)

## Project Structure
All of the solutions are available in the ```com/nbkelly/advent``` folder, and can be run using the ```run.sh``` script, like so:

1. Run with input file - ```./run.sh 01 input.txt```
2. Run with optional args, such as debug mode - ```./run.sh 01 input.txt --debug```

## Lore
Here's a brief summary of the 2021 advent of code **deep** lore.

| Problem | Plot |
| :-----: | :--- |
| Day 01  | You're minding your own business on a boat when Santa (now black) has his keys go flying over the edge. To find his keys, we start by performing a depth analysis of the ocean floor using a handy submarine. Because this measurement wasn't useful, we then need to smooth/aggregate that data.
| Day 02  | Today we learnt to pilot the submarine. The course is pre-programmed, so we just need to figure out where we are going. To make matters worse, the manual is in chinese, so we calculated the wrong number the first time.

## Problem Ratings
Here are my ratings for each problem, and what the time complexity of the solutions happens to be. If I use the letter N, it's line count (unless otherwise noted).

| Problem | Complexity (Part One) | Complexity (Part Two) | Comments |
| ------- |:---------------------:|:---------------------:|:-------- |
| Day 01  | *O(N)*                  | *O(N)*      | A good solution should read the input from a file one line at a time - this would allow processing of arbitrarily large files. <br/> Eric should have had the window size be larger, say 5 or 10, to force some of the programmers to ask "is there an easier way".
| Day 02  | *O(N)*		    | *O(N)*	  | This problem is pretty straightforward. It looks messy if you use the same data twice. I quite like this problem.

## Solutions

### Day 01: Sonar Sweep
#### Part One: Count how many times a list increments.

Simply read through the list, and note every time ```li(i) > li(i-1)```.

#### Part Two: Count how many times a sliding window (of size 3) for the list increments.

The same as above, but note every time ```li(i) > li(i-3)``` instead.

Whenever you move on the sliding window, you are adding the element at *i*, and removing the element at *i-3* from the sum of that window, so you can determine the value of that change by directly comparing those two elements.

More visually, we are making the comparison ```[i-3] + [i-2] + [i-1] < [i-2] + [i-1] + [i]```. It is trivial to see that we can factor this out to ```[i-3] < [i]```.

### Day 02: Dive!
#### Part One: Manhattan distance

Simply keep a tally of ```loc``` and ```depth```. This is a simple switch statement on your input.

#### Part Two: Manhattan product

Just keep track of an aim variable too.

This is mostly the same procedure, you just move all your state changes into the handler for ```forward``` - ```up``` and ```down``` only change your aim.

## Visualizations

Where I can, I will try to produce visualizations for the puzzles.

### Day 01: Sonar Sweep 
The seabed that we are scanning (The elf dropped the keys into marianas trench).

<img src="/images/01_out.png" alt="drawing" width="400">

### Day 02: Dive
Visualizations for part one, part two, and both overlaid.

<img src="/images/02_out_01.png" alt="drawing" width="400">

The graphs for part two and three have been scaled (vertically) down by 100x.

<img src="/images/02_out_02.png" alt="drawing" width="400">

For the graph with both overlaid, the path for part one has been scaled up 20x to be visible.

<img src="/images/02_out_03.png" alt="drawing" width="400">
