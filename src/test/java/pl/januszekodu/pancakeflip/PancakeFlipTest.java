package pl.januszekodu.pancakeflip;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.januszekodu.pancakeflip.PancakeSide.BLANK;
import static pl.januszekodu.pancakeflip.PancakeSide.HAPPY;

@RunWith(JUnitParamsRunner.class)
public class PancakeFlipTest {

  static Object[] params() {
    return $(
        $(aRowOf(HAPPY), 1, "0"),
        $(aRowOf(BLANK, BLANK), 1, "2"),
        $(aRowOf(BLANK, BLANK), 2, "1"),
        $(aRowOf(BLANK, HAPPY, BLANK), 2, "2"),
        $(aRowOf(HAPPY, BLANK, HAPPY), 2, "IMPOSSIBLE"),
        $(aRowOf(BLANK, HAPPY, HAPPY, BLANK), 3, "2"),
        $(aRowOf(BLANK, BLANK, BLANK, HAPPY, BLANK, HAPPY, HAPPY, BLANK), 3, "3"),
        $(aRowOf(BLANK, HAPPY, HAPPY, BLANK, HAPPY, BLANK, BLANK, BLANK), 3, "3")
    );
  }

  @Test
  @Parameters(method = "params")
  public void shouldCountFlips(PancakeRow row, int flipperSize, String resutl) {
    FlipCounter flipCounter = new FlipCounter(flipperSize);

    String flips = flipCounter.count(row);

    assertThat(flips).isEqualTo(resutl);
  }

  private static PancakeRow aRowOf(PancakeSide... sides) {
    return PancakeRow.of(sides);
  }

}
