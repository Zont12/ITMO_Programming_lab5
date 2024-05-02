package managers;

import exceptions.WrongFilePermissionsEX;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileloaderManager {

    public FileloaderManager() {
    }

    public File loadFile(String filePath, String extension, String permissions, String fileName) throws FileNotFoundException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new FileNotFoundException(String.format("%s не существует!", fileName));
        }
        if (Files.isDirectory(path)) {
            throw new FileNotFoundException("Ошибка: Этот путь директория!");
        }
        if (!filePath.endsWith(String.format(".%s", extension))) {
            throw new FileNotFoundException(String.format("Ошибка: %s должен быть .%s!", fileName, extension));
        }
        if (permissions.contains("r") && !Files.isReadable(path)) {
            throw new WrongFilePermissionsEX(String.format("Ошибка: нету прав на чтение файла!", fileName));
        }
        if (permissions.contains("w") && !Files.isWritable(path)) {
            throw new WrongFilePermissionsEX(String.format("Ошибка: нету прав на чтение файла", fileName));
        }
        return new File(filePath);

    }
}
