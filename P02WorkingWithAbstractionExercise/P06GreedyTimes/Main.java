
package P02WorkingWithAbstractionExercise.P06GreedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long input = Long.parseLong(scanner.nextLine());
        String[] safe = scanner.nextLine().split("\\s+");

        LinkedHashMap<String, LinkedHashMap<String, Long>> bag = new LinkedHashMap<String, LinkedHashMap<String, Long>>();
        long gold = 0;
        long kindsOfGems = 0;
        long cash = 0;

        for (int i = 0; i < safe.length; i += 2) {
            String name = safe[i];
            long number = Long.parseLong(safe[i + 1]);

            String nameSave = "";

            if (name.length() == 3) {
                nameSave = "Cash";
            } else if (name.toLowerCase().endsWith("gem")) {
                nameSave = "Gem";
            } else if (name.toLowerCase().equals("gold")) {
                nameSave = "Gold";
            }

            if (nameSave.equals("")) {
                continue;
            } else {
                long sum = 0L;
                for (LinkedHashMap<String, Long> stringLongLinkedHashMap : bag.values()) {
                    Collection<Long> values = stringLongLinkedHashMap.values();
                    for (Long e : values) {
                        long l = e;
                        sum += l;
                    }
                }
                if (input < sum + number) {
                    continue;
                }
            }

            switch (nameSave) {
                case "Gem":
                    if (!bag.containsKey(nameSave)) {
                        if (bag.containsKey("Gold")) {
                            if (number > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(nameSave).values().stream().mapToLong(e -> e).sum() + number > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
                case "Cash":
                    if (!bag.containsKey(nameSave)) {
                        if (bag.containsKey("Gem")) {
                            if (number > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(nameSave).values().stream().mapToLong(e -> e).sum() + number > bag.get("Gem").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
            }

            if (!bag.containsKey(nameSave)) {
                bag.put((nameSave), new LinkedHashMap<String, Long>());
            }

            if (!bag.get(nameSave).containsKey(name)) {
                bag.get(nameSave).put(name, 0L);
            }


            bag.get(nameSave).put(name, bag.get(nameSave).get(name) + number);
            if (nameSave.equals("Gold")) {
                gold += number;
            } else if (nameSave.equals("Gem")) {
                kindsOfGems += number;
            } else if (nameSave.equals("Cash")) {
                cash += number;
            }
        }

        for (var x : bag.entrySet()) {
            Long sumValues = x.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", x.getKey(), sumValues));

            x.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }
}