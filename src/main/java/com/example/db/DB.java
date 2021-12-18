package com.example.db;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DB extends Application {
    static final ObjectMapper objectMapper = new ObjectMapper();
    static AgentsBase agentsBase = new AgentsBase();

    public static void main(String[] args) throws Exception {


        launch();
        Serializer serializer = new Serializer();
        serializer.run();
        ExcelWriter writeXLS = new ExcelWriter(agentsBase);
        writeXLS.run("Agents.xlsx");
    }


    public static void deserialize() throws IOException {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        objectMapper.registerModule(javaTimeModule);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        File dir = new File(".\\target\\save");
        File[] arrFiles = dir.listFiles();
        assert arrFiles != null;
        List<File> lst = Arrays.asList(arrFiles);
        lst.sort(Comparator.comparing(File::getName));
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        agentsBase = objectMapper.readValue(new File(lst.get(lst.size()-1).getPath()), agentsBase.getClass());
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DB.class.getResource("DB.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PentagonDB");
        stage.setScene(scene);
        stage.show();
    }


}