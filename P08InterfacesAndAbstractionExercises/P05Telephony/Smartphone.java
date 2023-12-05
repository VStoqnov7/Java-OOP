package P08InterfacesAndAbstractionExercises.P05Telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {

    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }
    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();

        for (String number : numbers) {
            boolean isValid = true;

            for (Character symbol : number.toCharArray()) {
                if (symbol < 48 || symbol > 57) {
                    sb.append("Invalid number!").append(System.lineSeparator());
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                sb.append(String.format("Calling... %s", number)).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();

        for (String url : urls) {
            boolean isValid = true;
            for (Character symbol : url.toCharArray()) {
                if (symbol >= 48 && symbol <= 57) {
                    sb.append("Invalid URL!").append(System.lineSeparator());
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                sb.append(String.format("Browsing: %s!", url)).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}

