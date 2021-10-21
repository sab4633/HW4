import java.util.Scanner;

public class IncrSubseqLengthP {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int[] nums = new int[n];
        for (int i =0; i < n; i++) { //iterate through input placing each number into the array;
            nums[i] = sc.nextInt();
        }

        int[][] opt = new int[n][p]; //tracks optimal solution up that index
        for(int i =0; i<n; i++){
            for(int j=0; j<p;j++){
                opt[i][j] = 0;
            }
        }

        for(int i =0; i<n; i++){
            opt[i][0] = nums[i];
        }




        int maxVal = 0; //track maximum value throughout
        for(int i = 1; i < n; i++){ //iterate each value of the array
            for(int j = 0; j < i; j++) { //solve for each previous index
                if (nums[j] < nums[i]) { //if value is less and better than current solution
                    for(int k = 0; k<p-1;k++){
                        if(opt[j][k]!=0){
                            if(opt[i][k+1] < (opt[j][k]+nums[i])){
                                opt[i][k+1] = (opt[j][k]+nums[i]);
                            }
                        }
                    }
                }
            }


        }

        for(int i =0; i<n; i++){
            for(int j=0; j<p;j++){
                System.out.print(opt[i][j]+ " ");
            }
            System.out.println();
        }
        for(int i =0; i<n; i++){
            if(maxVal<opt[i][p-1]){
                maxVal = opt[i][p-1];
            }
        }
        if(maxVal==0){
            System.out.println("-1");
        }else{
            System.out.println(maxVal);
        }

    }
}
