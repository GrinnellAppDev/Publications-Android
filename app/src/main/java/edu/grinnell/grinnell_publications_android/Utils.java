package edu.grinnell.grinnell_publications_android;

/**
 * Contains utility methods and constants that are used throughout the application.
 * @author Albert Owusu-Asare
 * @since 1.1 Thu May  5 16:14:59 CDT 2016
 */
public final class Utils {

    private Utils(){
        // Never instantiated.
    }

    /** Logging */
    static final String VERB_CREATED = "created";
    static final String VERB_CHANGED = "changed";
    static final String VERB_IGNORED = "ignored";
    static final String VERB_ENQUEUED = "enqueued";
    static final String VERB_CANCELED = "canceled";
    static final String VERB_BATCHED = "batched";
    static final String VERB_RETRYING = "retrying";
    static final String VERB_EXECUTING = "executing";
    static final String VERB_DECODED = "decoded";
    static final String VERB_TRANSFORMED = "transformed";
    static final String VERB_JOINED = "joined";
    static final String VERB_REMOVED = "removed";
    static final String VERB_DELIVERED = "delivered";
    static final String VERB_REPLAYING = "replaying";
    static final String VERB_COMPLETED = "completed";
    static final String VERB_ERRORED = "errored";
    static final String VERB_PAUSED = "paused";
    static final String VERB_RESUMED = "resumed";

    /** Author Contact Urls */
    public enum AuthorContactUrl {
        FACEBOOK("Facebook"),
        LINKED_IN("LinkedIn"),
        PERSONAL_WEBSITE("Website");
        private final String name;
        AuthorContactUrl(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            return otherName != null && name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }

    }



}
