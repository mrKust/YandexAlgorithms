import java.io.*;
import java.util.*;

public class Task23 {

    public static void main(String[] args) {
        List<String> inputData = readData();
        int n = Integer.parseInt(inputData.get(0));
        List<String> outputData = new ArrayList<>();
        outputData.add(countMinNumberOfOperations(n));
        writeData(outputData);
    }

    private static String countMinNumberOfOperations(int n) {

        int[] numberOfActions = new int[n + 1];

        numberOfActions[1] = 0;
        int min = 0;
        for (int i = 2; i < n + 1; i++) {
            min = numberOfActions[i - 1];
            if (i % 2 == 0)
                min = Math.min(min, numberOfActions[i / 2]);
            if (i % 3 == 0)
                min = Math.min(min, numberOfActions[i / 3]);

            numberOfActions[i] = ++min;
        }

        StringBuilder result = new StringBuilder();
        int i = n;
        result.append(n).append(" ");
        while (i > 1) {
            if (numberOfActions[i] == numberOfActions[i - 1] + 1)
                i--;
            else if ((i % 2 == 0) && (numberOfActions[i] == numberOfActions[i / 2] + 1))
                    i /= 2;
            else {
                i /= 3;
            }
            result.insert(0, i + " ");
        }

        result.insert(0, numberOfActions[n] + "\n");

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
