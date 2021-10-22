import java.util.Scanner;

public class AllWhiteSquare {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] mat = new char[n][n];
        int[][] opt = new int[n][n];
        int max = 0;
        sc.nextLine();
        for (int i = 0; i < n; i++) { //iterate through input placing each number into the array;
            String row = sc.nextLine();
            char[] rowChar = row.toCharArray();
            for(int j =0; j<n; j++){
                mat[i][j] = rowChar[j];
            }


        }
        for (int i = 0; i < n; i++) { //iterate through input placing each number into the array;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 'b') {
                    opt[i][j] = 0;
                } else {
                    int min = n;
                    if (i != 0) {
                        if (opt[i - 1][j] < min) {
                            min = opt[i - 1][j];
                        }

                        if (j != 0) {
                            if (opt[i][j - 1] < min) {
                                min = opt[i][j - 1];
                            }
                        }
                        if (i != 0 && j != 0) {
                            if (opt[i - 1][j - 1] < min) {
                                min = opt[i - 1][j - 1];
                            }
                        }
                        if((i == 0 || j == 0)){
                            min = 0;
                        }
                        opt[i][j] = min + 1;
                        if (opt[i][j] > max) {
                            max = opt[i][j];
                        }
                    }
                }
            }

        }

        System.out.println(max);
    }

}
