import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Dice {
    public static void main(String args[]){
        int dice1=6,dice2=6;//
        int total_combinations=dice1*dice2;
        System.out.println("The total combinations of dice faces possible when both the dices are rolled :"+total_combinations);
        int[][] combination_distribution=new int[6][6];
        combination_distribution=findcombinations(combination_distribution);
        printmatrix(combination_distribution);
        double dp[]=probabilty(total_combinations,combination_distribution);
        List<int[]> diceACombos = generateDiceACombinations();
        List<int[]> diceBCombos = generateDiceBCombinations();
        checkprobabilities(diceACombos,diceBCombos,dp);


    }
    public static void checkprobabilities(List<int[]> diceacombos,List<int[]> dicebcombos,double[] dp){
        for(int[] arr1:diceacombos){
            for(int[] arr2:dicebcombos){
                int flag=0;
                  int[] dp1=new int[13];
                   int[] a=arr1;
                   int[] b=arr2;
                   
                   for(int n:a){
                    for(int m:b){

                        dp1[n+m]++;
                    }
                   }
                   for(int i=2;i<=12;i++){
                  
                    if((int)dp[i]!=dp1[i]){
                        flag=1;
                        break;
                        
                    }
                   }
                   if(flag==0){
                    printlist(a,b);
                    break;
                   }
            }
        }
    }
    public static void printlist(int[] a,int[] b){
         System.out.print("The new dice a sides are: [");
         for(int i:a){
            System.out.print(i+ " ");
         }
         System.out.print("]");
         System.out.println();
         System.out.print("The new dice b sides are: [");
         for(int i:b){
            System.out.print(i+ " ");
         }
         System.out.print("]");
         System.out.println();
         System.out.println();  System.out.println();
    }
    public static int[][] findcombinations(int combination_distribution[][]){
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                combination_distribution[i][j]=(i+1)+(j+1);
            }
        }
        return combination_distribution;
    }
    public static void printmatrix(int[][] combination_distribution){
       
        for(int i=0;i<combination_distribution.length;i++){
            for(int j=0;j<combination_distribution[0].length;j++){
                System.out.print(combination_distribution[i][j] + " ");
             }
             System.out.println();
        }
    }
    public static double[] probabilty(int total_combinations,int[][] combination_distribution){
        int min_sum=2; //1+1; when dice 1 is 1 and dice 2 is 1
        int max_sum=12; //6+6 when dice 1 is 6 and dice 2 is 6
        
        double[] dp=new double[13];
        double total=total_combinations;
            for(int i=0;i<combination_distribution.length;i++){
                for(int j=0;j<combination_distribution[0].length;j++){
                    dp[combination_distribution[i][j]]++;
                }
            }
            System.out.println("Probablilites for sums :");
            for(int i=min_sum;i<=max_sum;i++){
                  
                  double prob=dp[i]/total;
                
                  System.out.println(i+" : "+dp[i]+" / "+total+"  = "+prob);
        
            }
            return dp;
        }
        
        static List<int[]> generateDiceACombinations() {
            List<int[]> combos = new ArrayList<>();
            generateDiceA(new int[6], 0, combos);
            return combos;
        }
    
        static void generateDiceA(int[] curr, int index, List<int[]> combos) {
            if (index == 6) {
                combos.add(curr.clone());
                return;
            }
    
            if (index == 0 ) {
                curr[index] = 1;
                generateDiceA(curr, index + 1, combos);
            }
            if(index == 4){
                curr[index] = 4;
                generateDiceA(curr, index + 1, combos);}

             else {
                curr[index] = 2;
                generateDiceA(curr, index + 1, combos);
                curr[index] = 3;
                generateDiceA(curr, index + 1, combos);
            }
        }
    
        static List<int[]> generateDiceBCombinations() {
            List<int[]> combos = new ArrayList<>();
            generateDiceB(new int[6], 0, combos);
            return combos;
        }
        static void generateDiceB(int[] curr, int index, List<int[]> combos) {
            if (index == 6) {
                combos.add(curr.clone());
                return;
            }
    
            if (index == 0) {
                curr[index] = 1;
                generateDiceB(curr, index + 1, combos);
            } else if (index == 1) {
                curr[index] = 8;
                generateDiceB(curr, index + 1, combos);
            } else {
                for (int i = 1; i <= 7; i++) {
                    final int finalI = i;
                    if (Arrays.stream(curr).noneMatch(x -> x == finalI)) {
                        curr[index] = i;
                        generateDiceB(curr, index + 1, combos);
                    }
                }
            }
        }    

   

        

   
}


