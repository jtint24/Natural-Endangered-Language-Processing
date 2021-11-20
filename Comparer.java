import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Comparer Class
 *
 *  Compares two strings in a given langauge by optionally applying synonym matching and then
 *  doing an n-gram cosine string match algorithm based on word frequency
 * */

public class Comparer {
    private Language lang;
    private ArrayList<ArrayList<String>> synonymSets = new ArrayList<ArrayList<String>>();

    public Comparer(Language _lang) {
        lang = _lang;
    }

    public Comparer() {}

    public Language getLanguage() {
        return lang;
    }
    /**
     * similarity
     *
     *  calculates the similarity of two pieces of text
     *
     * @param text1 the first text to compare
     * @param text2 the second text to compare
     * @return the coefficient, from 0 to 1, representing the similarity of the texts (0 is low
     *         similarity, 1 is high similarity)
     * */
    public float similarity(String text1, String text2) {
        text1 = cleanSynonyms(text1);
        text2 = cleanSynonyms(text2);
        String[] text1words = text1.split(" ");
        String[] text2words = text2.split(" ");
        ArrayList<String> vocabulary = new ArrayList<String>();
        for (String word : text1words) {
            if (!vocabulary.contains(word)) {
                vocabulary.add(word);
            }
        }
        for (String word : text2words) {
            if (!vocabulary.contains(word)) {
                vocabulary.add(word);
            }
        }
        int[] text1vec = new int[vocabulary.size()];
        int[] text2vec = new int[vocabulary.size()];
        for (int i = 0; i<vocabulary.size(); i++) {
            text1vec[i] = 0;
            text2vec[i] = 0;
        }
        for (String word : text1words) {
            text1vec[vocabulary.indexOf(word)]++;
        }
        for (String word : text2words) {
            text2vec[vocabulary.indexOf(word)]++;
        }

        int numerator = 0;
        int denominator1 = 0;
        int denominator2 = 0;

        for (int i = 0; i<text1vec.length; i++) {
            numerator+=text1vec[i]*text2vec[i];
            denominator1+=text1vec[i]*text1vec[i];
            denominator2+=text2vec[i]*text2vec[i];
        }

        return ((float)numerator)/((float) Math.sqrt(denominator1*denominator2));
    }
    /**
     * addSynonym
     *
     * add a synonym to the synonym set. If it can't be coalesced into an existing synonym set,
     * a new synonym set will be added with the included words.
     *
     * @param syn1 first synonym
     * @param syn2 second synonym
     * */
    public void addSynonym(String syn1, String syn2) {
        for (ArrayList<String> synonymSet : synonymSets) {
            if (synonymSet.contains(syn1)) {
                synonymSet.add(syn2);
                return;
            }
            if (synonymSet.contains(syn2)) {
                synonymSet.add(syn1);
                return;
            }
        }
        ArrayList<String> newSynSet = new ArrayList<String>();
        newSynSet.add(syn1);
        newSynSet.add(syn2);
        synonymSets.add(newSynSet);
    }
    /**
     * addSynonymSet
     *
     * add a synonymSet to the synonym sets. If it can't be coalesced into an existing synonym set,
     * a new synonym set will be added with the included words.
     *
     * @param syns a variadic parameter of synonyms to add
     * */
    public void addSynonymSet(String ...syns) {
        for (String syn: syns) {
            for (ArrayList<String> synonymSet : synonymSets) {
                if (synonymSet.contains(syn)) {
                    for (String synonymToAdd : syns) {
                        synonymSet.add(synonymToAdd);
                    }
                    return;
                }
            }
        }
        ArrayList<String> newSynSet = new ArrayList<String>();
        for (String synonymToAdd : syns) {
            newSynSet.add(synonymToAdd);
        }
        synonymSets.add(newSynSet);
    }
    /**
     * addSynonymSet
     *
     * add a synonymSet to the synonym sets. If it can't be coalesced into an existing synonym set,
     * a new synonym set will be added with the included words.
     *
     * @param syns the arrayList of synonyms to add
     * */
    public void addSynonymSet(ArrayList<String> syns) {
        for (String syn: syns) {
            for (ArrayList<String> synonymSet : synonymSets) {
                if (synonymSet.contains(syn)) {
                    synonymSet.addAll(syns);
                    return;
                }
            }
        }
        synonymSets.add(syns);
    }
    /**
     * cleanSynonym
     *
     * returns the root synonym of a word, if one exists. Otherwise, returns the original word.
     *
     * @param word word to find root synonym of.
     * */
    private String cleanSynonym(String word) {
        for (ArrayList<String> synonymSet : synonymSets) {
            if (synonymSet.contains(word)) {
                return synonymSet.get(0);
            }
        }
        return word;
    }
    /**
     * cleanSynonyms
     *
     * returns the root synonym of each word in a set, if one exists. Otherwise, returns the
     * original word.
     *
     * @param text text to find root synonyms in.
     * */
    private String cleanSynonyms(String text) {
        String[] textWords = text.split(" ");
        String retString = "";
        for (int i = 0; i<textWords.length; i++) {
            retString+=cleanSynonym(textWords[i])+" ";
        }
        return retString;
    }
}
