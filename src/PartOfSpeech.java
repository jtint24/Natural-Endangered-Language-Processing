package src;

/**
 * PartOfSpeech
 *
 * a list of possible parts of speech
 *
 * */

public enum PartOfSpeech {
  NOUN, VERB, ADVERB, ADJECTIVE, NUMERAL, DETERMINER, PRONOUN, PREPOSITION, POSTPOSITION, CONJUNCTION, INTERJECTION;
  /**
   * isModifier
   *
   * returns whether the part of speech is a modifier
   */

  public boolean isModifier() {
    return (this==PartOfSpeech.ADJECTIVE || this==PartOfSpeech.ADVERB || this==PartOfSpeech.PREPOSITION);
  }

  /**
   * isFunctionWord
   *
   * returns whether the part of speech is a function word or not
   */
  public boolean isFunctionWord() {
    return (this == PartOfSpeech.NOUN || this == PartOfSpeech.VERB || this == PartOfSpeech.ADJECTIVE || this == PartOfSpeech.ADVERB || this == PartOfSpeech.NUMERAL || this == PartOfSpeech.PRONOUN || this == PartOfSpeech.INTERJECTION);
  }
  /**
   * isContentWord
   *
   * returns whether the part of speech is a content word or not
   * */
  public boolean isContentWord() {
    return !this.isFunctionWord();
  }
  /**
   * isNoun
   *
   * returns whether the part of speech is a type of noun or not
   * */
  public boolean isNoun() {
    return (this==PartOfSpeech.NOUN || this==PartOfSpeech.PRONOUN);
  }
}