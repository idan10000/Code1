package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class ArrayUtils {

    public static int[][] transposeSecondaryMatrix(int[][] m) {
        if (m.length == 0 || m[0].length == 0)
            return m;
        int[][] newMat = new int[m[0].length][m.length];
        int temp;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                newMat[m[0].length - j - 1][m.length - i - 1] = m[i][j];
            }
        }
        return newMat;
    }

    public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
        if (direction != 'R' && direction != 'L' || move <= 0 || array.length == 0)
            return array;
        int moves = move % array.length;
        int[] arr = new int[array.length];
        int i;
        if (direction == 'L') {
            i = moves;
        } else {
            i = array.length - moves;
        }
        for (int j = 0; j < arr.length; j++) {
            arr[j] = array[i++ % array.length];
        }
        return arr;

    }

    public static int alternateSum(int[] array) {
        int maxSum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length; j > i; j--) {
                maxSum = Math.max(maxSum, calcAlternateSum(array, i, j));
            }
        }
        return maxSum;

    }

    private static int calcAlternateSum(int[] arr, int start, int end) {
        int sign = 1;
        int sum = 0;
        for (int i = start; i < end - start; i++) {
            sum += arr[i] * sign;
            sign *= -1;
        }
        return sum;
    }

    public static int findPath(int[][] m, int i, int j) {
        int[][] visMat = new int[m.length][m[0].length]; // a matrix that where 1 represents the places already visited, 0 otherwise
        return recFindPath(m, visMat, i, j);

    }

    private static int recFindPath(int[][] m, int[][] visited, int i, int j) {
        if (m[i][j] == 1) {
            return 1;
        }
        for (int val :
                m[i]) {
            if (val == 1 && visited[i][val] == 0) {
                visited[i][val] = 1;
                if (recFindPath(m, visited, val, j) == 1)
                    return 1;
            }
        }
        return 0;
    }


}


