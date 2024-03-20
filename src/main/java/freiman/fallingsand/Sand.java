package freiman.fallingsand;

import java.util.Random;
import java.util.Scanner;

public class Sand {

    private final int[][] field;

    private final Random random;

    public Sand(int width, int height) {
        field = new int[height][width];
        this.random = new Random();
    }

    public Sand(int width, int height, Random random) {
        field = new int[height][width];
        this.random = random;
    }

    public static void main(String[] args) {
        Sand sand = new Sand(50, 10);
        sand.randomSand(50);
        System.out.println("Press enter to make sand fall or any key to quit.");
        System.out.println(sand.toString());
        //Take user input
        Scanner s = new Scanner(System.in);
        String key = s.nextLine();
        do {
            if (key.isEmpty()) {
                sand.fall();
                System.out.println(sand.toString());
            }
            key = s.nextLine();
        } while (key.isEmpty());
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                builder.append(field[y][x]);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    /**
     * @return the value in field
     */
    public int get(int x, int y) {
        return field[y][x];
    }

    /**
     * @return the field
     */
    public int[][] getField() {
        return field;
    }

    /**
     * Sets the value in field to be 1
     */

    public void put(int x, int y) {
        field[y][x] = 1;
    }

    /**
     * makes sand fall
     */
    public void fall() {
        // moves all sand down one square
        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[0].length; x++) {
                if (field[y][x] == 1) {
                    if (field[y + 1][x] == 0) {
                        // does the sand fall straight down?
                        field[y][x] = 0;
                        field[y + 1][x] = 1;
                        continue;
                    }

                    boolean rightFirst = random.nextBoolean();
                    int direction1 = rightFirst ? +1 : -1;
                    int direction2 = rightFirst ? -1 : +1;


                    if (x + direction1 >= 0 && x + direction1 < field[x].length
                            && field[y + 1][x + direction1] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x + direction1] = 1;
                    } else if (x + direction2 >= 0 && x + direction2 < field[x].length
                            && field[y + 1][x + direction2] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x + direction2] = 1;
                    }
                }
            }
        }
    }

    /**
     * places n amount of sand in the grid in random places
     */
    public void randomSand(int n) {
        for (int i = 0; i < n; i++) {
            int x;
            int y;
            do {
                x = random.nextInt(field[0].length);
                y = random.nextInt(field.length);
            } while (field[y][x] == 1);
            field[y][x] = 1;
        }
    }
}


