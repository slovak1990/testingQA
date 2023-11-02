package hackMeBank11.api.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileHelper {
    @Step("Преобразование объект класса в формат JSON")
    public static void createJsonFile(Object object, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(object);
            FileUtils.writeStringToFile(new File(filePath), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}