package ua.com.alevel;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReadFromCsv {

    private final String fileName;

    public ReadFromCsv(String filename) {
        this.fileName = filename;
        Path path = Paths.get(fileName);

        try {
            boolean exists = Files.exists(path);
            if (!exists) {
                String fileDirectory = fileName.substring(0,fileName.lastIndexOf("/"));
                Files.createDirectories(Paths.get(fileDirectory));
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<? extends String[]> readAll() {
        List<String[]> values = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String split = ",";
                values.add(line.split(split));
            }
            br.close();
            return values;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}