// package backtracking;

public class b {
    public static int findSubsets(String str, String ans, int i) {
        // base case
        if (i == str.length()) {
            System.out.println(ans);
            return 1;
        }
        // Recursion
        // yes choice
        int include = findSubsets(str, ans + str.charAt(i), i + 1);
        // No choice
        int exclude = findSubsets(str, ans, i + 1);

        return include + exclude;
    }

    public static void main(String[] args) {
        String str = "spdf";
        // findSubsets(str, " ", 0);
        int total = findSubsets(str, "", 0);
        System.out.println("Total subsets: " + total);
    }
}
