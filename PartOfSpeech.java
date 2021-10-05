public enum PartOfSpeech {
  NOUN, VERB, ADVERB, ADJECTIVE, NUMERAL, DETERMINER, PRONOUN, PREPOSITION, POSTPOSITION, CONJUNCTION, INTERJECTION;
  public boolean isModifier() {
    return (this==PartOfSpeech.ADJECTIVE || this==PartOfSpeech.ADVERB || this==PartOfSpeech.PREPOSITION);
  }
  public boolean isFunctionWord() {
    return (this == PartOfSpeech.NOUN || this == PartOfSpeech.VERB || this == PartOfSpeech.ADJECTIVE || this == PartOfSpeech.ADVERB || this == PartOfSpeech.NUMERAL || this == PartOfSpeech.PRONOUN || this == PartOfSpeech.INTERJECTION);
  }
  public boolean isContentWord() {
    return !this.isFunctionWord();
  }
  public boolean isNoun() {
    return (this==PartOfSpeech.NOUN || this==PartOfSpeech.PRONOUN);
  }
}