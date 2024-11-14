import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AdvertisementBudgetAllocation {
    public static int maxAudienceReach(int[] costs, int[] reaches, int budget) {
        int n = costs.length;
        int[][] dp = new int[n + 1][budget + 1];
        for (int i = 1; i <= n; i++) {                 
            for (int b = 0; b <= budget; b++) {          
                if (costs[i - 1] <= b) {                
                    dp[i][b] = Math.max(
                        reaches[i - 1] + dp[i - 1][b - costs[i - 1]],
                        dp[i - 1][b]       
                    );
                } else {
                    dp[i][b] = dp[i - 1][b];
                }
            }
        }
        List<Integer> selectedChannels = getSelectedChannels(dp, costs, budget);
        System.out.println("Channels selected for maximum audience reach: " + selectedChannels);
        return dp[n][budget]; 
    }
    private static List<Integer> getSelectedChannels(int[][] dp, int[] costs, int budget) {
        List<Integer> selectedChannels = new ArrayList<>();
        int n = dp.length - 1;
        int b = budget;
        for (int i = n; i > 0 && b > 0; i--) {
            if (dp[i][b] != dp[i - 1][b]) {         
                selectedChannels.add(i);             
                b -= costs[i - 1];                   
            }
        }
        return selectedChannels;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the total advertising budget: ");
        int budget = scanner.nextInt();
        System.out.print("Enter the number of advertising channels: ");
        int n = scanner.nextInt();
        int[] costs = new int[n];
        int[] reaches = new int[n];
        System.out.println("Enter cost and audience reach for each channel:");
        for (int i = 0; i < n; i++) {
            System.out.print("Channel " + (i + 1) + " - Cost: ");
            costs[i] = scanner.nextInt();
            System.out.print("Channel " + (i + 1) + " - Audience Reach: ");
            reaches[i] = scanner.nextInt();  
        }
        int maxReach = maxAudienceReach(costs, reaches, budget);
        System.out.println("Maximum audience reach achievable = " + maxReach);
        scanner.close();
    }
}
