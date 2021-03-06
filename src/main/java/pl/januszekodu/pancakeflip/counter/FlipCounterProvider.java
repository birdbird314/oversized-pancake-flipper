package pl.januszekodu.pancakeflip.counter;

import java.util.HashMap;
import java.util.Map;

public class FlipCounterProvider {

  private Map<Integer, FlipCounter> counters = new HashMap<>();

  public FlipCounter get(int flipperSize) {
    if (!counters.containsKey(flipperSize)) {
      counters.put(flipperSize, new FlipCounter(flipperSize));
    }
    return counters.get(flipperSize);
  }
}
