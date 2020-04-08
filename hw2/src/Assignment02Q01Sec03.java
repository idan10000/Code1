public class Assignment02Q01Sec03 {
    public static void main(String[] args) {
        int curNum;
        int amount = 0;
        for (int i = 0; i < args.length; i++) {
            curNum = Integer.parseInt(args[i]);
            if ((curNum % 3) % 2 != 0) {
                amount++;
            }
        }
        System.out.println(amount);
    }
}
