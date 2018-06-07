import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by szymank5 on 25.12.2017.
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[][] map;
        int mapSize;
        String msg;
        String output;
        String[] firstSplit;
        String[] secondSplit;
        String[] outSplit;
        String[] outSecSplit;
        try {
            String configMsg = reader.readLine();
            String[] configSplit = configMsg.split("_");
            if (configSplit.length > 1) {
                mapSize = Integer.parseInt(configSplit[0]);
                map = new int[mapSize][mapSize];
                for (int i = 0; i < mapSize; i++)
                    for (int j = 0; j < mapSize; j++)
                        map[i][j] = 0;
                for (int i = 1; i < configSplit.length; i++) {
                    String[] cords = configSplit[i].split("x");
                    map[Integer.parseInt(cords[0])][Integer.parseInt(cords[1])] = 1;
                }
            } else {
                mapSize = Integer.parseInt(configMsg);
                map = new int[mapSize][mapSize];
                for (int i = 0; i < mapSize; i++)
                    for (int j = 0; j < mapSize; j++)
                        map[i][j] = 0;
            }
            MoveMaker move;
            System.out.println("ok");
            String firstMsg = reader.readLine();
            if (firstMsg.toLowerCase().equals("start")) {
                move = new MoveMaker(map);
                output = move.doMove();
                outSplit = output.split("_");
                for (int i = 0; i < 2; i++) {
                    outSecSplit = outSplit[i].split("x");
                    map[Integer.parseInt(outSecSplit[0])][Integer.parseInt(outSecSplit[1])] = 1;
                }
                System.out.println(output);
            } else {
                firstSplit = firstMsg.split("_");
                for (int i = 0; i < 2; i++) {
                    secondSplit = firstSplit[i].split("x");
                    map[Integer.parseInt(secondSplit[0])][Integer.parseInt(secondSplit[1])] = 1;
                }
                move = new MoveMaker(map);
                output = move.doMove();
                outSplit = output.split("_");
                for (int i = 0; i < 2; i++) {
                    outSecSplit = outSplit[i].split("x");
                    map[Integer.parseInt(outSecSplit[0])][Integer.parseInt(outSecSplit[1])] = 1;
                }
                System.out.println(output);
            }
            while (true) {
                msg = reader.readLine();
                if (msg.toLowerCase().equals("stop"))
                    return;
                else {
                    firstSplit = msg.split("_");
                    for (int i = 0; i < 2; i++) {
                        secondSplit = firstSplit[i].split("x");
                        map[Integer.parseInt(secondSplit[0])][Integer.parseInt(secondSplit[1])] = 1;
                    }
                    move = new MoveMaker(map);
                    output = move.doMove();
                    outSplit = output.split("_");
                    for (int i = 0; i < 2; i++) {
                        outSecSplit = outSplit[i].split("x");
                        map[Integer.parseInt(outSecSplit[0])][Integer.parseInt(outSecSplit[1])] = 1;
                    }
                    System.out.println(output);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }/*
        int[][] map = new int[3][3];
        for(int i = 0 ; i < map.length ; i++)
            for(int j = 0 ; j < map.length ; j++)
                map[i][j] = 0;
        map[0][0] = 1;
        map[0][1] = 1;
        map[1][1] = 1;
        map[1][2] = 1;
        MoveMaker move = new MoveMaker(map);
        System.out.println(move.doMove());*/
    }
}
