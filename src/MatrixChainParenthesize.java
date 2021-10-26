import java.util.Scanner;

public class MatrixChainParenthesize {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] matrices = new int[n+1];
        for (int i =0; i <= n; i++) { //iterate through input placing each number into the array;
            matrices[i] = sc.nextInt();
        }
        int[][] opt = new int[n+1][n+1];
        for(int i =1;i<=n;i++){
            opt[i][i] = 0;
        }
        for(int d = 1; d<=n-1; d++){
            for(int i =1; i<=n-d; i++){
                int j = i+d;
                opt[i][j] = 2147483647;
                for(int k = i; k<=j-1;k++){
                    int tmp = opt[i][k]+ opt[k+1][j] +matrices[i-1]*matrices[k]*matrices[j];
                    if(opt[i][j]>tmp){
                        opt[i][j] = tmp;
                    }
                }
            }
        }
        System.out.println(opt[1][n]);
        for(int d = 1; d<=n-1; d++) {
            for (int i = 1; i <= n - d; i++) {
                int j = i + d;

                System.out.print(opt[i][j]+ " ");
            }
            System.out.println();
        }


    }
}
