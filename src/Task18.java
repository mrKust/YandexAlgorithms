import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task18 {

    public static void main(String[] args) {
        List<String> inputData = readData();

        List<String> outputData = new ArrayList<>();
        outputData.add(dequeueWorks(inputData));

        writeData(outputData);
    }

    public static String dequeueWorks(List<String> commands) {
        StringBuilder result = new StringBuilder();
        List<Long> deque = new ArrayList<>();

        for (String currentCommand : commands) {
            String[] data = currentCommand.split(" ");
            switch (data[0]) {
                case "push_front":
                    deque.add(0, Long.parseLong(data[1]));
                    result.append("ok\n");
                    break;
                case "push_back":
                    deque.add(Long.parseLong(data[1]));
                    result.append("ok\n");
                    break;
                case "pop_front":
                    if (deque.size() != 0) {
                        result.append(deque.get(0));
                        result.append("\n");
                        deque.remove(0);
                    } else result.append("error\n");
                    break;
                case "pop_back":
                    if (deque.size() != 0) {
                        result.append(deque.get(deque.size() - 1));
                        result.append("\n");
                        deque.remove(deque.size() - 1);
                    } else result.append("error\n");
                    break;
                case "front":
                    if (deque.size() != 0) {
                        result.append(deque.get(0));
                        result.append("\n");
                    } else result.append("error\n");
                    break;
                case "back":
                    if (deque.size() != 0) {
                        result.append(deque.get(deque.size() - 1));
                        result.append("\n");
                    } else result.append("error\n");
                    break;
                case "size":
                    result.append(deque.size());
                    result.append("\n");
                    break;
                case "clear":
                    deque.clear();
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