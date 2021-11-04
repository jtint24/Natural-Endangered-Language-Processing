import java.util.ArrayList;
import java.util.Arrays;

class Syllabifier {
  Language lang;

  Syllabifier(Language l) {
    lang = l;
  }

  ArrayList<String> syllabify(String str) {
    Linearizer sylLinearizer = new Linearizer(lang);
    str = sylLinearizer.linearize(str);
    String[] splitBySpaces = str.split(" ");
    if (lang.getSpaceProtocol() == SpaceProtocol.SYLLABIC) {
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

  private String ipaSwap(String inStr) {
    String returnStr = "";
    for (int i = 0; i < inStr.length(); i++) {
      returnStr += lang.getIpaOrthography().ipaOf("" + inStr.charAt(i));
    }
    return returnStr;
  }

  private boolean isConsonant(char c) {
    char[] vowels = {'a', 'e', 'i', 'o', 'u', 'ɨ', 'ʉ', 'ɯ', 'u', 'ɪ', 'ʏ', 'ʊ', 'ø', 'ɘ', 'ɵ', 'ɤ', 'ə', 'ɛ', 'œ', 'ɜ', 'ɞ', 'ʌ', 'ɔ', 'æ', 'ɐ', 'ɶ', 'ä', 'ɑ', 'ɒ'};
    for (int i = 0; i<vowels.length; i++) {
      if (c==vowels[i]) {
        return false;
      }
    }
    return true;
  }

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