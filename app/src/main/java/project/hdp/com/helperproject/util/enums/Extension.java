package project.hdp.com.helperproject.util.enums;

public enum Extension {
    JPG(".jpg"),
    PNG(".png"),
    TXT(".txt");

    public final String ext;

    Extension( String ext){
        this.ext = ext;
    }

    public boolean is(String compare){
        return compare.contains(this.ext);
    }

    public boolean is ( Extension extension ) {
        return is(extension.ext);
    }
}
