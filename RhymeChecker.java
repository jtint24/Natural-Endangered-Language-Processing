import java.util.ArrayList;

public class RhymeChecker {

    Syllabifier syllabifier;

    public RhymeChecker(Language l) {
        this.syllabifier = new Syllabifier(l);
    }

    // Returns yes if the word rhymes
    // Returns no if the word does not rhyme
    public boolean rhymes(String str1, String str2) {
        // Gets the syllables of the words
        ArrayList<String> word1 = syllabifier.syllabify(str1);
        ArrayList<String> word2 = syllabifier.syllabify(str2);
        // Starts iterating through each ipa of the first word and comparing with second word
        for(int i = 0; i < word1.size(); i++) {
            // Start the second word pointer at the first word pointer
            for(int j = i; j < word2.size(); j++) {
                String ipa2 = word2.get(j);
                // If the second word pointer has gone over the max capacity of the first word, skip this iteration
                if(j >= word1.size()) {
                    break;
                }
                String ipa1j = word1.get(j);
                // Check if the ipa prononciations are equal
                if(!ipa1j.equals(ipa2)) {
                    break;
                }
                // If it gets to the end of the second word and everything still matches, it rhymes
                if(j == (word2.size() - 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
