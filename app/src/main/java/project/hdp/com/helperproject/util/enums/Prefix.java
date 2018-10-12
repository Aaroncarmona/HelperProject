package project.hdp.com.helperproject.util.enums;

public enum Prefix {
    TEST("TS"),
    FILES("FL");

    public final String prefix;

    Prefix(String prefix){
        this.prefix = prefix;
    }

    public boolean is(String compare){
        return compare.contains(this.prefix);
    }
}
