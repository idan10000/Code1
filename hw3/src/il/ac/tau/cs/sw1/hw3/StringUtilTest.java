package il.ac.tau.cs.sw1.hw3;

import static il.ac.tau.cs.sw1.hw3.StringUtils.*;

public class StringUtilTest {
    public static void main(String[] args) {
        testAnagram();

    }

    private static void testAnagram() {
        System.out.println("isAnagram, first test: " + isAnagram("mothEr in law","hitlEr woman"));
        System.out.println("isAnagram, second test: " + !isAnagram("ListeN","SileNt"));
        System.out.println("isAnagram, third test: " + !isAnagram("software","jeans"));
        System.out.println("isAnagram, fourth test: " + isAnagram("Funeral","real Fun"));
        System.out.println("isAnagram, fifth test: " + isAnagram("Aa","aA"));
        System.out.println("isAnagram, sixth test: " + isAnagram(""," "));
    }

    private static void testParity() {
        System.out.println("parityXorStrings, first test: " +
                parityXorStrings("dog", "god").equals(""));
        System.out.println("parityXorStrings, second test: " +
                parityXorStrings("catcatcat", "tacotaco").equals("catcatcat"));
        System.out.println("parityXorStrings, third test: " +
                parityXorStrings("catcat", "jeffjeff").equals(""));
        System.out.println("parityXorStrings, fourth test: " +
                parityXorStrings("catcat", "jeff").equals(""));
        System.out.println("parityXorStrings, fifth test: " +
                parityXorStrings("cat", "cajeffjefft").equals(""));
        System.out.println("parityXorStrings, sixth test: " +
                parityXorStrings("jeff", "catff").equals("je"));
        System.out.println("parityXorStrings, seventh test: " +
                parityXorStrings("izoi", "oziizo").equals("zo"));
        System.out.println("parityXorStrings, eight test: " +
                parityXorStrings("fireman", "maniac").equals("frea"));



    }


    private static void testSequence() {
        System.out.println("findSortedSequence, first test: " + findSortedSequence("to be or not to be").equals("not to"));
        System.out.println("findSortedSequence, second test: " + findSortedSequence("my mind is an empty zoo").equals("an empty zoo"));
        System.out.println("findSortedSequence, third test: " + findSortedSequence("").equals(""));
        System.out.println("findSortedSequence, fourth test: " + findSortedSequence("andy bought candy").equals("andy bought candy"));
        System.out.println("findSortedSequence, fifth test: " + findSortedSequence("life is not not not fair").equals("is not not not"));
        System.out.println("findSortedSequence, sixth test: " + findSortedSequence("art act").equals("act"));
    }

    

}
