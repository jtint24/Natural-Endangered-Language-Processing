import java.util.ArrayList;
import java.util.Collections;

class Main {
  public static void main(String[] args)  {
    keywordDemo();
  }
  private static void keywordDemo() {
    ArrayList<String> fwList = new ArrayList<>();
    fwList.add("");
    KeywordExtractor myKeywordExtractor = new KeywordExtractor("This is a demo. A demo is what this is", fwList);
    System.out.println(myKeywordExtractor.getEntropyWords().toString());
  }
  private static void syllableDemo() {
    Language mylang = new Language("mylang", ScriptDirection.LTR, SpaceProtocol.SPACES);
    Syllabifier mySyllabifier = new Syllabifier(mylang);
    ArrayList<String> sylls = mySyllabifier.syllabify("hello there I am splitting this multisyllabic sentence with a fascinating yet straightforward algorithm");
    System.out.println(sylls.toString());
  }
  private static void classifierDemo() throws Exception {
    Language mylang = new Language("mylang", ScriptDirection.LTR, SpaceProtocol.SPACES);
    Comparer myComparer = new Comparer(mylang);
    Classifier myClassifier = new Classifier(myComparer);
    myClassifier.addCategory("negative", "I am not flying to France.", "That isn't the way to Nashville." , "They are not from Ecuador." , "He wasn't eating white rice." , "We were not sad when he moved away." , "They don't practice yoga." , "She did not like Bikhram yoga." , "He doesn't have to commute to work." , "They will not be joining us for dinner tonight." , "She won't be attending the Met Gala this year." , "These aren't pistachios." , "They weren't playing poker.");
    myClassifier.addCategory("positive", "Marie is a published author.", "In three years, everyone will be happy.", "Nora Roberts is the most prolific romance writer the world has ever known.", "She has written more than 225 books.", "If you walk into Knoxville, you’ll find a shop named Rala.", "There are more than 850 miles of hiking trails in the Great Smoky Mountains.", "Harrison Ford is 6’1”.","According to Reader’s Digest, in the original script of Return of The Jedi, Han Solo died.", "Kate travels to Doolin, Ireland every year for a writers’ conference.", "Fort Stevens was decommissioned by the United States military in 1947.", "Today, it is filled with ghosts.", "She loves to write short stories in the local coffee shop.", "Yesterday, he traded in his Android for an iPhone.", "If you take a cruise to Alaska aboard Holland America, you’ll stop in Victoria, British Columbia.", "Butchart Gardens contains over 900 varieties of plants.");
    System.out.println(myClassifier.categoryOf("I think this is bad!"));
  }
}