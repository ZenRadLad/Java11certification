package test;

public class Test {

    public static void main(String[] args) {
        int[] numbers = {1, 33, -2, 3400, 24, -29990};
        boolean b = true;
        boolean c = b ? !b : b;


        // solution works with small array (problem solving)
        // works with empty array (reliability)
        // use Java API to do binary search

        //System.out.println(findLargest(numbers));
        System.out.println(c);
    }

    private static int checkPresenceOfNumber(int[] numbers) {
        int max = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        return max;
    }
}