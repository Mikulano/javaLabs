package lab1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by ihb on 28.12.16.
 */
public class Main {

    private static final String PATH = "src/main/resources/file";

    public static void main(String[] args) throws IOException {

        File file = new File(PATH);

        System.out.println(FileUtils.readFileToString(file, "utf-8"));
    }
}
