import java.io.*;
import java.util.*;

public class Task15 {

    public static void main(String[] args) {
        List<String> inputData = readData();

        List<String> outputData = new ArrayList<>();
        int numberOfTowns = Integer.parseInt(inputData.get(0));
        Map<Integer, Integer> moneyToLiveInTown = new HashMap<>();
        String[] tmp = inputData.get(1).split(" ");
        for (int i = 0; i < tmp.length; i++) {
            moneyToLiveInTown.put(i, Integer.parseInt(tmp[i]));
        }

        outputData.add(migrationMap(numberOfTowns, moneyToLiveInTown));
        writeData(outputData);
    }

    public static String migrationMap(int numberOfTowns, Map<Integer, Integer> moneyToLiveInTown) {

        class Pair {
               int index;
               int value;

               Pair(int index, int value) {
                   this.index = index;
                   this.value = value;
               }
        }

        Stack<Pair> helper = new Stack<>();
        int[] finalDistanation = new int[numberOfTowns];

        for (int i = 0; i < numberOfTowns; i++) {
            finalDistanation[i] = -1;
        }

        for (int i = 0; i < numberOfTowns; i++) {

            Pair currentPair = new Pair(i, moneyToLiveInTown.get(i));

            if (!helper.isEmpty()) {
                Pair checkVal = helper.peek();
                while (currentPair.value < checkVal.value) {
                    finalDistanation[checkVal.index] = currentPair.index;
                    helper.pop();
                    if (helper.isEmpty())
                        break;
                    checkVal = helper.peek();
                }
            }
            helper.push(currentPair);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numberOfTowns; i++) {
            result.append(finalDistanation[i]);
            result.append(" ");
        }

        return result.toString();
    }

    private static List<String> readData() {
        List<String> text = new ArrayList<>();
        try (BufferedReader inputFile = new BufferedReader(new FileReader("input.txt"))) {
            String currentLine = inputFile.readLine();

            while (currentLine != null) {
                text.add(currentLine);
                currentLine = inputFile.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    private static void writeData(List<String> writeData) {
        try (BufferedWriter outputFile = new BufferedWriter(new FileWriter("output.txt"))) {

            for (String currentLine : writeData) {
                outputFile.write(currentLine);
            }

            outputFile.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
