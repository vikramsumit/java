import java.io.*;
import java.util.*;

public class island {
    static int M, N;
    static char[][] grid;
    // directions: 0 = up, 1 = right, 2 = down, 3 = left
    static final int[] dr = { -1, 0, 1, 0 };
    static final int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        grid = new char[M][N];
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                String tok = st.nextToken();
                // accept either single char or escaped backslash input
                if (tok.equals("\\"))
                    grid[i][j] = '\\';
                else
                    grid[i][j] = tok.charAt(0);
            }
        }

        int totalStates = M * N * 4;
        boolean[] processed = new boolean[totalStates]; // states fully resolved
        int[] indexInPath = new int[totalStates]; // -1 if not in current path
        Arrays.fill(indexInPath, -1);

        int maxCellsInCycle = 0;

        for (int r = 0; r < M; ++r) {
            for (int c = 0; c < N; ++c) {
                for (int d = 0; d < 4; ++d) {
                    int startId = id(r, c, d);
                    if (processed[startId])
                        continue;

                    List<Integer> path = new ArrayList<>();
                    int curR = r, curC = c, curD = d;
                    while (true) {
                        int sid = id(curR, curC, curD);
                        if (processed[sid]) {
                            for (int s : path)
                                processed[s] = true;
                            break;
                        }
                        if (indexInPath[sid] != -1) {

                            int startIndex = indexInPath[sid];

                            HashSet<Integer> uniqueCells = new HashSet<>();
                            for (int k = startIndex; k < path.size(); ++k) {
                                int stateId = path.get(k);
                                int cellIndex = (stateId / 4); 
                                uniqueCells.add(cellIndex);
                            }
                            maxCellsInCycle = Math.max(maxCellsInCycle, uniqueCells.size());

                            for (int s : path)
                                processed[s] = true;
                            for (int s : path)
                                indexInPath[s] = -1;
                            break;
                        }
                        indexInPath[sid] = path.size();
                        path.add(sid);

                        char ch = grid[curR][curC];
                        if (ch == '/') {
                            curD = curD ^ 1; 
                        } else if (ch == '\\') {
                            curD = 3 - curD; 
                        }

                        int nr = curR + dr[curD];
                        int nc = curC + dc[curD];

                        if (nr < 0 || nr >= M || nc < 0 || nc >= N) {
                            for (int s : path) {
                                processed[s] = true;
                                indexInPath[s] = -1;
                            }
                            break;
                        }

                        curR = nr;
                        curC = nc;
                    }
                }
            }
        }

        System.out.println(maxCellsInCycle);
    }

    static int id(int r, int c, int d) {
        return ((r * N + c) << 2) | d;
    }
}

// make a txt file ip1.txt with input
// run: java island < ip1.txt