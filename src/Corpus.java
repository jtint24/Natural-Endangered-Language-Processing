import java.util.ArrayList;
import java.util.Collections;

/**
 * Corpus
 *
 * contains a corpus and its relevant details, including native language.
 * */

public class Corpus { 
  public ArrayList<String> words;
  Language language;
  public Corpus(Language lang, String str) {
    Corpus thisToBe = new Corpus(lang, str, false);
    language = thisToBe.language;
    words = thisToBe.words;
  }
  public Corpus(Language lang, String str, boolean linearize) {
    language = lang;
    if (linearize) { //if the language needs to be linearized, that operation is performed
      Linearizer myLinearizer = new Linearizer(lang);
      str = myLinearizer.linearize(str);
    }
    if (lang.getSpaceProtocol() == SpaceProtocol.SPACES || lang.getSpaceProtocol() == SpaceProtocol.SYLLABIC) {
      String[] wordArray = str.split(" "); //if the language is split by spaces, then each token is added to words
      for (int i = 0; i<wordArray.length; i++) {
        words.add(wordArray[i]);
      }
    } else if (lang.getSpaceProtocol() == SpaceProtocol.NOSPACES) {
      for (int i = 0; i < str.length(); i++) { //if the language is not split by spaces, then each character is added to words
        words.add("" + str.charAt(i));
      }
    }
  }
}