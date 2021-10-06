class IpaMatch {
  String[] input;
  String[] output;
  IpaMatch(String[] in, String[] out) {
    input = in;
    output = out;
  }
  String ipaOf(String str) {
    for (int i = 0; i<input.length; i++) {
      if (str==input[i]) {
        return output[i];
      }
    }
    throw new IllegalArgumentException("Error: String "+str+" not found in IPA Orthography");
  }

}