package util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class JSONParser {
    private HashMap<String, String> result = new HashMap<>();

    public HashMap<String, String> parsingJSON(String path) {

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(
                            path));
            result = new ObjectMapper().readValue(reader, HashMap.class);
        } catch (IOException e) {
            System.out.println("JSON parsing has error");
        }
        return result;
    }

}
