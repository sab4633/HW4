//Author: Sofia Bzhilyanskaya (sab4633@rit.edu)
/**
 * HW4-4 Given given n matrices this program calculates the minimum number of multiplications and the parenthetical order
 * to achieve that solution
 */
import java.util.Scanner;

public class MatrixChainParenthesize {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //number of matrices
        int[] matrices = new int[n+1]; //matrices

        for (int i =0; i <= n; i++) { //iterate through input placing each matrix into the array;
            matrices[i] = sc.nextInt();
        }
        int[][] opt = new int[n+1][n+1]; //track minimum multiplication value
        int[][] ktable = new int[n+1][n+1]; //tracks which k was used for opt array
        for(int i =1;i<=n;i++){ //set diagonal to 0
            opt[i][i] = 0;

        }
        for(int d = 1; d<=n-1; d++){ //outer loop
            for(int i =1; i<=n-d; i++){ //inner loop
                int j = i+d; //diagonal iteration
                opt[i][j] = 2147483647; //int.max
                for(int k = i; k<=j-1;k++){ //all possible multiplication sets
                    int tmp = opt[i][k]+ opt[k+1][j] +matrices[i-1]*matrices[k]*matrices[j];
                    if(opt[i][j]>tmp){ //if its smaller use it
                        opt[i][j] = tmp;
                        ktable[i][j] = k; //set k
                    }
                }
            }
        }
        System.out.println(opt[1][n]); //print min solution

        int[] parenthL = new int[n+1]; //tracks number of left parenthesis of a matrix
        int[] parenthR = new int[n+1]; //tracks number of right parenthesis of a matrix
        int startK = ktable[1][n]; //outer k split
        MatrixChainParenthesize program = new MatrixChainParenthesize();

        program.recursiveParenth(parenthL, parenthR, 1, startK, ktable); //left recursion
        parenthL[1]++;
        program.recursiveParenth(parenthL, parenthR, startK+1, n, ktable); //right recursion
        parenthR[n]++;
        for(int i = 1; i<n+1; i++){
            for(int j = 0; j<parenthL[i]; j++){ //for each left parenthesis print it
                System.out.print("( ");
            }
            System.out.print("A"+i+" "); //print matrix
            for(int j = 0; j<parenthR[i]; j++){ //for each right parenthesis print it
                System.out.print(") ");
            }
            if(i!=n){ //as long as its not the last matrix print a multiply
                System.out.print("x ");
            }

        }
    }

    /**
     * This recursive method calculates the number of parenthesis on the left and right sides of all the matrices
     * @param parenthL left parenthesis tracking array
     * @param parenthR right parenthesis tracking array
     * @param k1 min k value of split
     * @param k2 max k value of split
     * @param ktable table of all the k values
     */
        public void recursiveParenth(int[] parenthL, int[] parenthR, int k1, int k2, int[][] ktable){
            //base cases
            if(k2 - k1 == 1){ //if there are two items
                parenthL[k1]++; //add left parenthesis
                parenthR[k2]++; //add right parenthesis
                return;
            }else if(k2-k1 == 0){ //if one element
                return; //dont add a parenthesis
            }
            //if 3 or more
            int newk = ktable[k1][k2]; //find the new k value
            recursiveParenth(parenthL, parenthR, k1,newk,ktable); //left recursive call
            parenthL[k1]++; //add left parenthesis
            recursiveParenth(parenthL, parenthR, newk+1,k2,ktable); ///right recursive call
            parenthR[k2]++; //add right parenthesis
            return;

        }
}
