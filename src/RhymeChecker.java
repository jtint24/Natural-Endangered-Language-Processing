package src;

import java.util.ArrayList;

/**
 * RhymeChecker
 *
 * checks whether two pieces of text rhyme or not
 * */

public class RhymeChecker {

    private Syllabifier syllabifier;

    public RhymeChecker(Language l) {
        this.syllabifier = new Syllabifier(l);
    }

    // Returns yes if the word rhymes
    // Returns no if the word does not rhyme
    public boolean rhymes(String str1, String str2) {
        IpaMatch langIPA = syllabifier.lang.getIpaOrthography();

        // Gets the syllables of the words
        ArrayList<String> syllables1 = syllabifier.syllabify(str1);
        ArrayList<String> syllables2 = syllabifier.syllabify(str2);

        for (int i = 0; i<syllables1.size(); i++) { //removes all consonants in syllables1
            String syllable = syllables1.get(i);
            String newSyllable = "";
            for (char c : syllable.toCharArray()) {
                if (langIPA.isVowel(c+"")) {
                    newSyllable+=c;
                }
            }
            syllables1.set(i, newSyllable);
        }

        for (int i = 0; i<syllables2.size(); i++) { //removes all consonants in syllables2
            String syllable = syllables2.get(i);
            String newSyllable = "";
            for (char c : syllable.toCharArray()) {
                if (langIPA.isVowel(c+"")) {
                    newSyllable+=c;
                }
            }
            syllables2.set(i, newSyllable);
        }

        // Starts iterating through each ipa of the first word and comparing with second word
        for(int i = 0; i < syllables1.size(); i++) {
            // Start the second word pointer at the first word pointer
            for(int j = i; j < syllables2.size(); j++) {
                String ipa2 = syllables2.get(j);
                // If the second word pointer has gone over the max capacity of the first word, skip this iteration
                if(j >= syllables1.size()) {
                    break;
                }
                String ipa1j = syllables1.get(j);
                // Check if the ipa pronunciations are equal
                if(!ipa1j.equals(ipa2)) {
                    break;
                }
                // If it gets to the end of the second word and everything still matches, it rhymes
                if(j == (syllables2.size() - 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
