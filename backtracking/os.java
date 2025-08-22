import java.util.Scanner;

public class os {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n];  // process id
        int[] at  = new int[n];  // arrival time
        int[] bt  = new int[n];  // burst time
        int[] ct  = new int[n];  // completion time
        int[] tat = new int[n];  // turnaround time
        int[] wt  = new int[n];  // waiting time

        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;  // process IDs start from 1
            System.out.print("Enter Arrival Time and Burst Time for P" + pid[i] + ": ");
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
        }

        // Sort processes by Arrival Time (simple bubble sort)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (at[j] > at[j + 1]) {
                    // swap AT
                    int temp = at[j]; at[j] = at[j + 1]; at[j + 1] = temp;
                    // swap BT
                    temp = bt[j]; bt[j] = bt[j + 1]; bt[j + 1] = temp;
                    // swap PID
                    temp = pid[j]; pid[j] = pid[j + 1]; pid[j + 1] = temp;
                }
            }
        }

        // Calculate CT, TAT, WT
        int time = 0;
        for (int i = 0; i < n; i++) {
            if (time < at[i]) {
                time = at[i];
            }
            time += bt[i];
            ct[i] = time;
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        // Print results
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                               ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        // Average TAT & WT
        double avgTAT = 0, avgWT = 0;
        for (int i = 0; i < n; i++) {
            avgTAT += tat[i];
            avgWT += wt[i];
        }
        System.out.printf("\nAverage TAT = %.2f", avgTAT / n);
        System.out.printf("\nAverage WT = %.2f\n", avgWT / n);

        sc.close();
    }
}
