package leetcode.array;

public class SnakePrint {
    public static int size = 0;
    private int value = -1;
    static int[][] array = null;

    enum Direction {
        Right,
        Down,
        Left,
        Up     //有4个方向，右->下->左->上
    }

    private Direction direction = null;

    //该方法用来判断下一个位置
    private Direction detectDirection(int row, int col) {
        Direction direction1 = this.direction;
        switch (direction1) {
            case Right:
                //外环判断                 内环判断
                if ((col == size - 1) || (array[row][col + 1] != 0)) {
                    direction1 = Direction.Down;
                }
                break;
            case Down:
                if ((row == size - 1) || (array[row + 1][col] != 0)) {
                    direction1 = Direction.Left;
                }
                break;
            case Left:
                if ((col == 0) || (array[row][col - 1] != 0)) {
                    direction1 = Direction.Up;
                }
                break;
            case Up:
                if (array[row - 1][col] != 0) {
                    direction1 = Direction.Right;
                }
                break;
        }
        return direction1;
    }

    public void ArrayCount() {     //计算数组
        int row = 0, col = 0;
        for (int i = 0; i < size * size; i++) {
            array[row][col] = value;
            direction = detectDirection(row, col);
            switch (direction) {
                case Right:
                    col++;
                    break;
                case Down:
                    row++;
                    break;
                case Left:
                    col--;
                    break;
                case Up:
                    row--;
                    break;
                default:
                    return;
            }
            value++;
        }
    }

    public SnakePrint(int size) {
        if (size <= 0) {
            return;
        }
        SnakePrint.size = size;
        array = new int[size][size];
        this.value = 1;
        direction = Direction.Right;
        ArrayCount();
    }

    public static void main(String[] args) {
        SnakePrint snakePrint = new SnakePrint(6);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}