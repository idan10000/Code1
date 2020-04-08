package il.ac.tau.cs.sw1.hw3;


import java.util.Arrays;
import static il.ac.tau.cs.sw1.hw3.ArrayUtils.*;

class ArrayUtilsTest {
    public static void main(String[] args) {
        System.out.println(alternateSum(new int[] {10,4,23,-4,-2,-3,16,1,3}));
        System.out.println(alternateSum(new int[] {19,-42,30,8,7,0,33,22,1,2,1}));

    }

    private static void testVisit() {
        System.out.println("findPath, first test: " +
                (findPath(new int[][]
                                {{1,0,0},
                                {0,1,0},
                                {0,0,1}},1,1) == 1));
        System.out.println("findPath, second test: " +
                (findPath(new int[][]
                                {{1,0,0,1},
                                {0,1,0,1},
                                {0,0,1,0},
                                {1,1,0,1}},0,1) == 1));
        System.out.println("findPath, third test: " +
                (findPath(new int[][]
                                {{1,1,0},
                                {0,1,1},
                                {0,1,1}},0,2) == 1));

        System.out.println("findPath, fourth test: " +
                (findPath(new int[][]
                                {{1,1,0},
                                {0,1,1},
                                {0,1,1}},2,0) == 0));
        System.out.println("findPath, fifth test: " +
                (findPath(new int[][]
                                {{1,0,0,1},
                                {0,1,0,1},
                                {0,0,1,0},
                                {1,1,0,1}},0,2) == 0));
    }

    private static void testSum() {
        System.out.println("alternateSum, first test: " + (alternateSum(new int[] {1, -2, 3, 4, 5}) == 7));
        System.out.println("alternateSum, second test: " + (alternateSum(new int[] {1, 2, -3, 4, 5}) == 9));
        System.out.println("alternateSum, third test: " + (alternateSum(new int[] {}) == 0));
    }

    private static void testShift() {
        System.out.println("shiftArrayCyclic, first test: " + Arrays.equals(
                shiftArrayCyclic(new int[] {1, 2, 3, 4, 5}, -1, 'R'),
                new int[] {1, 2, 3, 4, 5}
        ));
        System.out.println("shiftArrayCyclic, second test: " + Arrays.equals(
                shiftArrayCyclic(new int[] {1, 2, 3, 4, 5}, 1, 'R'),
                new int[] {5, 1, 2, 3, 4}
        ));
        System.out.println("shiftArrayCyclic, third test: " + Arrays.equals(
                shiftArrayCyclic(new int[] {1, 2, 3, 4, 5}, 1, 'r'),
                new int[] {1, 2 ,3 ,4 ,5}
        ));
        System.out.println("shiftArrayCyclic, fourth test: " + Arrays.equals(
                shiftArrayCyclic(new int[] {1, 2, 3, 4, 5}, 1, 'g'),
                new int[] {1,2 ,3 ,4 ,5}
        ));
        System.out.println("shiftArrayCyclic, fifth test: " + Arrays.equals(
                shiftArrayCyclic(new int[] {1, 2 ,3 ,4 ,5}, 3, 'L'),
                new int[] {4, 5, 1, 2, 3}
        ));
        System.out.println("shiftArrayCyclic, sixth test: " + Arrays.equals(
                shiftArrayCyclic(new int[] {0, 8, 9, 5, 6}, 6, 'L'),
                new int[] {8, 9, 5, 6, 0}
        ));
        System.out.println("shiftArrayCyclic, seventh test: " + Arrays.equals(
                shiftArrayCyclic(new int[] {}, 3, 'R'),
                new int[] {}
        ));
    }

    private static void testTranspose() {
        System.out.println("transposeSecondaryMatrix, first test: " + Arrays.deepEquals(
                transposeSecondaryMatrix(new int[][]
                        {{1,2,3},
                        {4,5,6},
                        {7,8,9}}),
                new int[][]
                        {{9,6,3},
                        {8,5,2},
                        {7,4,1}}));
        System.out.println("transposeSecondaryMatrix, second test: " + Arrays.deepEquals(
                transposeSecondaryMatrix(new int[][]
                        {{-1,8},
                        {7,-3}}),
                new int[][]
                        {{-3,8},
                        {7,-1}}));
        System.out.println("transposeSecondaryMatrix, third test: " + Arrays.deepEquals(
                transposeSecondaryMatrix(new int[][]
                        {{5,3,2}}),
                new int[][]
                        {{2},
                        {3},
                        {5}}));
        System.out.println("transposeSecondaryMatrix, fourth test: " + Arrays.deepEquals(
                transposeSecondaryMatrix(new int[][]
                        {{1,2,3},
                        {4,5,6}}),
                new int[][]
                        {{6,3},
                        {5,2},
                        {4,1}}));
        System.out.println("transposeSecondaryMatrix, fifth test: " + Arrays.deepEquals(
                transposeSecondaryMatrix(new int[][] {}),
                new int[][] {}));
        System.out.println("transposeSecondaryMatrix, sixth test: " + Arrays.deepEquals(
                transposeSecondaryMatrix(new int[][] {{}}),
                        new int[][] {{}}));



    }
}