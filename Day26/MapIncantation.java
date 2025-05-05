package Day26;

// Imagine you're an adventurer with a mystical treasure map. This map is a grid of 
// ancient runes, where each cell holds a single character. Legend has a 
// powerful incantation—represented as a string—is hidden within these runes. 
// To unlock the treasure, you must verify if the incantation exists on the map.

// The incantation is formed by linking runes that are directly next to each other 
// either horizontally or vertically. Each rune on the map can only be used once in
// the incantation.

// Your Task:  
// Given an m x n grid representing the treasure map and a string representing the 
// incantation, return true if the incantation can be traced on the map; 
// otherwise, return false.


// Example 1:
// ----------
// Input:  
// 3 4
// ABCD
// SFCS
// ADEE
// ABCCED

// Output:
// ABCCED can be traced

// Explanation (check hint)
// Treasure Map Grid:  
// [
//   ["A", "B", "C", "E"],
//   ["S", "F", "C", "S"],
//   ["A", "D", "E", "E"]
// ]

// Incantation: "ABCCED" exists in map


// Example 2:
// ----------
// Input:
// 3 4
// ABCE
// SFCS
// ADEE
// ABCB

// Output: 
// ABCB cannot be traced

// Explanation:
// Treasure Map Grid:  

// [
//   ["A", "B", "C", "E"],
//   ["S", "F", "C", "S"],
//   ["A", "D", "E", "E"]
// ]

// Incantation: "ABCB" does not exist in map


// Constraints:

// - m == the number of rows in the grid  
// - n == the number of columns in the grid  
// - 1 <= m, n <= 6  
// - 1 <= incantation length <= 15  
// - The grid and incantation consist only of uppercase and lowercase English letters.

import java.util.*;

public class MapIncantation {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int m = sc.nextInt();
        int n = sc.nextInt();
        
        sc.nextLine();
        char[][] board = new char[m][n];
        for(int i = 0; i < m; i++) {
            String s = sc.nextLine();
            for(int j = 0; j < n; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        
        
        String word = sc.nextLine();
        exist(board, word);

        sc.close();
    }
    
    public static boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        // boolean res = searchWord(board, word, 0, 0, 0, visited);
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(searchWord(board, word, i, j, 0, visited)) {
                    System.out.println(word + " can be traced");
                    return true;
                }
            }
        }
        
        System.out.println(word + " cannot be traced");

        return false;
    }
    
    private static boolean searchWord(char[][] board, String word, int i, int j, int idx, boolean[][] visited) {
        if(idx == word.length()) return true;
        
        if(idx > word.length()) return false;
        
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) return false;
        
        if(board[i][j] != word.charAt(idx)) return false;
        
        visited[i][j] = true;
        
        boolean res = searchWord(board, word, i - 1, j, idx + 1, visited)  || 
                searchWord(board, word, i + 1, j, idx + 1, visited) ||
                searchWord(board, word, i, j - 1, idx + 1, visited) ||
                searchWord(board, word, i, j + 1, idx + 1, visited);
                
        visited[i][j] = false;
        
        return res;
    }
}