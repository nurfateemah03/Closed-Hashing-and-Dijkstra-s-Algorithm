import java.util.Scanner;

public class DijkstrasAlgorithm {
    static int length1 = 10;
    static int[] adjacent = new int[length1];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] matrix = { { 0, 53, 10, 12, 0, 0, 0, 0, 0, 0 }, { 53, 0, 33, 0, 2, 0, 101, 0, 0, 0 },
                { 10, 33, 0, 9, 30, 18, 0, 0, 0, 0 }, { 12, 0, 9, 0, 0, 17, 0, 0, 6, 0 },
                { 0, 2, 30, 0, 0, 14, 123, 122, 0, 0 }, { 0, 0, 18, 17, 14, 0, 0, 137, 7, 0 },
                { 0, 101, 0, 0, 123, 0, 0, 8, 0, 71 }, { 0, 0, 0, 0, 122, 137, 8, 0, 145, 66 },
                { 0, 0, 0, 6, 0, 7, 0, 145, 0, 212 }, { 0, 0, 0, 0, 0, 0, 71, 66, 212, 0 }, };

        System.out.print("Where do you want to start?");
        int start = scan.nextInt();
        System.out.print("Where do you wannt stop?");
        int stop = scan.nextInt();
        dijks(matrix, start, stop, length1);

    }

    public static void path(int nodes, int[] adjacent) {
        if (nodes == 0) {
            return;
        }
        path(adjacent[nodes], adjacent);
        System.out.print(nodes + " ");
    }

    public static int minPath(int[] distance, Boolean[] visted) {
        int min = Integer.MAX_VALUE;
        int temp = -1;

        for (int m = 0; m < length1; m++) {
            if (distance[m] < min && visted[m] == false) {
                min = distance[m];
                temp = m;
            }
        }
        return temp;

    }

    public static void dijks(int[][] matrix, int start, int stop, int length) {
        int temps = 0;
        int[] distance = new int[length];
        Boolean[] nodesVisited = new Boolean[length];

        for (int m = 0; m < length; m++) {
            if (m == start) {
                distance[m] = 0;
            } else {
                distance[m] = Integer.MAX_VALUE;
            }
            nodesVisited[m] = false;
        }

        intoit(matrix, start, stop, length, nodesVisited, distance, temps);
    }

    public static void intoit(int[][] matrix, int start, int stop, int length, Boolean[] nodesVisited, int[] distance,
            int temps) {

        for (int m = 0; m < Math.abs(length) - 1; m++) {

            int distancenode = minPath(distance, nodesVisited);
            int distance1 = distance[distancenode];
            nodesVisited[distancenode] = true;

            for (int k = 0; k < length; k++) {
                if (matrix[distancenode][k] != 0
                        && distance1 + matrix[distancenode][k] < distance[k]) {

                    adjacent[k] = distancenode;
                    distance[k] = distance1 + matrix[distancenode][k];

                }
            }
        }

        for (int m = 0; m < length; m++) {
            if (m == stop) {

                temps = distance[m];
                System.out.println("Total length is: " + temps);
            }
        }
        if (stop != start) {
            if (start == 0) {
                System.out.print(0 + " ");
                path(stop, adjacent);
            } else {
                path(stop, adjacent);
            }
        }

    }

}
