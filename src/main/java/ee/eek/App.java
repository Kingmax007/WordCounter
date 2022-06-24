package ee.eek;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class App {
    private App() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Kingsley\\WordCount\\src\\test\\java\\ee\\eek\\ignatius.txt";

        String wordCount = convertFileToString(filePath);

        List<String> list = Stream.of(wordCount)
                .map(w -> w.split("[()\"!?,;.\\s]+"))
                .flatMap(Arrays::stream)
                .toList();

        //Collect word and iterate number of time it occurs
        Map<String, Integer> wordCounter = list.stream()
                .collect(Collectors.toMap(String::toLowerCase, w -> 1, Integer::sum));

        //Sort by Reversing the order
        wordCounter.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }

    private static String convertFileToString(String filePath) {

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream
                     = Files.lines(Paths.get(filePath),
                StandardCharsets.UTF_8)) {
            stream.forEach(
                    s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
