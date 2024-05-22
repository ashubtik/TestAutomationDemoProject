package test.framework.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class JsonUtils {
    private static final String JSON_PATH = "src/test/resources/testdata/";

    @SneakyThrows
    public static <T> T deserializeFromJsonFile(String filename, Class<T> classType) {
        return new ObjectMapper().readValue(Files.readAllBytes(Paths.get(JSON_PATH.concat(filename))), classType);
    }
}
