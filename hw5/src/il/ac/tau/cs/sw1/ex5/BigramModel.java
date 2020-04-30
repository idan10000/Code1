package il.ac.tau.cs.sw1.ex5;


import java.io.*;
import java.util.Arrays;

public class BigramModel {
    public static final int MAX_VOCABULARY_SIZE = 14000;
    public static final String VOC_FILE_SUFFIX = ".voc";
    public static final String COUNTS_FILE_SUFFIX = ".counts";
    public static final String SOME_NUM = "some_num";
    public static final int ELEMENT_NOT_FOUND = -1;

    String[] mVocabulary;
    int[][] mBigramCounts;

    // DO NOT CHANGE THIS !!!
    public void initModel(String fileName) throws IOException {
        mVocabulary = buildVocabularyIndex(fileName);
        mBigramCounts = buildCountsArray(fileName, mVocabulary);

    }


    /*
     * @post: mVocabulary = prev(mVocabulary)
     * @post: mBigramCounts = prev(mBigramCounts)
     */
    public String[] buildVocabularyIndex(String fileName) throws IOException { // Q 1
        BufferedReader buffer = new BufferedReader(new FileReader(new File(fileName)));
        String[] vocab = new String[MAX_VOCABULARY_SIZE];
        int index = 0;
        String line;
        while ((line = buffer.readLine()) != null && index <= MAX_VOCABULARY_SIZE) {
            line = line.toLowerCase();
            String[] words = line.split(" ");
            exchangeNums(words);
            for (String word :
                    words) {
                if ((isLegalWord(word) && !isRepeatWord(word, vocab))) {
                    vocab[index++] = word.toLowerCase();
                }
            }
        }
        buffer.close();
        return (index >= MAX_VOCABULARY_SIZE) ? vocab : Arrays.copyOfRange(vocab, 0, index);
    }

    private void exchangeNums(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].chars().allMatch(Character::isDigit))
                words[i] = SOME_NUM;
        }
    }

    private boolean isLegalWord(String word) {
        return word.chars().anyMatch(Character::isLetter);
    }

    private boolean isRepeatWord(String word, String[] words) {
        for (int i = 0; i < words.length && words[i] != null; i++) {
            if (words[i].equals(word))
                return true;
        }
        return false;
    }


    /*
     * @post: mVocabulary = prev(mVocabulary)
     * @post: mBigramCounts = prev(mBigramCounts)
     */
    public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException { // Q - 2
        int[][] countArray = new int[vocabulary.length][vocabulary.length];
        BufferedReader buffer = new BufferedReader(new FileReader(new File(fileName)));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.toLowerCase();
            String[] words = line.split(" ");
            exchangeNums(words);
            int firstWordLoc = isWordInVocab(words[0], vocabulary), secWordLoc;
            for (int i = 0; i < words.length - 1; i++) {
                secWordLoc = isWordInVocab(words[i + 1], vocabulary);
                if (firstWordLoc != -1 && secWordLoc != -1)
                    countArray[firstWordLoc][secWordLoc]++;
                firstWordLoc = secWordLoc;
            }
        }
        buffer.close();
        return countArray;
    }

    private int isWordInVocab(String word, String[] vocab) {
        for (int i = 0; i < vocab.length; i++) {
            if (vocab[i].equals(word))
                return i;
        }
        return -1;
    }


    /*
     * @pre: the method initModel was called (the language model is initialized)
     * @pre: fileName is a legal file path
     */
    public void saveModel(String fileName) throws IOException { // Q-3
        BufferedWriter vocWriter = new BufferedWriter(new FileWriter(new File(fileName + VOC_FILE_SUFFIX)));
        BufferedWriter countsWriter = new BufferedWriter(new FileWriter(new File(fileName + COUNTS_FILE_SUFFIX)));

        vocWriter.write(mVocabulary.length + " words");
        for (int i = 0; i < mVocabulary.length; i++) {
            vocWriter.newLine();
            vocWriter.write(i + "," + mVocabulary[i]);
        }
        boolean lineFlag = false;
        for (int i = 0; i < mBigramCounts.length; i++) {
            for (int j = 0; j < mBigramCounts[i].length; j++) {
                if (mBigramCounts[i][j] != 0) {
                    if (lineFlag)
                        countsWriter.newLine();
                    countsWriter.write(i + "," + j + ":" + mBigramCounts[i][j]);
                    lineFlag = true;
                }
            }
        }
        vocWriter.flush();
        countsWriter.flush();
        vocWriter.close();
        countsWriter.close();
    }


    /*
     * @pre: fileName is a legal file path
     */
    public void loadModel(String fileName) throws IOException { // Q - 4
        BufferedReader vocBuffer = new BufferedReader(new FileReader(new File(fileName + VOC_FILE_SUFFIX)));
        BufferedReader countBuffer = new BufferedReader(new FileReader(new File(fileName + COUNTS_FILE_SUFFIX)));
        String line;
        int vocabLen = Integer.parseInt(vocBuffer.readLine().split(" ")[0]);
        String[] newVocab = new String[vocabLen];

        while ((line = vocBuffer.readLine()) != null) {
            int split = findSplit(line);
            newVocab[Integer.parseInt(line.substring(0, split))] = line.substring(split + 1);
        }

        int[][] wordCount = new int[vocabLen][vocabLen];
        while ((line = countBuffer.readLine()) != null) {
            String[] split = line.split(":");
            int splitIndex = findSplit(split[0]);
            wordCount[Integer.parseInt(split[0].substring(0, splitIndex))]
                    [Integer.parseInt(split[0].substring(splitIndex + 1))]
                    = Integer.parseInt(split[1]);
        }
        mBigramCounts = wordCount;
        mVocabulary = newVocab;
        vocBuffer.close();
        countBuffer.close();
    }

    private int findSplit(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ',')
                return i;
        }
        return -1;
    }


    /*
     * @pre: word is in lowercase
     * @pre: the method initModel was called (the language model is initialized)
     * @pre: word is in lowercase
     * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
     */
    public int getWordIndex(String word) {  // Q - 5
        return isWordInVocab(word, mVocabulary);
    }


    /*
     * @pre: word1, word2 are in lowercase
     * @pre: the method initModel was called (the language model is initialized)
     * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
     * exist in the vocabulary, $ret = 0
     */
    public int getBigramCount(String word1, String word2) { //  Q - 6
        int iWord1 = getWordIndex(word1);
        int iWord2 = getWordIndex(word2);
        if (iWord1 != -1 && iWord2 != -1)
            return mBigramCounts[iWord1][iWord2];
        else
            return 0;
    }


    /*
     * @pre word in lowercase, and is in mVocabulary
     * @pre: the method initModel was called (the language model is initialized)
     * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
     * word was never seen, $ret will be null
     */
    public String getMostFrequentProceeding(String word) { //  Q - 7
        int index = getWordIndex(word);
        int max = 0;
        String maxWord = null;
        for (int i = 0; i < mBigramCounts[index].length; i++) {
            if (mBigramCounts[index][i] > max) {
                max = mBigramCounts[index][i];
                maxWord = mVocabulary[i];
            }
        }
        return maxWord;
    }


    /* @pre: sentence is in lowercase
     * @pre: the method initModel was called (the language model is initialized)
     * @pre: each two words in the sentence are are separated with a single space
     * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
     */
    public boolean isLegalSentence(String sentence) {  //  Q - 8
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            if (getBigramCount(words[i], words[i + 1]) == 0)
                return false;
        }
        return true;
    }


    /*
     * @pre: arr1.length = arr2.legnth
     * post if arr1 or arr2 are only filled with zeros, $ret = 0, otherwise
     */
    public static double calcCosineSim(int[] arr1, int[] arr2) { //  Q - 9
        double sumAB = 0, sumAA = 0, sumBB = 0;
        for (int i = 0; i < arr1.length; i++) {
            sumAB += arr1[i] * arr2[i];
            sumAA += arr1[i] * arr1[i];
            sumBB += arr2[i] * arr2[i];
        }
        if (sumAA == 0 || sumBB == 0)
            return 0.;
        else
            return sumAB / (Math.sqrt(sumAA * sumBB));
    }


    /*
     * @pre: word is in vocabulary
     * @pre: the method initModel was called (the language model is initialized),
     * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
     * other words in vocabulary
     */
    public String getClosestWord(String word) { //  Q - 10
        int wordIndex = getWordIndex(word);
        int[] wordVector = mBigramCounts[wordIndex];
        String closestWord = null;
        double cosineSim = 0;
        for (int i = 0; i < mVocabulary.length; i++) {
            if (i != wordIndex) {
                double newConsineSim = calcCosineSim(wordVector, mBigramCounts[getWordIndex(mVocabulary[i])]);
                if (newConsineSim > cosineSim) {
                    cosineSim = newConsineSim;
                    closestWord = mVocabulary[i];
                }
            }
        }
        return closestWord;

    }

}
