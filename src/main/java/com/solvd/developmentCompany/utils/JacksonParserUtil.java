package com.solvd.developmentCompany.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JacksonParserUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static <T> T deserialize(InputStream is, Class<T> clazz) throws IOException {
        return mapper.readValue(is, clazz);
    }

    public static void serialize(Object object, File file) throws IOException {
        mapper.writeValue(file, object);
    }
}