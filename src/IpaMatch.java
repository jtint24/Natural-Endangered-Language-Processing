package src;

import java.util.Objects;

/**
 * IpaMatch
 *
 * a class to convert a string into a string of its representative IPA characters.
 * */

class IpaMatch {
  String[] input;
  String[] output;
  IpaMatch(String[] in, String[] out) {
    input = in;
    output = out;
  }

  /**
   * ipaOf
   *
   * gets the relevant ipa character for the input string
   *
   * @param str the string to convert into ipa
   * @return the ipa character that matches the input string
   * */
  String ipaOf(String str) {
    for (int i = 0; i<input.length; i++) {
      if (Objects.equals(str, input[i])) {
        return output[i];
      }
    }
    throw new IllegalArgumentException("Error: String "+str+" not found in IPA Orthography");
  }
  /**
   * constrictionClass
   *
   * gets the constriction class of an ipa vowel
   *
   * @param ofMark the ipa vowel to get the constriction class of
   * @returns the constriction class, "front vowel," "central vowel," or "back vowel"
   * */
  String constrictionClass(String ofMark) {
    char[] frontVowels = {'i', 'y', 'e', 'ø', 'ɛ', 'œ',  'æ', 'a'};
    char[] centralVowels = {'ɨ', 'ʉ', 'ɘ', 'ɵ', 'ɜ', 'ɞ',  'ɐ',  'ä'};
    char[] backVowels = {'ɯ', 'u', 'ʊ', 'ø', 'ɤ', 'o', 'ʌ', 'ɔ', 'ɑ', 'ɒ'};
    for (int i = 0; i<frontVowels.length; i++) {
      if (Objects.equals(ofMark, "" + frontVowels[i])) {
        return "front vowel";
      }
    }
    for (int i = 0; i<centralVowels.length; i++) {
      if (Objects.equals(ofMark, "" + centralVowels[i])) {
        return "central vowel";
      }
    }
    for (int i = 0; i<backVowels.length; i++) {
      if (Objects.equals(ofMark, "" + backVowels[i])) {
        return "back vowel";
      }
    }
    return "not a vowel";
  }

  /**
   * opennessClass
   *
   * gets the openness of a particular IPA vowel.
   *
   * @param ofMark the vowel to get the openness of
   * @return the opennessClass of the ipa vowel, "close," "near close," "close mid," "open mid," "near open," or "open"
   * */

  String opennessClass(String ofMark) {
    char[] closeVowels = {'i', 'y', 'u', 'ɨ', 'ʉ', 'ɯ'};
    char[] nearCloseVowels = {'ɪ', 'ʏ', 'ʊ'};
    char[] closeMidVowels = {'e', 'ø', 'ɘ', 'ɵ', 'ɤ', 'o'};
    char[] openMidVowels = {'ɛ', 'œ', 'ɜ', 'ɞ', 'ʌ', 'ɔ'};
    char[] nearOpenVowels = {'æ', 'ɐ'};
    char[] openVowels = {'a', 'ɶ', 'ä', 'ɑ', 'ɒ'};

    for (int i = 0; i<closeVowels.length; i++) {
      if (Objects.equals(ofMark, "" + closeVowels[i])) {
        return "close vowel";
      }
    }
    for (int i = 0; i<nearCloseVowels.length; i++) {
      if (Objects.equals(ofMark, "" + nearCloseVowels[i])) {
        return "near close vowel";
      }
    }
    for (int i = 0; i<closeMidVowels.length; i++) {
      if (Objects.equals(ofMark, "" + closeMidVowels[i])) {
        return "close mid vowel";
      }
    }
    for (int i = 0; i<openMidVowels.length; i++) {
      if (ofMark==""+openMidVowels[i]) {
        return "open mid vowel";
      }
    }
    for (int i = 0; i<nearOpenVowels.length; i++) {
      if (ofMark==""+nearOpenVowels[i]) {
        return "near open vowel";
      }
    }
    for (int i = 0; i<openVowels.length; i++) {
      if (ofMark==""+openVowels[i]) {
        return "open vowel";
      }
    }
    return "not a vowel";
  }

  /**
   * isRounded
   *
   * returns if a particular ipa vowel is rounded or not
   *
   * @param ofMark the ipa vowel to return the roundedness of
   * @return returns true if the character is a rounded vowel, otherwise returns false.
   * */

  boolean isRounded(String ofMark) {
    char[] roundedVowels = {'y', 'ʉ', 'u', 'ʏ', 'ʊ', 'ø', 'ɵ', 'o', 'œ', 'ɞ', 'ɔ', 'ɶ', 'ɒ'};
    for (int i = 0; i<roundedVowels.length; i++) {
      if (ofMark==""+roundedVowels[i]) {
        return true;
      }
    }
    return false;
  }

  /**
   * isUnrounded
   *
   * returns if a particular ipa character is an unrounded vowel or not
   *
   * @param ofMark the ipa character to return the roundedness of
   * @return returns true if the character is an unrounded vowel, otherwise returns false.
   * */

  boolean isUnrounded(String ofMark) {
    return !isRounded(ofMark) && isVowel(ofMark);
  }

  /**
   * isVowel
   *
   * returns whether the given ipa character is a vowel
   *
   * @param ofMark the ipa character in question
   * @return returns true if the character is a vowel, otherwise returns false.
   * */

  boolean isVowel(String ofMark) {
    char[] vowels = {'a', 'e', 'i', 'o', 'u', 'ɨ', 'ʉ', 'ɯ', 'u', 'ɪ', 'ʏ', 'ʊ', 'ø', 'ɘ', 'ɵ', 'ɤ', 'ə', 'ɛ', 'œ', 'ɜ', 'ɞ', 'ʌ', 'ɔ', 'æ', 'ɐ', 'ɶ', 'ä', 'ɑ', 'ɒ'};
    for (int i = 0; i<vowels.length; i++) {
      if (ofMark==""+vowels[i]) {
        return true;
      }
    }
    return false;
  }
}