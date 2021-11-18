class IpaMatch {
  String[] input;
  String[] output;
  IpaMatch(String[] in, String[] out) {
    input = in;
    output = out;
  }
  String ipaOf(String str) {
    for (int i = 0; i<input.length; i++) {
      if (str==input[i]) {
        return output[i];
      }
    }
    throw new IllegalArgumentException("Error: String "+str+" not found in IPA Orthography");
  }

  String constrictionClass(String ofMark) {
    char[] frontVowels = {'i', 'y', 'e', 'ø', 'ɛ', 'œ',  'æ', 'a'};
    char[] centralVowels = {'ɨ', 'ʉ', 'ɘ', 'ɵ', 'ɜ', 'ɞ',  'ɐ',  'ä'};
    char[] backVowels = {'ɯ', 'u', 'ʊ', 'ø', 'ɤ', 'o', 'ʌ', 'ɔ', 'ɑ', 'ɒ'};
    for (int i = 0; i<frontVowels.length; i++) {
      if (ofMark==""+frontVowels[i]) {
        return "front vowel";
      }
    }
    for (int i = 0; i<centralVowels.length; i++) {
      if (ofMark==""+centralVowels[i]) {
        return "central vowel";
      }
    }
    for (int i = 0; i<backVowels.length; i++) {
      if (ofMark==""+backVowels[i]) {
        return "back vowel";
      }
    }
    return "not a vowel";
  }

  String opennessClass(String ofMark) {
    char[] closeVowels = {'i', 'y', 'u', 'ɨ', 'ʉ', 'ɯ'};
    char[] nearCloseVowels = {'ɪ', 'ʏ', 'ʊ'};
    char[] closeMidVowels = {'e', 'ø', 'ɘ', 'ɵ', 'ɤ', 'o'};
    char[] openMidVowels = {'ɛ', 'œ', 'ɜ', 'ɞ', 'ʌ', 'ɔ'};
    char[] nearOpenVowels = {'æ', 'ɐ'};
    char[] openVowels = {'a', 'ɶ', 'ä', 'ɑ', 'ɒ'};

    for (int i = 0; i<closeVowels.length; i++) {
      if (ofMark==""+closeVowels[i]) {
        return "close vowel";
      }
    }
    for (int i = 0; i<nearCloseVowels.length; i++) {
      if (ofMark==""+nearCloseVowels[i]) {
        return "near close vowel";
      }
    }
    for (int i = 0; i<closeMidVowels.length; i++) {
      if (ofMark==""+closeMidVowels[i]) {
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

  boolean isRounded(String ofMark) {
    char[] roundedVowels = {'y', 'ʉ', 'u', 'ʏ', 'ʊ', 'ø', 'ɵ', 'o', 'œ', 'ɞ', 'ɔ', 'ɶ', 'ɒ'};
    for (int i = 0; i<roundedVowels.length; i++) {
      if (ofMark==""+roundedVowels[i]) {
        return true;
      }
    }
    return false;
  }

  boolean isUnrounded(String ofMark) {
    return !isRounded(ofMark) && isVowel(ofMark);
  }

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