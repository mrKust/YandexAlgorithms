import java.io.*;
import java.util.*;

public class Task25 {

    public static void main(String[] args) {
        List<String> inputData = readData();
        int n = Integer.parseInt(inputData.get(0));
        int[] placeData = new int[n + 1];
        String[] tmp = inputData.get(1).split(" ");
        for (int i = 1; i <= n; i++) {
            placeData[i] = Integer.parseInt(tmp[i - 1]);
        }
        Arrays.sort(placeData);
        List<String> outputData = new ArrayList<>();
        outputData.add(String.valueOf(countMinTime(n, placeData)));
        writeData(outputData);
    }

    private static int countMinTime(int n, int[] a) {

        int[] dp = new int[n + 1];
        dp[2] = a[2] - a[1];
        if (n > 2) {
            dp[3] = a[3] - a[1];
            for (int i = 4; i <= n; i++) {
                int min = Math.min(dp[i - 2], dp[i - 1]);
                int tmpLength = a[i] - a[i - 1];
                dp[i] = min + tmpLength;
            }
        }

        return dp[dp.length - 1];
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
