package src;

import java.util.*;

public class KeywordExtractor {
    private RelatedWordMap rwMap;
    private ArrayList<String> functionWords;
    private ArrayList<EntropyWord> entropyWords;

    public KeywordExtractor(String inText, ArrayList<String> fw) {
        functionWords = fw;
        rwMap = new RelatedWordMap(inText);
        entropyWords = rwMap.getWordsSortedByEntropy();
    }

    public ArrayList<EntropyWord> getEntropyWords() {
        return entropyWords;
    }

    private class EntropyWord implements Comparable<EntropyWord> {
        private float entropy;
        private String word;
        public EntropyWord(String w, float e) {
            entropy = e;
            word = w;
        }

        public float getEntropy() {
            return entropy;
        }
        public String getWord() {
            return word;
        }

        @Override
        public int compareTo(EntropyWord ew) {
            return this.entropy<ew.entropy ? 1 : -1;
        }
        @Override
        public String toString() {
            return word+": "+entropy;
        }
    }
    private class RelatedWordMap {
        private ArrayList<String> wordOrdinalList = new ArrayList<>();
        private int[][] cooccuranceCount;
        private char[] punctuation = {'"',';',':','\'','\\','-',',','(',')','[',']','{','}','<','>'};
        private char[] stoppingPunctuation = {'.','?','!'};

        public RelatedWordMap(String inText) {
            inText = cleanPunctuation(inText);
            inText = inText.toLowerCase();

            for (String word : inText.split(" ")) {
                if (!wordOrdinalList.contains(word) && word.charAt(word.length()-1)!='.') {
                    if (!functionWords.contains(word)) {
                        wordOrdinalList.add(word);
                    }
                }
            }
            cooccuranceCount = new int[wordOrdinalList.size()][wordOrdinalList.size()];

            String[] sentences = inText.split("\\. ");
            //System.out.println(sentences);
            for (String sentence : sentences) {
                String[] sentenceSplitByWords = sentence.split(" ");
                //System.out.println("sentence: "+sentence);
                for (int i = 0; i<sentenceSplitByWords.length; i++) {
                    for (int j = i+1; j<sentenceSplitByWords.length; j++) {
                        //System.out.println("word match: "+sentenceSplitByWords[i]+" "+sentenceSplitByWords[j]);
                        int index1 = wordOrdinalList.indexOf(sentenceSplitByWords[i]);
                        int index2 = wordOrdinalList.indexOf(sentenceSplitByWords[j]);
                        cooccuranceCount[index1][index2]++;
                        cooccuranceCount[index2][index1]++;
                    }
                }
            }
            /*for (int i = 0; i<wordOrdinalList.size(); i++) {
                for (int j = 0; j<wordOrdinalList.size(); j++) {
                    System.out.print(cooccuranceCount[i][j]+" ");
                }
                System.out.println();
            }*/
        }
        private String cleanPunctuation(String inText) {
            String retText = "";
            for (int i = 0; i<inText.length(); i++) {
                if (contains(stoppingPunctuation, inText.charAt(i))) {
                    retText += '.';
                } else if (!contains(punctuation, inText.charAt(i))) {
                    retText += inText.charAt(i);
                }
            }
            return retText;
        }

        private boolean contains(char[] arr, char item) {
            for (char chr : arr) {
                if (chr == item) {
                    return true;
                }
            }
            return false;
        }

        private float getEntropyFor(String word) {
            int wordIndex = wordOrdinalList.indexOf(word);
            if (wordIndex==-1) {
                throw new RuntimeException("word "+word+" not found in the list");
            }
            int sumCount = 0;
            for (int i = 0; i<cooccuranceCount[0].length; i++) {
                sumCount+=cooccuranceCount[wordIndex][i];
            }
            float sumProbability = 0;
            float logConversionConstant = (float) (1.0/Math.log(2));
            for (int i = 0; i<cooccuranceCount[0].length; i++) {
                float probability = ((float)cooccuranceCount[wordIndex][i])/((float)sumCount);
                if (probability!=0) {
                    sumProbability -= probability * Math.log(probability) * logConversionConstant;
                }
            }
            return sumProbability;
        }
        private ArrayList<EntropyWord> getWordsSortedByEntropy() {
            ArrayList<EntropyWord> wordsSortedByEntropy = new ArrayList<>();
            for (String word : wordOrdinalList) {
                wordsSortedByEntropy.add(new EntropyWord(word, getEntropyFor(word)));
            }
            System.out.println(wordsSortedByEntropy);
            Collections.sort(wordsSortedByEntropy);
            System.out.println(wordsSortedByEntropy);
            return wordsSortedByEntropy;
        }
    }
}
