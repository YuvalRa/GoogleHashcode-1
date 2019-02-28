package hashcode;

import java.util.List;

public class SpecialImage {
    public char imgType;
    public List<String> tags;
    public int id;

    public SpecialImage (char _imgType, List<String> _tags, int _id) {
        imgType = _imgType;
        tags = _tags;
        id = _id;
    }

    public int commonTags (SpecialImage otherImage) {
        int counter = 0;
        for (String s : tags) {
            if (otherImage.tags.contains(s)) {
                counter++;
            }
        }
        return counter;
    }
}
