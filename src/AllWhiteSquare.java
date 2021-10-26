//Author: Sofia Bzhilyanskaya (sab4633@rit.edu)
/**
 * HW4-3 Given given a matrix filled with black squares and white squares this program dynamically finds the largest
 * white square in the grid.
 */

import java.util.Scanner;

public class AllWhiteSquare {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] mat = new char[n][n];
        int[][] opt = new int[n][n];
        int max = 0;
        sc.nextLine();
        for (int i = 0; i < n; i++) { //iterate through input placing each number into the matrix;
            String row = sc.nextLine(); //get row
            char[] rowChar = row.toCharArray(); //get individual elements of row
            for(int j =0; j<n; j++){
                mat[i][j] = rowChar[j];
            }


        }
        for (int i = 0; i < n; i++) { //outer loop
            for (int j = 0; j < n; j++) { //inner loop
                if (mat[i][j] == 'b') { //if its a black then its 0
                    opt[i][j] = 0;
                } else {
                    int min = n; //finding the minimum values in the left adjacent squares
                    if (i != 0) { //if its not the first row
                        if (opt[i - 1][j] < min) { //check above
                            min = opt[i - 1][j];
                        }
                    }

                    if (j != 0) { //if its not first column
                        if (opt[i][j - 1] < min) { //check left
                            min = opt[i][j - 1];
                        }
                    }
                    if (i != 0 && j != 0) { //if its not [0][0]
                        if (opt[i - 1][j - 1] < min) {
                            min = opt[i - 1][j - 1]; //check diagonal
                        }
                    }
                    if((i == 0 || j == 0)){ //if either are 0 set min to 0
                        min = 0;
                    }
                    opt[i][j] = min + 1; //add 1 to make a square
                    if (opt[i][j] > max) {
                        max = opt[i][j];
                    }

                }
            }

        }

        System.out.println(max);
    }

}
