import java.util.ArrayList;
import java.util.Arrays;

class Syllabifier {
  Language lang;

  Syllabifier(Language l) {
    lang = l;
  }

  ArrayList<String> syllabify(String str) {
    String[] splitBySpaces = str.split(" ");
    if (lang.getSpaceProtocol() == SpaceProtocol.SYLLABIC) {
      return new ArrayList<String>(Arrays.asList(splitBySpaces));
    } else {
      if (lang.getIpaOrthography() != null) {
        str = ipaSwap(str);
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
    return !(c=='a' || c=='e' || c=='i' || c=='o' || c=='u');
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