package hu.szte.inf.utils.db;

import hu.szte.inf.models.Course;
import hu.szte.inf.models.Grade;
import hu.szte.inf.models.Semester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DbInitializer {

    private static final int SEED = 42;
    private static final int SIZE = 10;
    private static final Random rnd = new Random(SEED);

    private static String charNameRandomizer() {
        return rnd.ints(32, 128)
                .limit(32)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static String nameRandomizer() {
        var words = new ArrayList<String>();
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wordlist.txt")) {
            if (stream != null) {
                try (InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                     BufferedReader reader = new BufferedReader(streamReader)) {
                    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                        words.add(line);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // fallback
        if (words.size() == 0) {
            return charNameRandomizer();
        }

        int nWords = rnd.nextInt(5) + 3;
        var selectedWords = IntStream.range(0, nWords)
                .mapToObj((i) -> words.get(rnd.nextInt(words.size())))
                .collect(Collectors.toList());
        return String.join(" ", selectedWords);
    }

    public static List<Course> getDefaultCourses(int size) {

        return IntStream.range(0, size)
                .mapToObj((mapper) -> new Course(
                        nameRandomizer(),
                        rnd.nextInt(100),
                        Semester.values()[rnd.nextInt(Semester.values().length)],
                        rnd.nextBoolean() ? Grade.values()[rnd.nextInt(Grade.values().length)] : null))
                .collect(Collectors.toList());
    }

    public static List<Course> getDefaultCourses() {
        return getDefaultCourses(SIZE);
    }
}
