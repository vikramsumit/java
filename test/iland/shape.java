import java.io.*;
import java.util.*;

public class shape {
    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; ++i)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb)
                return;
            if (rank[pa] < rank[pb])
                parent[pa] = pb;
            else if (rank[pb] < rank[pa])
                parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        CallableInt nextInt = new CallableInt(br);

        int N;
        try {
            N = nextInt.get();
        } catch (NoSuchElementException e) {
            System.out.println(0);
            return;
        }

        Map<Long, Integer> id = new HashMap<>(2 * N);
        int maxVertices = 2 * N;
        int[] a = new int[N], b = new int[N];
        int idx = 0;

        for (int i = 0; i < N; ++i) {
            int x1 = nextInt.get();
            int y1 = nextInt.get();
            int x2 = nextInt.get();
            int y2 = nextInt.get();
            long p1 = pack(x1, y1);
            long p2 = pack(x2, y2);
            if (!id.containsKey(p1))
                id.put(p1, idx++);
            if (!id.containsKey(p2))
                id.put(p2, idx++);
            a[i] = id.get(p1);
            b[i] = id.get(p2);
        }

        if (N == 0) {
            System.out.println(0);
            return;
        }

        DSU dsu = new DSU(maxVertices);
        for (int i = 0; i < N; ++i)
            dsu.union(a[i], b[i]);

        boolean[] seenRoot = new boolean[idx];
        int components = 0;
        for (int v = 0; v < idx; ++v) {
            int r = dsu.find(v);
            if (!seenRoot[r]) {
                seenRoot[r] = true;
                components++;
            }
        }

        int V = idx;
        int E = N;
        int result = E - V + components;
        if (result < 0)
            result = 0;
        System.out.println(result);
    }

    static long pack(int x, int y) {
        return (((long) x) << 32) | (y & 0xffffffffL);
    }

    static class CallableInt {
        BufferedReader br;
        StringTokenizer st = null;

        CallableInt(BufferedReader br) {
            this.br = br;
        }

        int get() throws Exception {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null)
                    throw new NoSuchElementException();
                st = new StringTokenizer(line);
            }
            return Integer.parseInt(st.nextToken());
        }
    }
}
