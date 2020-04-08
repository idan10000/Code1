public class Triangles {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        boolean flag = false;
        String input = "(" + a + "," + b + "," + c + ")";

        if(a <= 0 || b <= 0 || c <= 0)
            System.out.println("Invalid input!");
        else
            if(a*a == b*b + c*c || b*b == a*a + c*c || c*c == a*a + b*b)
                System.out.println("The input: " + input + " defines a right angle triangle!");
        else
            if(a == b || b == c || a == c)
                System.out.println("The input: " + input + " defines an isosceles triangle!");
        else System.out.println("The input: " + input + " does not define an isosceles or a right angle triangle!");
    }
}
