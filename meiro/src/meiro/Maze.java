package meiro;                                                                                                                                                                       
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Maze {

    public static final int WALL = 0;

    public static final int PATH = 1;

    public static final int START = 2;

    public static final int GOAL = 3;

    private int[][] maze;

    private int width, height;

    private Random random;

    public Maze(int width, int height) {

        this.width = width;

        this.height = height;

        this.maze = new int[height][width];

        this.random = new Random();

        generateMaze();

    }

    public void generateMaze() {

        // 初期化

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                maze[y][x] = WALL;

            }

        }

        // スタート地点を設定

        int startX = 1;

        int startY = 1;

        maze[startY][startX] = START;

        // ゴール地点を設定

        int goalX = width - 2;

        int goalY = height - 2;

        // 深さ優先探索で迷路を生成

        Stack<int[]> stack = new Stack<>();

        stack.push(new int[]{startX, startY});

        while (!stack.isEmpty()) {

            int[] current = stack.peek();

            int x = current[0];

            int y = current[1];

            List<int[]> neighbors = new ArrayList<>();

            if (x > 2 && maze[y][x - 2] == WALL) neighbors.add(new int[]{x - 2, y});

            if (x < width - 3 && maze[y][x + 2] == WALL) neighbors.add(new int[]{x + 2, y});

            if (y > 2 && maze[y - 2][x] == WALL) neighbors.add(new int[]{x, y - 2});

            if (y < height - 3 && maze[y + 2][x] == WALL) neighbors.add(new int[]{x, y + 2});

            if (neighbors.isEmpty()) {

                stack.pop();

            } else {

                int[] next = neighbors.get(random.nextInt(neighbors.size()));

                int nx = next[0];

                int ny = next[1];

                maze[ny][nx] = PATH;

                maze[ny + (y - ny) / 2][nx + (x - nx) / 2] = PATH;

                stack.push(next);

            }

        }

        // スタートからゴールまでの経路を確保

        ensurePath(startX, startY, goalX, goalY);

        // ゴール地点を設定

        maze[goalY][goalX] = GOAL;

    }

    private void ensurePath(int startX, int startY, int goalX, int goalY) {

        Stack<int[]> stack = new Stack<>();

        stack.push(new int[]{startX, startY});

        boolean[][] visited = new boolean[height][width];

        while (!stack.isEmpty()) {

            int[] current = stack.pop();

            int x = current[0];

            int y = current[1];

            visited[y][x] = true;

            if (x == goalX && y == goalY) return;

            List<int[]> neighbors = new ArrayList<>();

            if (x > 0 && !visited[y][x - 1] && maze[y][x - 1] == PATH) neighbors.add(new int[]{x - 1, y});

            if (x < width - 1 && !visited[y][x + 1] && maze[y][x + 1] == PATH) neighbors.add(new int[]{x + 1, y});

            if (y > 0 && !visited[y - 1][x] && maze[y - 1][x] == PATH) neighbors.add(new int[]{x, y - 1});

            if (y < height - 1 && !visited[y + 1][x] && maze[y + 1][x] == PATH) neighbors.add(new int[]{x, y + 1});

            if (!neighbors.isEmpty()) {

                stack.push(current);

                int[] next = neighbors.get(random.nextInt(neighbors.size()));

                stack.push(next);

            } else {

                maze[y][x] = PATH;

            }

        }

    }

    public int getWidth() {

        return width;

    }

    public int getHeight() {

        return height;

    }

    public int getCell(int x, int y) {

        return maze[y][x];

    }

}
 