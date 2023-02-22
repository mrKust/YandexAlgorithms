import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task16 {

    public static void main(String[] args) {
        List<String> inputData = readData();

        List<String> outputData = new ArrayList<>();
        outputData.add(queueWorks(inputData));

        writeData(outputData);
    }

    public static String queueWorks(List<String> commands) {
        StringBuilder result = new StringBuilder();
        int queueSize = commands.size();
        int[] circleQueue = new int[queueSize];
        int headIndex = 0;
        int tailIndex = 0;
        int elementsInQueue = 0;

        for (String currentCommand : commands) {
            String[] data = currentCommand.split(" ");
            switch (data[0]) {
                case "push":
                    int posInput = tailIndex % queueSize;
                    circleQueue[posInput] = Integer.parseInt(data[1]);
                    tailIndex++;
                    elementsInQueue++;
                    result.append("ok\n");
                    break;
                case "pop":
                    if (elementsInQueue > 0) {
                        int posGet = headIndex % queueSize;
                        result.append(circleQueue[posGet]);
                        result.append("\n");
                        headIndex++;
                        elementsInQueue--;
                    } else result.append("error\n");
                    break;
                case "front":
                    if (elementsInQueue > 0) {
                        int posGet = headIndex % queueSize;
                        result.append(circleQueue[posGet]);
                        result.append("\n");
                    } else result.append("error\n");
                    break;
                case "size":
                    result.append(elementsInQueue);
                    result.append("\n");
                    break;
                case "clear":
                    headIndex = 0;
                    tailIndex = 0;
                    elementsInQueue = 0;
                    result.append("ok\n");
                    break;
                case "exit":
                    result.append("bye");
                    return result.toString();
            }
        }

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