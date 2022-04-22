package parser;

import java.io.File;

public interface Parser {
    Object read(File file);
    void write(File file, Object object);
}
