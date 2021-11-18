import java.util.ArrayList;
import java.util.Collections;

public class Corpus { 
  public ArrayList<String> words;
  Language language;
  public Corpus(Language lang, String str, boolean linearize) {
    language = lang;
    if (linearize) {
      Linearizer myLinearizer = new Linearizer(lang);
      str = myLinearizer.linearize(str);
    }
    if (lang.getSpaceProtocol() == SpaceProtocol.SPACES || lang.getSpaceProtocol() == SpaceProtocol.SYLLABIC) {
      String[] wordArray = str.split(" ");
      for (int i = 0; i<wordArray.length; i++) {
        words.add(wordArray[i]);
      }
    } else if (lang.getSpaceProtocol() == SpaceProtocol.NOSPACES) {
      for (int i = 0; i < str.length(); i++) {
        words.add("" + str.charAt(i));
      }
    }
  }
}