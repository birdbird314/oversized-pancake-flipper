package pl.januszekodu.pancakeflip.casehandler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.januszekodu.pancakeflip.counter.FlipCounter;
import pl.januszekodu.pancakeflip.counter.FlipCounterProvider;
import pl.januszekodu.pancakeflip.counter.PancakeRow;
import pl.januszekodu.pancakeflip.counter.PancakeSide;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static pl.januszekodu.pancakeflip.counter.PancakeSide.BLANK;
import static pl.januszekodu.pancakeflip.counter.PancakeSide.HAPPY;

@RunWith(MockitoJUnitRunner.class)
public class CaseSuiteSolverTest {

  @Mock private FlipCounterProvider flipCounterProvider;
  @Mock private FlipCounter flipCounter;

  @InjectMocks private CaseSuiteSolver solver;

  @Test
  public void shouldSolveSingleCase() {
    String input =
        "1" + "\n" +
        "-+ 1";
    given(flipCounterProvider.get(eq(1))).willReturn(flipCounter);
    given(flipCounter.count(eq(aRowOf(BLANK, HAPPY)))).willReturn("someValue");

    String result = solver.solve(input);

    assertThat(result).isEqualTo("Case #1: someValue");
  }

  @Test
  public void shouldSolveMultipleCases() {
    String input =
        "2" + "\n" +
        "-+ 1" + "\n" +
        "+- 2";
    given(flipCounterProvider.get(anyInt())).willReturn(flipCounter);
    given(flipCounter.count(eq(aRowOf(BLANK, HAPPY)))).willReturn("firstCaseResult");
    given(flipCounter.count(eq(aRowOf(HAPPY, BLANK)))).willReturn("secondCaseResult");

    String result = solver.solve(input);

    assertThat(result).isEqualTo(
        "Case #1: firstCaseResult" + "\n" +
        "Case #2: secondCaseResult"
    );
  }

  private static PancakeRow aRowOf(PancakeSide... sides) {
    return PancakeRow.of(sides);
  }
}
