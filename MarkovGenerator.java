import java.util.ArrayList;

/**
 * MarkovGenerator
 *
 * generates an extension of a text based on its patterns.
 * */

public class MarkovGenerator {
    private ArrayList<String> tokens = new ArrayList<String>();
    private Language lang;
    private String seed = "";
    public MarkovGenerator(Corpus inCorp) {
        tokens = inCorp.words;
        lang = inCorp.language;
    }
    /**
     * setSeed
     *
     * sets the "seed" word of the Markov Generator, the word to start generation from
     *
     * @param s the string to set as the seed.
     * */
    public void setSeed(String s) {
        seed = s;
    }
    /**
     * tokensFollowing
     *
     * returns an arraylist of tokens following a particular token within the Markov Generator
     *
     * @param token the token that precedes all the ones in the return list
     * @return the list of tokens in the text that follow 'token'
     * */
    public ArrayList<String> tokensFollowing(String token) {
        ArrayList<String> nextTokens = new ArrayList<String>();
        for (int i = 0; i<tokens.size()-1; i++) {
            if (tokens.get(i).equals(token)) {
                nextTokens.add(tokens.get(i + 1));
            }
        }
        return nextTokens;
    }
    /**
     * generateByLength
     *
     * returns a string of generated text based on the corpus, capped at a certain length.
     *
     * @param length the length of the generated text, in tokens
     * @return the Markov-generated string
     * */
    public String generateByLength(int length) {
        String generatedString = "";
        String lastWord = (seed==null) ? "" : seed;
        for (int i = 0; i<length; i++) {
            ArrayList<String> tokenCandidates = tokensFollowing(lastWord);
            lastWord = tokenCandidates.get((int) Math.random()* tokenCandidates.size());
            generatedString += lastWord+" ";
        }
        return generatedString;
    }
    /**
     * generateUntilToken
     *
     * returns a string of generated text based on the corpus, capped when a certain token is reached
     *
     * @param token the token that caps the generated text
     * @return the Markov-generated string
     * */
    public String generateUntilToken(String token) {
        String generatedString = "";
        String lastWord = (seed==null) ? "" : seed;
        while (lastWord!=token) {
            ArrayList<String> tokenCandidates = tokensFollowing(lastWord);
            lastWord = tokenCandidates.get((int) Math.random()* tokenCandidates.size());
            generatedString += lastWord+" ";
        }
        return generatedString;
    }
    /**
     * generateUntilCharacter
     *
     * returns a string of generated text based on the corpus, capped when a certain character
     * is reached
     *
     * @param character the character to cap the return string
     * @return the Markov-generated string
     * */
    public String generateUntilCharacter(char character) {
        String generatedString = "";
        String lastWord = (seed==null) ? "" : seed;
        while (!lastWord.contains(character+"")) {
            ArrayList<String> tokenCandidates = tokensFollowing(lastWord);
            lastWord = tokenCandidates.get((int) Math.random()* tokenCandidates.size());
            generatedString += lastWord+" ";
        }
        return generatedString;
    }
}
