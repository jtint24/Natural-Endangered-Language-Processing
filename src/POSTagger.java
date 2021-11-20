/**
 * POSTagger
 *
 * When given a corpus of words in a given language, tags words as being part of a particular part of speech
 */

public class POSTagger {
    private Corpus corpus;

    public POSTagger(Corpus corp) {
        corpus = corp;
    }

    public void addCorpus(Corpus corp) {
        corpus.words.addAll(corp.words);
    }
    /**
     * shannonEntropyOf
     *
     * finds the Shannon Entropy of a given word within the corpus.
     *
     * @param word the word to find the Shannon Entropy of
     * @return the Shannon Entropy of the word within the corpus, in bits.
     * */
    private float shannonEntropyOf(String word) {
        float wordCount = 0;
        final float log10to2Coefficient = 1.0f/((float)Math.log(2.0));
        for (String corpusWord : corpus.words) {
            if (corpusWord.equals(word)) {
                wordCount++;
            }
        }
        float wordProporiton = wordCount/((float)corpus.words.size());
        float invWordProportion = 1.0f-wordProporiton;
        return (float) (-wordProporiton*Math.log(wordProporiton)*log10to2Coefficient - invWordProportion*Math.log(invWordProportion)*log10to2Coefficient);
    }
    /**
     * isNumeric
     *
     * returns true if the given string is numeric
     *
     * @param word the word to check numericness of
     * @return true if the word is numeric, or false if it isn't
     * */
    private boolean isNumeric(String word) {
        try {
            float s = Float.parseFloat(word);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    /**
     * posFor
     *
     * returns a guess for the PartOfSpeech of the given word based on the word's Shannon Entropy
     *
     * @param word the word to check POS of
     * @return the guessed Part of Speech of the word.
     * */
    public PartOfSpeech posFor(String word) {

        if (isNumeric(word)) {
            return PartOfSpeech.NUMERAL;
        }

        float entropy = shannonEntropyOf(word);
        float relativeEntropy = 5.0f * entropy / corpus.averageShannonEntropy();
        if (relativeEntropy>=0 && relativeEntropy<=1.16) {
            return PartOfSpeech.NOUN;
        } else if (relativeEntropy>1.16 && relativeEntropy<=1.44) {
            return PartOfSpeech.ADJECTIVE;
        } else if (relativeEntropy>1.44 && relativeEntropy<=1.955) {
            return PartOfSpeech.VERB;
        } else if (relativeEntropy>1.955 && relativeEntropy<=2.28) {
            return PartOfSpeech.ADVERB;
        } else if (relativeEntropy>2.28 && relativeEntropy<=2.56) {
            return PartOfSpeech.INTERJECTION;
        } else if (relativeEntropy>2.56 && relativeEntropy<=3.455) {
            return PartOfSpeech.CONJUNCTION;
        } else if (relativeEntropy>3.455 && relativeEntropy<=4.265) {
            return PartOfSpeech.PRONOUN;
        } else if (relativeEntropy>4.265 && relativeEntropy<=5.215) {
            return PartOfSpeech.DETERMINER;
        } else if (relativeEntropy>5.215) {
            return PartOfSpeech.PREPOSITION;
        }
        return PartOfSpeech.NOUN;
    }
}
