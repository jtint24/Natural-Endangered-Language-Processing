import java.util.ArrayList;

/**
 * Classifier
 *
 * classifies a piece of text into a set of available categories
 * */

public class Classifier {
    private ArrayList<Category> categories = new ArrayList<Category>();
    private Comparer customComparer;
    private Language language;

    public Classifier(Language lang) {
        language = lang;
    }
    public Classifier(Comparer _customComparer) {
        customComparer = _customComparer;
        language = _customComparer.getLanguage();
    }
    public Classifier(Language lang, Comparer _customComparer) {
        language = lang;
        customComparer = _customComparer;
    }

    /**
     * addCategory
     *
     * adds a category into the category list
     *
     * @param name the name of the category
     * @param tags the tags that fit under the category
     * @exception 'Duplicate category added' if a category with a duplicate name is attempted to be added
     * */

    public void addCategory(String name, String ... tags) throws Exception {
        for (Category cat : categories) {
            if (cat.getName().equals(name)) {
                throw new Exception("Duplicate category added: "+name);
            }
        }
        categories.add(new Category(name, tags));
    }

    /**
     * addTagsToCategory
     *
     * adds a list of tags to a given category
     *
     * @param name the name of the category to add the tags to
     * @param tags the tags fitting the category to add to
     * @exception 'No category with title' if there is no category with the given name to add categories to
     * */
    public void addTagsToCategory(String name, String ... tags) throws Exception {
        for (Category cat : categories) {
            if (cat.getName().equals(name)) {
                cat.addTags(tags);
            }
        }
        throw new Exception("No category with title "+name+" found");
    }

    /**
     * categoryOf
     *
     * returns the name of the category that best matches a piece of text
     *
     * @param text the text to give a category to
     * @return the name of the category that best matches the piece of text
     * */

    public String categoryOf(String text) {
        String bestCatSoFar = categories.get(0).getName();
        float bestScoreSoFar = categories.get(0).avgCosineSimilarity(text);
        for (int i = 1; i<categories.size(); i++) {
            float scoreForThis = categories.get(i).avgCosineSimilarity(text);
            if (scoreForThis>bestScoreSoFar) {
                bestCatSoFar = categories.get(i).getName();
                bestScoreSoFar = scoreForThis;
            }
        }
        return bestCatSoFar;
    }

    /**
     * Category
     *
     * stores information pertaining to a certain classifier category
     * */
    private class Category {
        private String name;
        private ArrayList<String>  tags = new ArrayList<String>();
        Category(String _name, String ... _tags) {
            name = _name;
            for (int i = 0; i<_tags.length; i++) {
                tags.add(_tags[i]);
            }
        }
        Category(String _name, ArrayList<String>  _tags) {
            name = _name;
            tags.addAll(_tags);
        }

        public String getName() {
            return name;
        }

        public void addTags(String ... _tags) {
            for (int i = 0; i<_tags.length; i++) {
                tags.add(_tags[i]);
            }
        }

        public void addTags(ArrayList<String> _tags) {
            tags.addAll(_tags);
        }

        /**
         * avgCosineSimilarity
         *
         * gives the average cosine similarity to a piece of text from each tag in the category
         *
         * @param text the text to get avgCosineSimilarity to
         * @return the average from 0 to 1 of how closely the text matches the category
         * */
        public float avgCosineSimilarity(String text) {
            Comparer langComparer = customComparer==null ?  new Comparer(language) : customComparer;
            float comparerSum = 0.0f;

            for (int i = 0; i<tags.size(); i++) {
                comparerSum += langComparer.similarity(text, tags.get(i));
            }
            return comparerSum/((float) tags.size());
        }
    }
}
