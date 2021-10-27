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
        int[][] ktable = new int[n+1][n+1];
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
                        ktable[i][j] = k;
                    }
                }
            }
        }
        System.out.println(opt[1][n]);
//        for(int d = 1; d<=n-1; d++) {
//            for (int i = 1; i <= n - d; i++) {
//                int j = i + d;
//
//                System.out.print(opt[i][j]+ " ");
//            }
//            System.out.println();
//        }
        int[] parenthL = new int[n+1];
        int[] parenthR = new int[n+1];
        int startK = ktable[1][n];
        MatrixChainParenthesize program = new MatrixChainParenthesize();

        program.recursiveParenth(parenthL, parenthR, 1, startK, ktable);
        parenthL[1]++;
        program.recursiveParenth(parenthL, parenthR, startK+1, n, ktable);
        parenthR[n]++;
        for(int i = 1; i<n+1; i++){
            for(int j = 0; j<parenthL[i]; j++){
                System.out.print("( ");
            }
            System.out.print("A"+i+" ");
            for(int j = 0; j<parenthR[i]; j++){
                System.out.print(") ");
            }
            if(i!=n){
                System.out.print("x ");
            }

        }



    }

        public void recursiveParenth(int[] parenthL, int[] parenthR, int k1, int k2, int[][] ktable){
            if(k2 - k1 == 1){
                parenthL[k1]++;
                parenthR[k2]++;
                return;
            }else if(k2-k1 == 0){
                return;
            }
            int newk = ktable[k1][k2];
            recursiveParenth(parenthL, parenthR, k1,newk,ktable);
            parenthL[k1]++;
            recursiveParenth(parenthL, parenthR, newk+1,k2,ktable);
            parenthR[k2]++;
            return;

        }
}
