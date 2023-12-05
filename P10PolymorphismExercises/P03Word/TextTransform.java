package P10PolymorphismExercises.P03Word;

public class TextTransform {
    private String text;
    private String lastCut;

    public TextTransform(String text) {
        this.text = text;
        this.lastCut = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLastCut() {
        return lastCut;
    }

    public void setLastCut(String lastCut) {
        this.lastCut = lastCut;
    }
}