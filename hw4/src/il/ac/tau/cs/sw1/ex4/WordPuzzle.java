package il.ac.tau.cs.sw1.ex4;

import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
    public static final char HIDDEN_CHAR = '_';
    public static final int MAX_VOCABULARY_SIZE = 3000;


    public static String[] scanVocabulary(Scanner scanner) {          // Q - 1
        String[] unsortedWords = new String[MAX_VOCABULARY_SIZE];
        int numOfWords = 0;
        while (numOfWords < MAX_VOCABULARY_SIZE && scanner.hasNext()) {
            String newWord = scanner.next().toLowerCase();
            if (newWord.length() > 1 && newWord.chars().allMatch(Character::isLetter) && !isRepeatInArray(unsortedWords, newWord)) {
                unsortedWords[numOfWords] = newWord;
                numOfWords++;
            }
        }
        String[] sorted = Arrays.copyOfRange(unsortedWords, 0, numOfWords);
        Arrays.sort(sorted);
        scanner.close();
        return sorted;
    }

    private static boolean isRepeatInArray(String[] words, String word) {
        for (String curWord :
                words) {
            if (word.equals(curWord))
                return true;
        }
        return false;
    }


    public static int countHiddenInPuzzle(char[] puzzle) { // Q - 2
        int numOfChars = 0;
        for (char ch :
                puzzle) {
            if (ch == HIDDEN_CHAR)
                numOfChars++;
        }
        return numOfChars;
    }

    public static String getRandomWord(String[] vocabulary, Random generator) { // Q - 3
        int i = generator.nextInt(vocabulary.length);
        return vocabulary[i];

    }

    public static boolean checkLegal(String word, char[] puzzle) {  // Q - 4
        boolean contLetter = false, contHidden = false;
        for (int i = 0; i < puzzle.length; i++) {
            if (!contHidden && puzzle[i] == HIDDEN_CHAR)
                contHidden = true;
            else if (!contLetter && puzzle[i] != HIDDEN_CHAR)
                contLetter = true;

            for (int j = i + 1; j < puzzle.length; j++) {
                if (word.charAt(j) == word.charAt(i))
                    if (puzzle[j] != puzzle[i])
                        return false;
            }
        }
        return contLetter && contHidden;
    }

    public static char[] getRandomPuzzleCandidate(String word, double prob, Random generator) { // Q - 5
        char[] puzzle = new char[word.length()];

        for (int i = 0; i < word.length(); i++) {
            int isRepeat = repeatLetter(word, word.charAt(i), i);
            if (isRepeat != -1) {
                puzzle[i] = puzzle[isRepeat];
            } else if (generator.nextFloat() <= prob) {
                puzzle[i] = HIDDEN_CHAR;
            } else
                puzzle[i] = word.charAt(i);
        }
        return puzzle;
    }

    private static int repeatLetter(String word, char ch, int i) {
        for (int j = 0; j < i; j++) {
            if (word.charAt(j) == ch)
                return j;
        }
        return -1;
    }

    public static char[] getRandomPuzzle(String word, double prob, Random generator) {  // Q - 6
        for (int i = 0; i < 1000; i++) {
            char[] puzzle = getRandomPuzzleCandidate(word, prob, generator);
            if (checkLegal(word, puzzle))
                return puzzle;
        }
        throwPuzzleGenerationException();
        return null;
    }


    public static int applyGuess(char guess, String solution, char[] puzzle) { // Q - 7
        int changed = 0;
        for (int i = 0; i < puzzle.length; i++) {
            if (solution.charAt(i) == guess) {
                if (puzzle[i] == HIDDEN_CHAR) {
                    puzzle[i] = guess;
                    changed++;
                } else
                    break;
            }
        }
        return changed;
    }

    public static char[] getHelp(String solution, char[] puzzle) { // Q - 8
        int hiddenIndex = -1;
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == HIDDEN_CHAR) {
                hiddenIndex = i;
                break;
            }
        }
        char hintCh = solution.charAt(hiddenIndex);
        for (int i = hiddenIndex; i < puzzle.length; i++) {
            if (solution.charAt(i) == hintCh)
                puzzle[i] = hintCh;
        }
        return puzzle;
    }


    public static void main(String[] args) throws Exception { //Q - 9
        Random generator = new MyRandom(new int[]{0, 1, 2, 3, 4, 5}, new float[]{0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f});
        if (args.length == 0)
            throw new RuntimeException();
        Scanner sysScanner = new Scanner(System.in);
        Scanner inScanner = new Scanner(new File(args[0]));
        String[] vocab = scanVocabulary(inScanner);
        inScanner.close();

        printReadVocabulary(args[0], vocab.length);
        printSettingsMessage();
        printEnterHidingProbability();

        float prob = sysScanner.nextFloat();
        String replaceStr = "";
        char[] puzzle = null;
        String solution = null;

        while (!replaceStr.equals("no")) {
            replaceStr = "";
            solution = getRandomWord(vocab, generator);
            puzzle = getRandomPuzzle(solution, prob, generator);
            printPuzzle(puzzle);
            while (!replaceStr.equals("yes") && !replaceStr.equals("no")) {
                printReplacePuzzleMessage();
                replaceStr = sysScanner.next();
            }
        }

        printGameStageMessage();

        int numOfHidden = countHiddenInPuzzle(puzzle);
        int numOfTries = numOfHidden + 3;

        while (numOfTries != 0) {
            printPuzzle(puzzle);
            printEnterYourGuessMessage();
            char guess = sysScanner.next().charAt(0);
            int changed = applyGuess(guess, solution, puzzle);
            numOfHidden -= changed;
            if (changed != 0)
                if (numOfHidden == 0) {
                    printWinMessage();
                    break;
                } else
                    printCorrectGuess(--numOfTries);
            else {
                if (guess == 'H') {
                    getHelp(solution, puzzle);
                    numOfHidden = countHiddenInPuzzle(puzzle);
                    if (numOfHidden == 0) {
                        printWinMessage();
                        break;
                    }
                    else{
                        numOfTries--;

                    }
                } else
                    printWrongGuess(--numOfTries);
            }
        }

        if(numOfHidden != 0)
            printGameOver();
        sysScanner.close();
    }


    /*************************************************************/
    /*********************  Don't change this ********************/
    /*************************************************************/

    public static void printReadVocabulary(String vocabularyFileName, int numOfWords) {
        System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
    }

    public static void throwPuzzleGenerationException() {
        throw new RuntimeException("Failed creating a legal puzzle after 1000 attempts!");
    }

    public static void printSettingsMessage() {
        System.out.println("--- Settings stage ---");
    }

    public static void printEnterHidingProbability() {
        System.out.println("Enter your hiding probability:");
    }

    public static void printPuzzle(char[] puzzle) {
        System.out.println(puzzle);
    }

    public static void printReplacePuzzleMessage() {
        System.out.println("Replace puzzle?");
    }

    public static void printGameStageMessage() {
        System.out.println("--- Game stage ---");
    }

    public static void printEnterYourGuessMessage() {
        System.out.println("Enter your guess:");
    }

    public static void printCorrectGuess(int attemptsNum) {
        System.out.println("Correct Guess, " + attemptsNum + " guesses left");
    }

    public static void printWrongGuess(int attemptsNum) {
        System.out.println("Wrong Guess, " + attemptsNum + " guesses left");
    }

    public static void printWinMessage() {
        System.out.println("Congratulations! You solved the puzzle");
    }

    public static void printGameOver() {
        System.out.println("Game over!");
    }

}
