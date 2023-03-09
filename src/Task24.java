import java.io.*;
import java.util.*;

public class Task24 {

    public static void main(String[] args) {
        List<String> inputData = readData();
        int n = Integer.parseInt(inputData.get(0));
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        int[] c = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            String[] tmp = inputData.get(i).split(" ");
            a[i] = Integer.parseInt(tmp[0]);
            b[i] = Integer.parseInt(tmp[1]);
            c[i] = Integer.parseInt(tmp[2]);
        }
        List<String> outputData = new ArrayList<>();
        outputData.add(String.valueOf(countMinTime(n, a, b, c)));
        writeData(outputData);
    }

    private static int countMinTime(int n, int[] a, int[] b, int[] c) {
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        for (int i = 1; i <= n; i++) {
            int min = dp.get(i - 1) + a[i];
            if (i >= 2)
                min = Math.min(min, dp.get(i - 2) + b[i - 1]);
            if (i >= 3)
                min = Math.min(min, dp.get(i - 3) + c[i - 2]);
            dp.add(min);

        }

        return dp.get(dp.size() - 1);
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
