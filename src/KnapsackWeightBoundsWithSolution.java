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
        int diff = w2-w1;
        int[][] opt = new int[n+1][w2+1];
        int[][] act = new int[n+1][w2+1];
        for(int i = 0; i<=w2; i++){
            opt[0][i] = 0;
            act[0][i] = 0;
        }
        for(int i = 0; i<=n; i++){
            opt[i][0] = 0;
            act[i][0] = 0;
        }
        int maxVal = 0;
        for(int v = 1; v<=w2;v++){
            for(int j= 1;j<=n;j++){
                opt[j][v] = opt[j-1][v];
                act[j][v] = act[j-1][v];
                if(weights[j-1]<=v && opt[j-1][v-weights[j-1]]+costs[j-1] > opt[j][v]){
                    if(diff ==0 && v==weights[j-1]+ act[j-1][v-weights[j-1]]){
                        opt[j][v] = opt[j-1][v-weights[j-1]]+costs[j-1];
                        act[j][v] = act[j-1][v-weights[j-1]]+weights[j-1];
                    }else if(diff!=0){
                        opt[j][v] = opt[j-1][v-weights[j-1]]+costs[j-1];
                        act[j][v] = act[j-1][v-weights[j-1]]+weights[j-1];
                    }
                    if(act[j][v] >= w1 && act[j][v]<=w2 && opt[j][v]>maxVal){
                        maxVal=opt[j][v];
                    }

                    opt[j][v] = opt[j-1][v-weights[j-1]]+costs[j-1];
                    act[j][v] = act[j-1][v-weights[j-1]]+weights[j-1];




                }



            }
        }
//        System.out.println();
//        for(int i =0; i<=n; i++){
//            for(int j=0; j<=w2;j++){
//                System.out.print(act[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        for(int i =0; i<=n; i++){
//            for(int j=0; j<=w2;j++){
//                System.out.print(opt[i][j]+" ");
//            }
//            System.out.println();
//        }
        if(maxVal==0){
            maxVal =-1;
        }



        int val = 0;
        int indval = 0;
        for(int i = w1; i<=w2; i++){
            if(opt[n][i]>val){
                val = opt[n][i];
                indval = i;
            }
        }
        System.out.println(maxVal);
        int weight = indval;
        int j = n;
        int c = opt[j][weight];
        int[] stack = new int[n];
        int top = 0;
        while(weight>0 && j>=0){
            while(j>=0 && opt[j][weight] == c ){
                j--;
            }
            if(j>=0){
                stack[top] = j;
                top++;
                c = opt[j][weight-weights[j]];

                weight = weight-weights[j];
            }else if(j==-1){
                j++;

                weight = weight-weights[j];
            }


        }
        top--;
        while(top>=0){
            System.out.print((stack[top]+1)+" ");
            top--;
        }

    }
}
