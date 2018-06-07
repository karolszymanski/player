/**
 * Created by szymank5 on 25.12.2017.
 */
public class MoveMaker {

    private int movesCounter;
    private int[][] map;
    private int mapSize;
    private int[] cords = new int[2];
    private int black;
    private int white;

    public MoveMaker(int[][] map) {
        this.mapSize = map.length + 2;
        this.map = new int[this.mapSize][this.mapSize];
        for (int row = 0; row < this.mapSize; row++)
            for (int col = 0; col < this.mapSize; col++)
                this.map[col][row] = 1;
        for (int col = 1; col < this.mapSize - 1; col++)
            for (int row = 1; row < this.mapSize - 1; row++)
                this.map[col][row] = map[col - 1][row - 1];
    }

    public String doMove() {
        if (isShortAvailable() == -1)
            return putOnBorders();
        else
            return putWisely();

    }


    private String buildString(int col, int row, int tab1, int tab0, int minus1, int minus2, int minus3, int minus4) {
        StringBuilder sb = new StringBuilder();
        sb.append(col + minus1);
        sb.append('x');
        sb.append(row + minus2);
        sb.append('_');
        sb.append(tab1 + minus3);
        sb.append('x');
        sb.append(tab0 + minus4);
        return sb.toString();
    }

    private String putWisely() {
        int[] tab;
        int col = cords[0];
        int row = cords[1];
        if (countMoves(map) % 2 == 0) {
            if (map[col][row + 1] == 0) {
                tab = searchForFreeCords(map, col, row + 1, 'd');
                return buildString(col, row, tab[0], tab[1], -1, 0, -1, -1);
            }
            if (map[col][row - 1] == 0) {
                tab = searchForFreeCords(map, col, row - 1, 'u');
                return buildString(col, row, tab[0], tab[1], -1, -2, -1, -1);
            }
            if (map[col + 1][row] == 0) {
                tab = searchForFreeCords(map, col + 1, row, 'l');
                return buildString(col, row, tab[0], tab[1], 0, -1, -1, -1);
            }
            if (map[col - 1][row] == 0) {
                tab = searchForFreeCords(map, col - 1, row, 'r');
                return buildString(col, row, tab[0], tab[1], -2, -1, -1, -1);
            }
        } else {
            tab = searchForFreeCords(map, col, row, 'z');
            return buildString(col, row, tab[0], tab[1], -1, -1, -1, -1);
        }
        return "Failure";
    }



    private int[] searchForFreeCords(int[][] map, int col, int row, char key) {
        int[] tab = new int[2];
        if (map[col][row + 1] == 0 && key != 'u') {
            tab[0] = col;
            tab[1] = row + 1;
            return tab;
        }
        if (map[col][row - 1] == 0 && key != 'd') {
            tab[0] = col;
            tab[1] = row - 1;
            return tab;
        }
        if (map[col + 1][row] == 0 && key != 'r') {
            tab[0] = col + 1;
            tab[1] = row;
            return tab;
        }
        if (map[col - 1][row] == 0 && key != 'l') {
            tab[0] = col - 1;
            tab[1] = row;
            return tab;
        }
        return searchForFreeCords(map, col, row, 'z');
    }

    private String putOnBorders() {
        for (int col = 1; col < mapSize - 1; col++)
            for (int row = 1; row < mapSize - 1; row++)
                if (countMovesFromField(map, col, row) > 0) {
                    if (map[col][row] == 0 && map[col][row + 1] == 0) {
                        return buildString(col, row, col, row, -1, -1, -1, 0);
                    }
                    if (map[col][row] == 0 && map[col][row - 1] == 0) {
                        return buildString(col, row, col, row, -1, -1, -1, -2);
                    }
                    if (map[col][row] == 0 && map[col + 1][row] == 0) {
                        return buildString(col, row, col, row, -1, -1, 0, -1);
                    }
                    if (map[col][row] == 0 && map[col - 1][row] == 0) {
                        return buildString(col, row, col, row, -1, -1, -2, -1);
                    }
                }
        return "Failure";
    }

    private int countMoves(int[][] map) {
        black = 0;
        white = 0;
        for (int col = 1; col < mapSize - 1; col++)
            for (int row = 1; row < mapSize - 1; row++) {
                if (countMovesFromField(map, col, row) > 0) {
                    if ((col - row) % 2 == 0)
                        black++;
                    else
                        white++;
                }
            }
        if (black > white)
            return white;
        else
            return black;
    }

    private int countMovesFromField(int[][] map, int col, int row) {
        movesCounter = 0;
        if (map[col][row + 1] == 0)
            movesCounter++;
        if (map[col][row - 1] == 0)
            movesCounter++;
        if (map[col + 1][row] == 0)
            movesCounter++;
        if (map[col - 1][row] == 0)
            movesCounter++;
        return movesCounter;
    }

    private int isShortAvailable() {
        for (int col = 1; col < mapSize - 1; col++) {
            for (int row = 1; row < mapSize - 1; row++) {
                movesCounter = 0;
                if (map[col][row] == 0 && map[col][row + 1] == 0)
                    movesCounter++;
                if (map[col][row] == 0 && map[col][row - 1] == 0)
                    movesCounter++;
                if (map[col][row] == 0 && map[col + 1][row] == 0)
                    movesCounter++;
                if (map[col][row] == 0 && map[col - 1][row] == 0)
                    movesCounter++;
                if (movesCounter == 1) {
                    cords[0] = col;
                    cords[1] = row;
                    return 1;
                }
            }
        }
        return -1;
    }
}
