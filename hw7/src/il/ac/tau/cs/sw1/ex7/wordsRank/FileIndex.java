package il.ac.tau.cs.sw1.ex7.wordsRank;

import il.ac.tau.cs.sw1.ex7.histogram.HashMapHistogram;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {

    public static final int UNRANKED_CONST = 30;

    private HashMap<String, HashMapHistogram<String>> countMap;
    private HashMap<String, RankedWord> rankWordMap;
    private HashMap<String, HashMap<String, Integer>> wordRankMap;
    private List<String> avgRankList, minRankList, maxRankList;

    public FileIndex() {
        countMap = new HashMap<>();
        rankWordMap = new HashMap<>();
        wordRankMap = new HashMap<>();
    }

    /*
     * @pre: the directory is no empty, and contains only readable text files
     */
    public void indexDirectory(String folderPath) {
        //This code iterates over all the files in the folder. add your code wherever is needed
        Set<String> allWords = new HashSet<>();
        File folder = new File(folderPath);
        File[] listFiles = folder.listFiles();
        for (File file : listFiles) {
            // for every file in the folder
            if (file.isFile()) {
                List<String> words = null;
                try {
                    words = FileUtils.readAllTokens(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HashMapHistogram<String> histogram = new HashMapHistogram<>();
                for (String word :
                        words) {
                    histogram.addItem(word);
                    allWords.add(word);
                }

                countMap.put(file.getName(), histogram);
                Iterator<String> iter = histogram.iterator();
                HashMap<String, Integer> tempMap = new HashMap<>();
                int index = 1;
                while (iter.hasNext()) {
                    tempMap.put(iter.next(), index++);
                }
                wordRankMap.put(file.getName(), tempMap);
            }
        }

        for (String word : allWords) {
            HashMap<String, Integer> tempMap = new HashMap<>();
            for (File file : listFiles) {
                if (wordRankMap.get(file.getName()).containsKey(word)) {
                    tempMap.put(file.getName(), wordRankMap.get(file.getName()).get(word));
                }
            }
            rankWordMap.put(word, new RankedWord(word, tempMap));
        }

    }

    /*
     * @pre: the index is initialized
     * @pre filename is a name of a valid file
     * @pre word is not null
     */
    public int getCountInFile(String filename, String word) throws FileIndexException {
        if (!countMap.containsKey(filename))
            throw new FileIndexException(filename);
        return countMap.get(filename).getCountForItem(word.toLowerCase());
    }

    /*
     * @pre: the index is initialized
     * @pre filename is a name of a valid file
     * @pre word is not null
     */
    public int getRankForWordInFile(String filename, String word) throws FileIndexException {
        if (!wordRankMap.containsKey(filename))
            throw new FileIndexException(filename);
        HashMap<String, Integer> tempMap = wordRankMap.get(filename);
        return tempMap.getOrDefault(word.toLowerCase(), tempMap.size() + UNRANKED_CONST);

    }

    /*
     * @pre: the index is initialized
     * @pre word is not null
     */
    public int getAverageRankForWord(String word) {
        if (rankWordMap.containsKey(word))
            return rankWordMap.get(word).getRankByType(RankedWord.rankType.average);
        else {
            int count = 0;
            for (HashMapHistogram<String> map :
                    countMap.values()) {
                count += map.getItemsSet().size() + UNRANKED_CONST;
            }
            return (int) Math.round(((double) count) / countMap.size());
        }
    }


    public List<String> getWordsWithAverageRankSmallerThanK(int k) {
        if (avgRankList == null)
            initList(RankedWord.rankType.average);
        return avgRankList.subList(0, k+1);
    }

    public List<String> getWordsWithMinRankSmallerThanK(int k) {
        if (minRankList == null)
            initList(RankedWord.rankType.min);
        return minRankList.subList(0, k+1);
    }

    public List<String> getWordsWithMaxRankSmallerThanK(int k) {
        if (maxRankList == null)
            initList(RankedWord.rankType.max);
        return maxRankList.subList(0, k+1);
    }

    private void initList(RankedWord.rankType type) {
        List<RankedWord> tempLst = new ArrayList<>(rankWordMap.values());
        tempLst.sort(new RankedWordComparator(type));
        switch (type) {
            case average:
                avgRankList = convertRankedWordToString(tempLst);
                break;
            case min:
                minRankList = convertRankedWordToString(tempLst);
                break;
            default: //case max
                maxRankList = convertRankedWordToString(tempLst);
                break;
        }
    }

    private List<String> convertRankedWordToString(List<RankedWord> words) {
        List<String> tempLst = new ArrayList<>();
        for (RankedWord word :
                words) {
            tempLst.add(word.getWord());
        }
        return tempLst;

    }

}