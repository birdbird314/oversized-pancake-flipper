package pl.januszekodu.pancakeflip.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.stream.Collectors.joining;

public class IOUtils {
  public static void write(String content, String path) {
    try (PrintWriter out = new PrintWriter(path)) {
      out.print(content);
      out.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static String read(String path) {
    try {
      return Files.readAllLines(Paths.get(path)).stream().collect(joining("\n"));
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }
}
