class Linearizer() {
  Language lang;
  Linearizer(Language _lang) {
    lang = _lang;
  }
  String linearize(String inStr) {
    if (inStr.direction == ScriptDirection.RTL) {
      String[] lines = inStr.split("\n");
      String retString = "";
      for (int i = 0; i<lines.length; i++) {
        retString+=reversed(lines[i]);
      }
      return retString;
    }
    if (inStr.direction == ScriptDirection.BOUSTROPHEDON) {
      String[] lines = inStr.split("\n");
      String retString = "";
      for (int i = 0; i<lines.length; i++) {
        if (i%2==1) {
          retString+=reversed(lines[i]);
        } else {
          retString+=lines[i];
        }
      }
      return retString;
    }
    return 
  }
  private String reversed(String input){
    char[] inCr = input.toCharArray();
    int begin=0;
    int end=inCr.length-1;
    char temp;
    while(end>begin){
        temp = inCr[begin];
        inCr[begin]=inCr[end];
        inCr[end] = temp;
        end--;
        begin++;
    }
    return new String(inCr);
  }
}