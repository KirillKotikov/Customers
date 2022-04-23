package parser;

import java.io.File;

public interface Parser {
    Object read(String fileName);
    void write(String fileName, Object object);
}
