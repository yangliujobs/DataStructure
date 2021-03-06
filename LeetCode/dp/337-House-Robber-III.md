## [337. House Robber III](https://leetcode.com/problems/house-robber-iii/)

The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
```
Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
```
Example 2:
```
Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
```

## Answer
### Method 1 - DFS
#### Approach 2 - :rabbit: 1ms (72.83%)
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // ============= Method 1: DFS ==============
    // 1ms (72.83%)
    public int rob(TreeNode root) {
        int[] ret = dfs(root);
        
        return Math.max(ret[0], ret[1]);
    }
    
    private int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        
        int robLeft = left[0], notRobLeft = left[1];
        int robRight = right[0], notRobRight = right[1];
        
        int robCurr = root.val + notRobLeft + notRobRight;
        int notRobCurr = Math.max(robLeft, notRobLeft) + Math.max(robRight, notRobRight);
        
        int[] ret = new int[2];
        ret[0] = robCurr;
        ret[1] = notRobCurr;
        
        return ret;
    }
}
```
#### Approach 1 - :rabbit: 4ms (53.11%)
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // ============= Method 2 ==============
    // 4ms (53.11%)
    Map<TreeNode, Integer> map;
    public int rob(TreeNode root) {
        map = new HashMap();
        return dfs(root);
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);
        
        int val = 0;
        
        if (root.left != null) {
            val += dfs(root.left.left) + dfs(root.left.right);
        }
        if (root.right != null) {
            val += dfs(root.right.left) + dfs(root.right.right);
        }
        
        val = Math.max(val + root.val, dfs(root.left) + dfs(root.right));
        
        map.put(root, val);
        
        return val;
    }
}
```
