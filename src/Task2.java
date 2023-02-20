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

        char[] chars = text.toCharArray();
        int[] w = new int[26];
        int max = 0;
        int p = 0;
        for (int i = 0; i < chars.length; i++) {
            int tmp = ++w[chars[i] - 'a'];
            max = Math.max(max, tmp);
            if (max + numberOfChanges - 1 < i - p)
                w[chars[p++] - 'a']--;
        }
        return chars.length - p;
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