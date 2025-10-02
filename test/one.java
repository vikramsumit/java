import java.io.*;
import java.util.*;

public class one {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) { System.out.println(-1); return; }
        int N = Integer.parseInt(line.trim());
        Map<String, List<List<String>>> recipes = new HashMap<>();
        Set<String> lhsSet = new HashSet<>();
        Set<String> ingredientSet = new HashSet<>();

        for (int i = 0; i < N; ++i) {
            String s = br.readLine();
            if (s == null) s = "";
            s = s.trim();
            if (s.length() == 0) { --i; continue; } 
            String[] parts = s.split("=");
            if (parts.length != 2) continue; 
            String product = parts[0].trim();
            lhsSet.add(product);
            String rhs = parts[1].trim();
            String[] toks = rhs.split("\\+");
            List<String> ing = new ArrayList<>();
            for (String t : toks) {
                String it = t.trim();
                if (it.length() > 0) { ing.add(it); ingredientSet.add(it); }
            }
            recipes.computeIfAbsent(product, k -> new ArrayList<>()).add(ing);
        }

        String target = br.readLine();
        if (target == null) { System.out.println(-1); return; }
        target = target.trim();

        Set<String> items = new HashSet<>(ingredientSet);
        items.removeAll(lhsSet);

        Set<String> potions = new HashSet<>(lhsSet);

        final int INF = Integer.MAX_VALUE / 4;
        Map<String, Integer> cost = new HashMap<>();

        
        for (String it : items) cost.put(it, 0);
        for (String p : potions) cost.put(p, INF);

        if (items.contains(target)) {
            System.out.println(0);
            return;
        }

        boolean updated = true;
        
        int iterations = 0, maxIter = Math.max(100, potions.size() * 5);
        
        while (updated && iterations++ < maxIter) {
            updated = false;
            for (Map.Entry<String, List<List<String>>> e : recipes.entrySet()) {
                String product = e.getKey();
                int best = cost.getOrDefault(product, INF);
                for (List<String> recipe : e.getValue()) {
                    int k = recipe.size();
                    if (k < 2) continue; 
                    long sum = 0;
                    boolean feasible = true;
                    for (String ing : recipe) {
                        Integer c = cost.get(ing);
                        if (c == null) {
                            c = 0;
                            cost.put(ing, 0);
                        }
                        if (c >= INF) { feasible = false; break; }
                        sum += c;
                        if (sum >= INF) { feasible = false; break; }
                    }
                    if (!feasible) continue;
                    int recipeCost = (int)sum + (k - 1); 
                    if (recipeCost < best) {
                        best = recipeCost;
                    }
                }
                if (best < cost.getOrDefault(product, INF)) {
                    cost.put(product, best);
                    updated = true;
                }
            }
        }

        int ans = cost.getOrDefault(target, INF);
        if (ans >= INF) System.out.println(-1);
        else System.out.println(ans);
    }
}


//  make 2 txt file ip1.txt and ip2.txt
//  run command: javac one.java && java one < ip1.txt and  java one < ip2.txt