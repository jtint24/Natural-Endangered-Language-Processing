import java.util.ArrayList;
import java.util.Collections;

class Main {
  public static void main(String[] args) {
    
  }
  private void syllableDemo() {
    Language mylang = new Language("mylang", ScriptDirection.LTR, SpaceProtocol.SPACES);
    Syllabifier mySyllabifier = new Syllabifier(mylang);
    ArrayList<String> sylls = mySyllabifier.syllabify("hello there I am splitting this multisyllabic sentence with a fascinating yet straightforward algorithm");
    System.out.println(sylls.toString());
  }
}