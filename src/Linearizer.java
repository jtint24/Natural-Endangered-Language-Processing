
/**
 * Linearizer
 *
 * for languages not written in a LTR script direction, this re-encodes word orders for other
 * algorithms to use them.
 * */

class Linearizer {
  Language lang;
  Linearizer(Language _lang) {
    lang = _lang;
  }
  /**
   * linearize
   *
   * the linearized string, put in a LTR format for use of the other algorithms
   *
   * @param inStr the input String
   * @return the output string, re-ordered in LTR
   * */
  String linearize(String inStr) {
    if (lang.getDirection() == ScriptDirection.RTL) {
      String[] lines = inStr.split("\n");
      String retString = "";
      for (int i = 0; i<lines.length; i++) {
        retString+=reversed(lines[i]);
      }
      return retString;
    }
    if (lang.getDirection() == ScriptDirection.BOUSTROPHEDON) {
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
    return inStr;
  }
  /**
   * reversed
   *
   * reverses a String (ie. "abc" -> "cba")
   *
   * @param input the string to reverse
   * @return the reversed string
   * */
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