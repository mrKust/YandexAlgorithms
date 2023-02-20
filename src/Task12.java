import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Task12 {

    public static void main(String[] args) {
        List<String> inputData = readData();

        List<String> outputData = new ArrayList<>();
        outputData.add(checkSymbols(inputData.get(0)));

        writeData(outputData);
    }

    public static String checkSymbols(String text) {

        int sumFlag = 0;
        Stack<Character> helper = new Stack<>();
        char[] textToChars = text.toCharArray();

        for (int i = 0; i < textToChars.length; i++) {
            char tmp = textToChars[i];
            if ( (tmp == '{') || (tmp == '[') || (tmp == '(') ) {
                sumFlag++;
                helper.push(tmp);
            } else {
                if (helper.size() == 0)
                    return "no";
                char tmp2 = helper.pop();
                if ( ((tmp2 == '{') && (tmp == '}')) ||
                        ((tmp2 == '(') && (tmp == ')')) ||
                        ((tmp2 == '[') && (tmp == ']')) ) {
                    sumFlag--;
                } else return "no";

            }
            if (sumFlag < 0)
                return "no";
        }

        if (sumFlag > 0)
            return "no";

        return "yes";
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
