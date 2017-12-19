package pl.januszekodu.pancakeflip.counter;

import java.util.Arrays;

import static java.util.stream.IntStream.range;

public class PancakeRow {

  private final PancakeSide[] sides;

  public static PancakeRow of(PancakeSide[] sides) {
    return new PancakeRow(sides);
  }

  private PancakeRow(PancakeSide[] sides) {
    this.sides = sides;
  }

  int getSize() {
    return sides.length;
  }

  PancakeSide getSideAt(Integer index) {
    return sides[index];
  }

  PancakeRow flip(int index, int flipperSize) {
    PancakeSide[] sides = range(0, getSize())
        .mapToObj(i -> flipSideIfNeeded(i, index, flipperSize))
        .toArray(PancakeSide[]::new);
    return of(sides);
  }

  private PancakeSide flipSideIfNeeded(int i, int index, int flipperSize) {
    if (i < index + flipperSize && i >= index)
      return PancakeSide.revert(getSideAt(i));
    else
      return getSideAt(i);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PancakeRow that = (PancakeRow) o;
    return Arrays.equals(sides, that.sides);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(sides);
  }
}
