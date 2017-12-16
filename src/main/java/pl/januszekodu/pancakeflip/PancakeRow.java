package pl.januszekodu.pancakeflip;

import static java.util.stream.IntStream.range;

class PancakeRow {

  private final PancakeSide[] sides;

  static PancakeRow of(PancakeSide[] sides) {
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

}
