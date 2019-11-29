package com.bayview.user.autodial.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.*;
import java.util.List;

public class CSVUtils {
    public static <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
        CsvMapper mapper = new CsvMapper();
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
        ObjectReader reader = mapper.readerFor(clazz).with(schema);
        return reader.<T>readValues(stream).readAll();
    }

    public static <T> File write(Class<T> clazz, List<T> data, String fileName) throws Exception {
        // create mapper and schema
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(false);
        // output writer
        ObjectWriter myObjectWriter = mapper.writer(schema);
        File tempFile = File.createTempFile(fileName, ".csv");
        FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream);
        OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
        myObjectWriter.writeValue(writerOutputStream, data);
        return tempFile;
    }

}
