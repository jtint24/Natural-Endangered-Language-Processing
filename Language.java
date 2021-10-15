public class Language {
  private String name;
  private ScriptDirection direction;
  private IpaMatch ipaOrthography;
  private SpaceProtocol spaceProtocol;

  public Language(String str) {
    name = str;
    direction = ScriptDirection.LTR;
  }
  public Language(String str, ScriptDirection dir) {
    name = str;
    direction = dir;
  }
  public Language(String str, ScriptDirection dir, SpaceProtocol sp) {
    name = str;
    direction = dir;
    spaceProtocol = sp;
  }
  public Language(String str, ScriptDirection dir, IpaMatch ipa) {
    name = str;
    direction = dir;
    ipaOrthography = ipa;
  }
  public Language(String str, ScriptDirection dir, SpaceProtocol sp, IpaMatch ipa) {
    name = str;
    direction = dir;
    ipaOrthography = ipa;
    spaceProtocol = sp;
  }

  public String getName() {
    return name;
  }
  public ScriptDirection getDirection() {
    return direction;
  }
  public IpaMatch getIpaOrthography() {
    return ipaOrthography;
  }
  public SpaceProtocol getSpaceProtocol() {
    return spaceProtocol;
  }
}