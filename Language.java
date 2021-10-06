public class Language {
  private String name;
  private ScriptDirection direction;
  private IpaMatch ipaOrthography;

  public Language(String str) {
    name = str;
    direction = ScriptDirection.LTR;
  }
  public Language(String str, ScriptDirection dir) {
    name = str;
    direction = dir;
  }
  public Language(String str, ScriptDirection dir, IpaMatch ipa) {
    name = str;
    direction = dir;
    ipaOrthography = ipa;
  }

  public String getName() {
    return name;
  }
  public ScriptDirection getDirection() {
    return direction;
  }
  public IpaMatch getIpa() {
    return ipaOrthography;
  }
}