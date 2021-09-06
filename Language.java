public class Language {
  public String name;
  public ScriptDirection direction;
  public Corpus corpus;

  public Language(String str) {
    name = str;
    direction = ScriptDirection.LTR;
  }
  public Language(String str, ScriptDirection dir) {
    name = str;
    direction = dir;
  }
}