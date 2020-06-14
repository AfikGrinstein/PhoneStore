package il.co.hit.model.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FileManager<T> {

    private final String filename;
    private final Gson gson;
    private final Type type;

    public FileManager(String filename) {
        this.filename = filename;
        this.gson = new Gson();
        this.type = new TypeToken<Set<T>>() {}.getType();
    }

    private boolean isFileExists() {
        File file = new File(filename);
        return file.exists();
    }

    public void write(Set<T> object) throws IOException {
        String json = this.gson.toJson(object, type);
        Files.write(Paths.get(this.filename), json.getBytes());
    }

    public Set<T> read() throws IOException, ClassNotFoundException {
        if (!isFileExists()) { // Return empty set when file is not exists
            return new HashSet<>();
        }

        String json = Files.lines(Paths.get(this.filename)).collect(Collectors.joining(System.lineSeparator()));
        return this.gson.fromJson(json, type);
    }
}
