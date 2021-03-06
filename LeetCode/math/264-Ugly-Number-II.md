## [264. Ugly Number II](https://leetcode.com/problems/ugly-number-ii/)

Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:
```
Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
```
Note:  

- 1 is typically treated as an ugly number.
- n does not exceed 1690.

## Answer
### Method 2 - DP - :rocket: 2ms (98.37%)
```java
public int nthUglyNumber(int n) {
    if(n==0 || n==1)return n;
    int i2 = 0, i3 = 0, i5 = 0;  // current index for 2n, 3n, 5n
    int[] u = new int[n];
    int next2 = 2;
    int next3 = 3;
    int next5 = 5;
    u[0] = 1;

    for (int i = 1; i < n; i++) {
        int minV = Math.min(Math.min(next2, next3), next5);
        u[i] = minV;

        if (minV == next2) {
            next2 = u[++i2] * 2;    // update i2 to point to next num to multiply by 2
        }
        // continue check minV to skip same value
        if (minV == next3) {
            next3 = u[++i3] * 3;
        }

        if (minV == next5) {
            next5 = u[++i5] * 5;
        }

    }

    return u[n - 1];
}
```
### Method 1 - Heap - :turtle: 67ms (11.71%)
```java
class Solution {
    public int nthUglyNumber(int n) {
        //return naive(n);
        return heap(n);
        //return elegent(n);
    }
    
    // =========== Method 1: Heap ==========
    // 67ms (11.71%)
    private int heap(int n) {
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> pq = new PriorityQueue();
        
        pq.add(1L);
        
        long top = 1;
        
        while (n > 0) {
            top = pq.poll();
            long x = top * 2;
            long y = top * 3; 
            long z = top * 5;
            if(!set.contains(x)) {
                pq.offer(x);
                set.add(x);
            }
            if(!set.contains(y)) {
                pq.offer(y);
                set.add(y);
            }
            if(!set.contains(z)) {
                pq.offer(z);
                set.add(z);
            }
            n--;
        }
        
        return (int)top;
        
    }
    
//     /*Naive solution*/
//     private int naive(int n) {
//         int i = 1;  //current number
//         int cnt = 1;
        
//         while (cnt < n) {
//             i++;
//             if (isUgly(i)) cnt++;
//         }
        
//         return i;
//     }
    
//     private boolean isUgly(int n) {
//         n = getDivide(n, 5);
//         n = getDivide(n, 3);
//         n = getDivide(n, 2);
        
//         return n == 1 ? true : false;
//     }
    
//     private int getDivide(int n, int factor) {
//         while (n % factor == 0) {
//             n = n / factor;
//         }
//         return n;
//     }
}
```
