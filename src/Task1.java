import java.io.*;
import java.util.*;

public class Task1 {
    public static void main(String[] args) {

        List<String> inputData = readData();
        StringBuilder text = new StringBuilder();
        inputData.forEach(text::append);

        List<String> outputData = buildGistogramm(text.toString().replace(" ", ""));

        writeData(outputData);

    }

    private static List<String> buildGistogramm(String inputText) {

        Map<Character, Integer> countSymbols = new HashMap<>();
        int maxAmount = 0;
        for (int i = 0; i < inputText.length(); i++) {
            char tmp = inputText.charAt(i);
            if (countSymbols.containsKey(tmp)) {
                int val = countSymbols.get(tmp);
                val++;
                if (val > maxAmount)
                    maxAmount = val;
                countSymbols.put(tmp, val);
            } else {
                if (maxAmount == 0)
                    maxAmount = 1;
                countSymbols.put(tmp, 1);
            }
        }
        Character[] result = countSymbols.keySet().toArray(new Character[0]);
        Arrays.sort(result);
        List<String> output = new ArrayList<>();
        for (int i = 0; i < maxAmount; i++) {
            StringBuilder tmp = new StringBuilder();
            for (int k = 0; k < result.length; k++) {
                char currentChar = result[k];
                int currentVal = countSymbols.get(currentChar);
                if (currentVal >= (maxAmount - i)) {
                    tmp.append('#');
                } else tmp.append(' ');
            }
            tmp.append("\n");
            output.add(tmp.toString());
        }
        StringBuilder labelString = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            labelString.append(result[i]);
        }
        output.add(labelString.toString());

        return output;
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