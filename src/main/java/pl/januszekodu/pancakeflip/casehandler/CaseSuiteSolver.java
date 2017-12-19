package pl.januszekodu.pancakeflip.casehandler;

import pl.januszekodu.pancakeflip.counter.FlipCounter;
import pl.januszekodu.pancakeflip.counter.FlipCounterProvider;
import pl.januszekodu.pancakeflip.counter.PancakeRow;
import pl.januszekodu.pancakeflip.counter.PancakeSide;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

public class CaseSuiteSolver {

  private static final String LINE_DELIMITER_REGEX = "\n|\r\n";
  private final FlipCounterProvider counterProvider;

  public CaseSuiteSolver(FlipCounterProvider counterProvider) {
    this.counterProvider = counterProvider;
  }

  public String solve(String input) {
    int suiteSize = extractCaseSuiteSize(input);
    String[] cases = input.split(LINE_DELIMITER_REGEX);
    return range(1, suiteSize + 1)
        .mapToObj(caseNum -> solveSingleCase(cases[caseNum], caseNum))
        .collect(joining("\n"));
  }

  private String solveSingleCase(String singleCase, int caseNum) {
    int size = extractFlipperSize(singleCase);
    PancakeRow row = extractPancakeRow(singleCase);
    FlipCounter counter = counterProvider.get(size);
    return "Case #" + caseNum + ": " + counter.count(row);
  }

  private int extractCaseSuiteSize(String singleCaseLine) {
    return parseInt(singleCaseLine.split(LINE_DELIMITER_REGEX)[0]);
  }

  private int extractFlipperSize(String singleCaseLine) {
    return extractCaseSuiteSize(singleCaseLine.split(" ")[1]);
  }

  private String extractPancakeRowString(String singleCaseLine) {
    return singleCaseLine.split(" ")[0];
  }

  private PancakeRow extractPancakeRow(String singleCaseLine) {
    return PancakeRow.of(
        stream(extractPancakeRowString(singleCaseLine).split(""))
            .map(PancakeSide::fromString)
            .toArray(PancakeSide[]::new)
    );
  }
}
