public class Assignment02Q01Sec02 {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            for (char ch :
                    args[i].toCharArray()) {
                    sum += ch;
            }
            System.out.println(sum);
            sum = 0;
        }
    }
}
