import java.io.*;
import java.util.*;

public class Task22 {

    public static void main(String[] args) {
        List<String> inputData = readData();
        String[] tmp = inputData.get(0).split(" ");
        int lengthOfBoard = Integer.parseInt(tmp[0]);
        int maxLengthOfJump = Integer.parseInt(tmp[1]);
        List<String> outputData = new ArrayList<>();
        outputData.add(String.valueOf(countMaxNumberOfVariants(lengthOfBoard, maxLengthOfJump)));
        writeData(outputData);
    }

    private static int countMaxNumberOfVariants(int n, int k) {
        if (n == 1)
            return 1;

        int[] countOfVariants = new int[n];
        int min = Math.min(n, k);
        for (int i = 0; i < min; i++) {
            countOfVariants[i] = (int) Math.pow(2, i);
        }

        for (int i = min; i < n - 1; i++) {
            for (int j = k; j > 0; j--) {
                countOfVariants[i] += countOfVariants[i - j];
            }
        }

        return countOfVariants[n - 2];
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
