package ee.nortal.tdd;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;

public class TestUtils {

  public static String readFileBody(String fileName) {
    try {
      ClassLoader classLoader = SignatureTest.class.getClassLoader();
      URL resource = classLoader.getResource(fileName);
      assertNotNull("File not found: " + fileName, resource);
      File file = new File(resource.getFile());
      return FileUtils.readFileToString(file, "UTF-8");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
