#!/usr/bin/env node

const fs = require("fs");
const assert = require("assert");
const {
  performance,
  PerformanceObserver
} = require("perf_hooks");

const obs = new PerformanceObserver(list => {
  list.getEntries().forEach(item => console.log(item.name + ": " + item.duration));
  obs.disconnect();
});

/**
 * Reads an input file as an array by splitting it  char by char
 */
function readInputAsArray(file) {
  const fileLines = fs.readFileSync(file, "utf8").split("\r\n");
  const initialState = fileLines[0].split(/initial state: (.*)/)[1].split('');

  const rule = fileLines.splice(2).map(item => item.split(/(.....) => (.)/).filter(e => e.length > 0));
  
}

function part1(data) {
}

/**
 * Solution for part 2
 *
 * @param  {} data Input data as array
 */
function part2(data) {}

/**
 * Runners, reference tests and performance data
 */

const testdataPart01 = readInputAsArray("testdata.txt");
const realData01 = readInputAsArray("input.txt");

// Part 1
const testResult01 = part1(testdataPart01);
console.log("Test result: " + testResult01);
assert(testResult01 == 138);

// obs.observe({ entryTypes: ["function"] });
// const timerify_part1 = performance.timerify(part1);
// const realResult01 = timerify_part1(realData01);
// console.log("RESULT: " + realResult01);
// assert(realResult01 == 43351, "Good job, you broke a working solution ... ");

// Part 2
// const testResult02 = part2(testdataPart01);
// console.log("Test result: " + testResult02);
// assert(testResult02 == 66);

// obs.observe({ entryTypes: ["function"] });
// const timerify_part2 = performance.timerify(part2);
// const realResult02 = timerify_part2(realData01);
// console.log("RESULT: " + realResult02);
// assert(realResult02 == 21502, "Good job, you broke a working solution ... ");
