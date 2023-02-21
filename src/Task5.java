import java.io.*;
import java.util.*;

public class Task5 {
    public static void main(String[] args) {

        List<String> inputData = readData();
        int numberOfLetters = Integer.parseInt(inputData.get(0));
        int[] amountOfLetters = new int[numberOfLetters];
        for (int i = 0; i < numberOfLetters; i++) {
            amountOfLetters[i] = Integer.parseInt(inputData.get(i + 1));
        }


        List<String> outputData = new ArrayList<>();
        outputData.add(countMax(amountOfLetters));

        writeData(outputData);

    }

    public static String countMax(int[] amountOfLetters) {
        long summaryAmount = 0;

        for (int i = 0; i < amountOfLetters.length - 1; i++) {
            summaryAmount += Math.min(amountOfLetters[i], amountOfLetters[i + 1]);
        }

        return String.valueOf(summaryAmount);
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