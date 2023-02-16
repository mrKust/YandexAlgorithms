import java.io.*;
import java.util.*;

public class Task5 {
    public static void main(String[] args) {

        List<String> inputData = readData();
        int numberOfLetters = Integer.parseInt(inputData.get(0));
        int[] amountOfLetters = new int[numberOfLetters + 1];
        for (int i = 1; i <= numberOfLetters; i++) {
            amountOfLetters[i] = Integer.parseInt(inputData.get(i));
        }


        List<String> outputData = new ArrayList<>();
        outputData.add(countMax(amountOfLetters));

        writeData(outputData);

    }

    public static String countMax(int[] amountOfLetters) {
        int summaryAmount = 0;



        return "";
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