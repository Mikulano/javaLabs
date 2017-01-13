import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ihb on 28.12.16.
 */
public class test1 {

    File file;
    String str = "QQQZAZA";

    @Before
    public void createFile() throws IOException {

        file = new File("src/main/resources/file");

        FileUtils.write(file, str, "utf-8", false);
    }


    @Test
    public void testFile() throws IOException {
        assertTrue(str.equals(FileUtils.readFileToString(file, "utf-8")));
    }

    @Test
    public void test2() throws IOException {
        assertFalse((str+"add").equals(FileUtils.readFileToString(file, "utf-8")));
    }

    @Test
    public void test3() throws IOException {
        FileUtils.deleteQuietly(file);
        FileUtils.touch(file);
        assertTrue("".equals(FileUtils.readFileToString(file, "utf-8")));
    }
    @Test(expected = FileNotFoundException.class)
    public void test4() throws IOException {
        file = new File ("src/main/resources/file1");
        assertFalse((str+"add").equals(FileUtils.readFileToString(file, "utf-8")));
    }
}
