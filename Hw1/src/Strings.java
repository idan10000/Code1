public class Strings {
    public static void main(String[] args) {
        String w1 = args[0];
        String w2 = args[1];
        String w3 = args[2];
        if(w3.length() == w1.length() + w2.length() + 1) {
            if ((w3.substring(0, w1.length()).equalsIgnoreCase(w1) && w3.substring(w1.length() + 1).equalsIgnoreCase(w2)) ||
                    (w3.substring(0, w2.length()).equalsIgnoreCase(w2) && w3.substring(w1.length() + 1).equalsIgnoreCase(w1)))
                System.out.println(w3 + " is a concatenation.");
            else
                System.out.println(w3 + " is not a concatenation.");
        }
        else
            System.out.println(w3 + " is not a concatenation.");
    }
}
