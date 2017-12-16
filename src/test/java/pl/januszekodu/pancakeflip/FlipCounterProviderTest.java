package pl.januszekodu.pancakeflip;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlipCounterProviderTest {
  @Test
  public void shouldProvideFlipCounterWithGivenFlipperSize() {
    FlipCounterProvider provider = new FlipCounterProvider();

    FlipCounter counter = provider.get(1);

    assertThat(counter).isNotNull();
  }

  @Test
  public void shouldCreateExacltyOneCounterForGivenSize() {
    FlipCounterProvider provider = new FlipCounterProvider();

    FlipCounter firstCounter = provider.get(1);
    FlipCounter secondCounter = provider.get(1);

    assertThat(firstCounter == secondCounter).isTrue();
  }
}
