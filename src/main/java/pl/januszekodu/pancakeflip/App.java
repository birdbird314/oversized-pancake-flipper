package pl.januszekodu.pancakeflip;

import pl.januszekodu.pancakeflip.casehandler.CaseSuiteSolver;
import pl.januszekodu.pancakeflip.counter.FlipCounterProvider;
import pl.januszekodu.pancakeflip.io.IOUtils;

public class App {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Specify path to file with test cases");
      System.exit(0);
    }
    String testCasesFilePath = args[0];
    log("Reading cases from " + testCasesFilePath);
    CaseSuiteSolver solver = new CaseSuiteSolver(new FlipCounterProvider());
    log("Solving...");
    String results = solver.solve(IOUtils.read(testCasesFilePath));
    log("Writing results to out.txt");
    IOUtils.write(results, "out.txt");
  }

  public static void log(String message) {
    System.out.print("[INFO] " + message);
  }
}
