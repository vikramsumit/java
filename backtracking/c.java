public class c {
    public static int findpermutation(String str, String ans){
        //base case
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
            
        }
        int count = 0;
        //Recursion
        for(int i = 0; i<str.length(); i++){
            char curr = str.charAt(i);

            String newstr = str.substring(0, i) + str.substring(i+1);
            count += findpermutation(newstr, ans + curr);

        }
        return count;
    }
    public static void main(String[] args) {
        String str = "spdf";
        int total = findpermutation(str, " ");
        System.out.println("Total no of permutation is: " + total);
    }
} 
