import java.util.ArrayList;

public class MarkovGenerator {
    private ArrayList<String> tokens = new ArrayList<String>();
    private Language lang;
    private String seed = "";
    public MarkovGenerator(Corpus inCorp) {
        tokens = inCorp.words;
        lang = inCorp.language;
    }
    public void setSeed(String s) {
        seed = s;
    }
    public ArrayList<String> tokensFollowing(String token) {
        ArrayList<String> nextTokens = new ArrayList<String>();
        for (int i = 0; i<tokens.size()-1; i++) {
            if (tokens.get(i).equals(token)) {
                nextTokens.add(tokens.get(i + 1));
            }
        }
        return nextTokens;
    }
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
