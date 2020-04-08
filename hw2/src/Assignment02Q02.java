public class Assignment02Q02 {
    public static void main(String[] args) {
        int sign = 1;
        double num = 1;
        double sum = 0;
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            sum += (1 / num) * sign;
            sign *= -1;
            num += 2;
        }
        sum *= 4;
        System.out.println(sum + " " + Math.PI);
    }
}
