package exceptions;

import java.io.FileNotFoundException;

public class WrongFilePermissionsEX extends FileNotFoundException {
    public WrongFilePermissionsEX(String message) {
        super(message);
    }
}
