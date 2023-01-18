import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        printSeparation();
        System.out.println("Coded by Crown#0016");
        System.out.println("Write 'exit' to close the program");
        Scanner scanner = new Scanner(System.in);

        String upgrade;
        FisheryGear gear = null;
        boolean stop = false;
        boolean first = true;
        while (true) {
            if (stop) {
                stop = false;
            }
            printSeparation();
            System.out.print("Which Tool?\na - Fishing Robot (Daily)\nb - Fishing Net (Voice)\nc - Fishing Rod (Message)\nd - Work\n");
            upgrade = scanner.next();

            switch (upgrade.toLowerCase()) {
                case "exit" -> System.exit(1);
                case "a" -> gear = FisheryGear.DAILY;
                case "b" -> gear = FisheryGear.VOICE;
                case "c" -> gear = FisheryGear.MESSAGE;
                case "d" -> gear = FisheryGear.WORK;
                default -> stop = true;
            }

            if (stop) {
                System.out.printf("No results for %s\n", upgrade);
                continue;
            }

            boolean validInput;
            do {
                System.out.println("For which level?");

                validInput = true;
                String level;
                do {
                    level = scanner.next();
                    try {
                        int levelInt = Integer.parseInt(level) - 1;
                        long effect = getEffect(levelInt, gear);
                        System.out.printf("Effect: %s\n", NumberUtil.numberToString(effect));
                    } catch (NumberFormatException e) {
                        if (level.equalsIgnoreCase("exit"))
                            System.exit(1);
                        System.out.println("The level has to be a natural number");
                        validInput = false;
                    }
                } while (!validInput);

                if (!first) {
                    System.out.print("Which Upgrade?\na - Fishing Robot (Daily)\nb - Fishing Net (Voice)\nc - Fishing Rod (Message)\nd - Work\n");
                } else {
                    first = false;
                }
                break;

            } while (!validInput);
        }
    }

    public static long getValue(long level) {
        long n = level + 1;
        return n * (n + 1) / 2;
    }

    private static void printSeparation() {
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
    }

    public static long getEffect(long level, FisheryGear gear) {
        return getValue(level) * gear.getEffect();
    }
}