package P10PolymorphismExercises.P03Word;

public class Command implements CommandInterface{
    private String commandName;
    private int startIndex;
    private int endIndex;

    public Command(String commandName, int startIndex, int endIndex) {
        this.commandName = commandName;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public void execute(TextTransform textTransform) {
        String text = textTransform.getText();

        switch (commandName) {
            case "uppercase":
                uppercase(text, startIndex, endIndex,textTransform);
                break;
            case "cut":
                cut(text, startIndex, endIndex, textTransform);
                break;
            case "paste":
                paste(text, startIndex, endIndex, textTransform);
                break;
        }
    }

    private void uppercase(String text, int startIndex, int endIndex, TextTransform textTransform) {
        if (startIndex == endIndex) {
            return;
        }
        StringBuilder sb = new StringBuilder(text);

        String substring = sb.substring(startIndex, endIndex);
        String uppercaseSubstring = substring.toUpperCase();

        sb.replace(startIndex, endIndex, uppercaseSubstring);

        textTransform.setText(sb.toString());
    }

    private void cut(String text, int startIndex, int endIndex, TextTransform textTransform) {
        if (startIndex == endIndex) {
            textTransform.setLastCut("");
            return;
        }

        textTransform.setLastCut(text.substring(startIndex, endIndex));
        StringBuilder sb = new StringBuilder(text.substring(0, startIndex) + text.substring(endIndex));
        textTransform.setText(sb.toString());
    }

    private void paste(String text, int startIndex, int endIndex, TextTransform textTransform) {
        String lastCut = textTransform.getLastCut();

        StringBuilder sb = new StringBuilder();

        if (startIndex == endIndex) {
            sb.append(text.substring(0, startIndex)).append(lastCut).append(text.substring(startIndex));
        } else {
            sb.append(text.substring(0, startIndex)).append(lastCut).append(text.substring(endIndex));
        }
        textTransform.setText(sb.toString());
    }
}