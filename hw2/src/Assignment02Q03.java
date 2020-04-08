public class Assignment02Q03 {
    public static void main(String[] args) {
        int n1 = 1, n2 = 1, temp;
        long sum = 1;
        System.out.println("The first " + args[0] + " Fibonacci numbers are:");
        System.out.print(n1 + " ");
        for (int i = 0; i < Integer.parseInt(args[0]) - 1; i++) {
            sum += n2;
            System.out.print(n2 + " ");
            temp = n2;
            n2 += n1;
            n1 = temp;
        }
        System.out.println("\nThe sum is: "+ sum);
    }
}
