import java.io.*;
import java.util.*;

public class Task21 {

    public static void main(String[] args) {
        List<String> inputData = readData();
        int lengthOfSubstring = Integer.parseInt(inputData.get(0));
        List<String> outputData = new ArrayList<>();
        outputData.add(String.valueOf(countMaxSubstringLength(lengthOfSubstring)));
        writeData(outputData);
    }

    private static int countMaxSubstringLength(int n) {
        int[] countOfVariants = new int[n + 2];
        countOfVariants[0] = 1;
        countOfVariants[1] = 2;
        countOfVariants[2] = 4;

        for (int i = 3; i <= n; i++) {
            countOfVariants[i] = countOfVariants[i - 1] + countOfVariants[i - 2] + countOfVariants[i - 3];
        }

        return countOfVariants[n];
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
