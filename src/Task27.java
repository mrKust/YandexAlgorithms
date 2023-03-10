import java.io.*;
import java.util.*;

public class Task27 {

    public static void main(String[] args) {
        List<String> inputData = readData();
        String[] tmp = inputData.get(0).split(" ");
        int n = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        int[][] field = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String[] currentString = inputData.get(i).split(" ");
            for (int k = 1; k <= m; k++) {
                field[i][k] = Integer.parseInt(currentString[k - 1]);
            }
        }
        List<String> outputData = new ArrayList<>();
        outputData.add(String.valueOf(searchMax(n, m, field)));
        writeData(outputData);
    }

    public static String searchMax(int n, int k, int[][] data) {

        int[][] dp = new int[n + 1][k + 1];
        dp[1][1] = data[1][1];
        for (int i = 2; i < k + 1; i++)
            dp[1][i] += dp[1][i - 1] + data[1][i];
        for (int i = 2; i < n + 1; i++)
            dp[i][1] += dp[i - 1][1] + data[i][1];

        for (int i = 2; i < n + 1; i++) {
            for (int j = 2; j < k + 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + data[i][j];
            }
        }
        StringBuilder result = new StringBuilder();

        int i = n;
        int j = k;
        while ((i > 1) || (j > 1)) {
            int current = dp[i][j];
            int max = Math.max(dp[i - 1][j], dp[i][j - 1]);
            if (max == dp[i - 1][j]) {
                result.insert(0, "D ");
                i = i - 1;
            }
            else {
                result.insert(0, "R ");
                j = j - 1;
            }
        }
        result.insert(0, dp[n][k] + "\n");

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
