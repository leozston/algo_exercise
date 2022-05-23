package offer;

/**
 * @description:
 * @author: longlonglv
 * @date: 2022/5/22
 */
public class BackTrack {
    public static void main(String[] args) {

    }
    /**
     * offer 12
     * */
    public boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    used[i][j] = true;
                    boolean r = existImp(board, used, word, 1, i, j);
                    used[i][j] = false;
                    if (r) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean existImp(char[][] board, boolean[][] used, String word, int index, int row, int column) {
        if (index == word.length()) {
            return true;
        }
        boolean up = false;
        if (row > 0 && !used[row - 1][column] && word.charAt(index) == board[row - 1][column]) {
            used[row - 1][column] = true;
            up = existImp(board, used, word, index+1, row-1, column);
            used[row - 1][column] = false;
        }
        if (up) {
            return true;
        }
        boolean down = false;
        if (row < board.length - 1 && !used[row + 1][column] && word.charAt(index) == board[row + 1][column]) {
            used[row + 1][column] = true;
            down = existImp(board, used, word, index + 1, row + 1, column);
            used[row + 1][column] = false;
        }
        if (down) {
            return true;
        }

        boolean left = false;
        if (column > 0 && !used[row][column - 1] && word.charAt(index) == board[row][column - 1]) {
            used[row][column - 1] = true;
            left = existImp(board, used, word, index + 1, row, column - 1);
            used[row][column - 1] = false;
        }
        if (left) {
            return true;
        }

        boolean right = false;
        if (column < board[0].length - 1 && !used[row][column + 1] && word.charAt(index) == board[row][column + 1]) {
            used[row][column + 1] = true;
            right = existImp(board, used, word, index + 1, row, column + 1);
            used[row][column+ 1] = false;
        }
        if (right) {
            return true;
        }
        return false;
//        注意：这里可以进行提前剪枝
//        return up || down || left || right;
    }


    /**
     * offer 13
     * */
    public static int movingCountVar = 0;
    public int movingCount(int m, int n, int k) {
        movingCountVar = 0;
        boolean[][] used = new boolean[m][n];
        movingCountImpl(m, n, 0, 0, k, used);
        return movingCountVar;
    }
    public void movingCountImpl(int m, int n, int i, int j, int k, boolean[][] used) {
        if (i < 0 || j < 0) {
            return;
        }
        if (i >= m || j >= n) {
            return;
        }
        if (used[i][j]) {
            return;
        }


        if (getMovingCountImplNum(i) + getMovingCountImplNum(j) > k) {
            return;
        }

        movingCountVar++;
        used[i][j] = true;

        movingCountImpl(m, n, i - 1, j, k, used);
        movingCountImpl(m, n, i + 1, j, k, used);
        movingCountImpl(m, n, i, j - 1, k, used);
        movingCountImpl(m, n, i, j + 1, k, used);
    }
    public int getMovingCountImplNum(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x = x / 10;
        }
        return res;
    }
}
