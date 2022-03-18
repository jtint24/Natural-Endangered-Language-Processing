# Natural Endangered Language Processing   

<!--[![Current Release Version](https://img.shields.io/github/release/jtint24/NELP.svg?style=flat-square&logo=github)](https://github.com/jtint24/Natural-Endangered-Language-Processing/releases) -->

<img width="300" alt="NELPlogo" src="https://user-images.githubusercontent.com/89891042/155790054-30ca27b2-96aa-4026-bf32-e7a6814af855.png" align="right"> 

Nearly every online service today uses some kind of language technology: services like spell-check, autofill, and machine translation. However, these algorithms are only available in a handful of languages. In fact, nearly half the languages spoken worldwide are classified as "endangered languages," and have almost no digital resources available for them. This project is a digital toolkit for creating language-related algorithms for endangered languages, to help increase digital penetration in underserved communities, allowing those who speak endangered languages to access 21st-century resources.

NELP provides a broad set of useful NLP algorithms that can be applied to nearly any endangered language. First, users instantiate a Language class with information about the language, like basic syntax data, a corpus of use, and orthography. After that, users can plug that language data into any one of the prebuilt algorithms, allowing quick and easy access to powerful NLP tools. 

<img width="500" alt="Screen Shot 2022-02-25 at 1 05 12 PM" src="https://user-images.githubusercontent.com/89891042/155789373-80d5c9b8-190a-4ab5-98ee-1254a0f67a8f.png">

# Current Algorithm List:

|Algorithm   | Description |
|------------|-------------|
|Comparer    | Compares two strings in a given langauge by optionally applying synonym matching and then doing an n-gram cosine string match algorithm based on word frequency|
|Classifier  | Classifies a piece of text into a set of available categories by using the Comparer's cosine-matching algorithm |
|Markov Generator| Generates a new piece of text based on the patterns present in a given piece of text |
|POS Tagger | Uses Shannon Entropy in order to give a rough guess as to a word's part of speech given its context |
|Rhyme Checker | Checks if two words or phrases rhyme |
|Syllabifier | Splits text into syllables |
|Keyword Extractor | Extracts keywords sorted by relevance to the text |
