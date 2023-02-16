import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) {

        List<String> inputData = readData();
        int numberOfChanges = Integer.parseInt(inputData.get(0));
        String text = inputData.get(1);

        List<String> outputData = new ArrayList<>();
        outputData.add(String.valueOf(getMaxSubstringLength(numberOfChanges, text)));

        writeData(outputData);

    }

    private static int getMaxSubstringLength(int numberOfChanges, String text) {
        int maxVal = 0;

        for (int i = 0; i < (text.length() - numberOfChanges ); i++) {
            char currentChar = text.charAt(i);
            StringBuilder tmpString = new StringBuilder(text);
            for (int k = 1; k <= numberOfChanges; k++) {
                tmpString.replace(i + k, i + k + 1, String.valueOf(currentChar));
            }
            int tmpMax = 0;
            for (int k = 0; k < tmpString.length(); k++) {
                if (currentChar == tmpString.charAt(k)) {
                    tmpMax++;
                } else {
                    if (tmpMax > maxVal) {
                        maxVal = tmpMax;
                    }
                    tmpMax = 0;
                }
            }
        }

        return maxVal;
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