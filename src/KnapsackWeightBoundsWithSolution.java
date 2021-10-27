//Author: Sofia Bzhilyanskaya (sab4633@rit.edu)
/**
 * HW4-2 Given given n items this program finds the optimal cost between the weight w1 and w2
 */
import java.util.Scanner;

public class KnapsackWeightBoundsWithSolution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //number of items
        int w1 = sc.nextInt(); //min weight
        int w2 = sc.nextInt(); //max weight
        int[] weights = new int[n]; //all weights
        int[] costs = new int[n]; //all costs
        for (int i = 0; i < n; i++) { //iterate through input placing each number into the array;
            weights[i] = sc.nextInt();
            costs[i] = sc.nextInt();
        }
        int[][] opt = new int[n+1][w2+1]; //track optimal cost
        int[][] act = new int[n+1][w2+1]; //track respective weight of optimal cost
        //set to 0
        for(int i = 0; i<=w2; i++){
            opt[0][i] = 0;
            act[0][i] = 0;
        }
        for(int i = 0; i<=n; i++){
            opt[i][0] = 0;
            act[i][0] = 0;
        }
        int maxVal = 0; //track best cost
        int maxw = 0; //track respective weight of cost
        for(int v = 1; v<=w2;v++){
            for(int j= 1;j<=n;j++){
                opt[j][v] = opt[j-1][v]; //equal to previous
                act[j][v] = act[j-1][v];
                if(weights[j-1]<=v && opt[j-1][v-weights[j-1]]+costs[j-1] > opt[j][v]){ //checks if inclusive is better
                    if(v==weights[j-1]+ act[j-1][v-weights[j-1]]) { //checks if its equal to the weight
                        opt[j][v] = opt[j - 1][v - weights[j - 1]] + costs[j - 1]; //update cost
                        act[j][v] = act[j - 1][v - weights[j - 1]] + weights[j - 1]; //update respective weight
                    }
                    if(act[j][v] >= w1 && opt[j][v]>maxVal){ //track max
                        maxVal=opt[j][v];
                        maxw = v;
                    }
                }
            }
        }

        if(maxVal==0){ //no solution
            maxVal =-1;
        }

        System.out.println(maxVal);
        int weight = maxw;//track weight
        int j = n; //track item id
        int c = opt[j][weight];
        int[] stack = new int[n]; //record items backwards
        int top = 0; //top of stack
        while(weight>0 && j>=0){ //while not at the end
            while(j>=0 && opt[j][weight] == c ){ //iterate upwards till cost changes
                j--;
            }
            if(j>=0){
                stack[top] = j; //push id to stack
                top++; //increase stack
                c = opt[j][weight-weights[j]]; //new cost

                weight = weight-weights[j]; //new weight
            }else if(j==-1){ //out of bounds
                j++;

                weight = weight-weights[j];
            }
        }
        top--; //decrement stack
        while(top>=0){ //print all the id's
            System.out.print((stack[top]+1)+" ");
            top--;
        }

    }
}
