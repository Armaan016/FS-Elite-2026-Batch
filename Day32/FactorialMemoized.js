/*
Implement a JavaScript ES6 module named AdvancedFactorialTool that calculates 
factorials of numbers provided by the user. The implementation should 
integrate the following ES6 features:

1. Implement your own ES6 class named CustomMap to mimic JavaScript's built-in Map.
2. Use the above-created CustomMap explicitly for caching factorial results to 
   avoid redundant calculations.
3. Provide a deepClone function to clone the cache object deeply, protecting the 
   internal state from accidental external modifications.
4. Implement a generator named factorialGenerator(n) which yields factorial 
   results sequentially from 1! to n!.

*/


class CustomMap {
    // write your code here
    constructor() {
        this.items = {};
        this.itemsCount = 0;
    }

    has(key) {
        return this.items.hasOwnProperty(key);
    }

    get(key) {
        let newK = this.keyToString(key);

        if (!this.items.hasOwnProperty(newK)) return undefined;

        return this.items[newK];
    }

    set(key, val) {
        let newK = this.keyToString(key);
        if (!this.items.hasOwnProperty(newK)) this.itemsCount++;

        this.items[newK] = val;
    }

    remove(key) {
        let newK = this.keyToString(key);
        if (this.items.hasOwnProperty(newK)) {
            this.itemsCount--;
            delete this.items[newK];
        }
    }

    size() {
        return this.itemsCount;
    }

    keyToString(key) {
        if (typeof key === 'object') {
            return JSON.stringify(key);
        }

        return String(key);
    }

    clone() {
        let newMap = new CustomMap();
        for (let key in this.items) {
            if (this.items.hasOwnProperty(key)) {
                newMap.items[key] = this.items[key];
            }
        }

        newMap.itemsCount = this.itemsCount;
        return newMap;
    }
}


const AdvancedFactorialTool = {

    cache: new CustomMap(),

    factorialMemoized: function (n) {
        // write your code here

        // return function(...args) {
        if (this.cache.has(n)) {
            return this.cache.get(n);
        }

        // let res = this.factorialGenerator(n);
        let fact = 1;
        for (let i = 2; i <= n; i++) {
            fact *= i;
        }

        this.cache.set(n, fact);

        return fact;
        // }
    },

    deepClone(obj) {
        // write your code here

        if (typeof obj !== "object" && !Array.isArray(obj)) return;

        if (Array.isArray(obj)) {
            return obj.map(o => this.deepClone(o));
        }

        let cloned = {};

        for (let key in obj) {
            cloned[key] = this.deepClone(obj[key]);
        }

        return cloned;
    },

    *factorialGenerator(n) {
        // write your code here

        let fact = 1;
        yield 1;
        for (let i = 2; i <= n; i++) {
            fact *= i;
            yield fact;
        }

    }
};


//Testing code
function testFactorialTool(n) {
    console.log(`Factorial of ${n} (Memoized):`, AdvancedFactorialTool.factorialMemoized(n));

    const originalCache = AdvancedFactorialTool.cache;
    const clonedCache = AdvancedFactorialTool.cache.clone();
    clonedCache.set(n, "modified");
    console.log("Original cache after clone modification:", originalCache.get(n));

    console.log(`Factorial sequence up to ${n}:`, [...AdvancedFactorialTool.factorialGenerator(n)]);
}

const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

readline.question('Enter a positive integer to calculate factorial: ', input => {
    const n = parseInt(input.trim());

    if (isNaN(n) || n <= 0) {
        console.log('Please enter a valid positive integer.');
    } else {
        testFactorialTool(n);
    }

    readline.close();
});
