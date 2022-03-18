package src;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Syllabifier
 *
 * splits texts into syllables
 * */

class Syllabifier {
  Language lang;

  Syllabifier(Language l) {
    lang = l;
  }

  /**
   * syllabify
   *
   * splits a text into syllables
   *
   * @param str the text to split
   * @return an arraylist of Strings representing each syllable
   * */
  ArrayList<String> syllabify(String str) {
    Linearizer sylLinearizer = new Linearizer(lang);
    str = sylLinearizer.linearize(str); //linearizes the text

    String[] splitBySpaces = str.split(" ");
    if (lang.getSpaceProtocol() == SpaceProtocol.SYLLABIC) { //if the language has syllabic space usage, just split by spaces
      return new ArrayList<String>(Arrays.asList(splitBySpaces));
    } else {
      if (lang.getIpaOrthography() != null) {
        str = ipaSwap(str); //use an IpaOrthography to convert to IPA
      }
      ArrayList<String> syllables = new ArrayList<String>();
      for (int i = 0; i<splitBySpaces.length; i++) {
        syllables.addAll(sylWord(splitBySpaces[i]));
      }
      return syllables;
    }
  }

  /**
   * ipaSwap
   *
   * swaps letters in a string for their ipa equivalents
   *
   * @param inStr the string to swap
   * @return the string with swaps performed
   * */
  private String ipaSwap(String inStr) {
    String returnStr = "";
    for (int i = 0; i < inStr.length(); i++) {
      returnStr += lang.getIpaOrthography().ipaOf("" + inStr.charAt(i));
    }
    return returnStr;
  }
  /**
   * isConsonant
   *
   * checks if a character is a consonant
   *
   * @param c the character in question
   * @return true if the character is a consonant, otherwise return false
   * */
  private boolean isConsonant(char c) {
    char[] vowels = {'a', 'e', 'i', 'o', 'u', 'ɨ', 'ʉ', 'ɯ', 'u', 'ɪ', 'ʏ', 'ʊ', 'ø', 'ɘ', 'ɵ', 'ɤ', 'ə', 'ɛ', 'œ', 'ɜ', 'ɞ', 'ʌ', 'ɔ', 'æ', 'ɐ', 'ɶ', 'ä', 'ɑ', 'ɒ'};
    for (int i = 0; i<vowels.length; i++) {
      if (c==vowels[i]) {
        return false;
      }
    }
    return true;
  }
  /**
   * sylWord
   *
   * splits a word into syllables
   *
   * @param str the word to split into syllables
   * @return an ArrayList of the syllables in a word
   * */
  private ArrayList<String> sylWord(String str) {
    ArrayList<String> returnStrs = new ArrayList<String>();
    boolean onCon = true;
    String activeStr = "";
    for (int i = 0; i < str.length(); i++) {
      activeStr += str.charAt(i);
      if (isConsonant(str.charAt(i)) & !onCon & i>0) {
        if (!isConsonant(str.charAt(i-1))) {
          returnStrs.add(activeStr);
          activeStr="";
          onCon = true;
        }
      }
      if (!isConsonant(str.charAt(i))) {
        onCon = false;
      }
    }
    if (activeStr!="" & activeStr.length()>1) {
      returnStrs.add(activeStr);
    }
    if (activeStr.length()==1 & returnStrs.size()>0) {
      returnStrs.set(returnStrs.size()-1,returnStrs.get(returnStrs.size()-1)+activeStr);
    }
    if (activeStr.length()==1 & returnStrs.size()==0) {
      returnStrs.add(activeStr);
    }
    return returnStrs;
  }
}