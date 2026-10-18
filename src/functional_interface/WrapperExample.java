package functional_interface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

// Handling Errors (Checked Exceptions)
public class WrapperExample {

    // 1. Define the custom interface that ALLOWS exceptions
    @FunctionalInterface
    interface ThrowingConsumer<T> {
        void accept(T t) throws Exception;
    }

    // 2. The "Wrap" method: It converts ThrowingConsumer into a regular Consumer
    public static <T> Consumer<T> wrap(ThrowingConsumer<T> action) {
        return i -> {
            try {
                action.accept(i); // Run the "scary" code
            } catch (Exception e) {
                // If it fails, turn it into a RuntimeException
                throw new RuntimeException("Something went wrong with: " + i, e);
            }
        };
    }

    public static void main(String[] args) {
        List<String> files = Arrays.asList("non_existent_file.txt", "config.txt");

        // 3. Clean usage: No try-catch blocks visible here!
        files.forEach(wrap(file -> {
        	// Files.readString throws IOException, but wrap() handles it
            byte[] bytes = Files.readAllBytes(Paths.get(file));
            String content = new String(bytes);
            
            System.out.println("File content: " + content);
        }));
    }
}
