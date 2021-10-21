import java.util.Scanner;

public class KnapsackWeightBoundsWithSolution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w1 = sc.nextInt();
        int w2 = sc.nextInt();
        int[] weights = new int[n];
        int[] costs = new int[n];
        for (int i = 0; i < n; i++) { //iterate through input placing each number into the array;
            weights[i] = sc.nextInt();
            costs[i] = sc.nextInt();
        }
        int[][] opt = new int[n+1][w2+1];
        for(int i = 0; i<=w2; i++){
            opt[0][i] = 0;
        }
        for(int i = 0; i<=n; i++){
            opt[i][0] = 0;
        }
        for(int v = 1; v<=w2;v++){
            for(int j= 1;j<=n;j++){
                opt[j][v] = opt[j-1][v];
                if(weights[j-1]<=v && opt[j-1][v-weights[j-1]]+costs[j-1] > opt[j][v]){
                    opt[j][v] = opt[j-1][v-weights[j-1]]+costs[j-1];

                }
            }
        }

//        for(int i =0; i<=n; i++){
//            for(int j=0; j<=w2;j++){
//                System.out.print(opt[i][j]+" ");
//            }
//            System.out.println();
//        }
        System.out.println(opt[n][w2]);
    }
}
