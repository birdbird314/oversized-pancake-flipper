package pl.januszekodu.pancakeflip.counter;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

class PancakeRowHelper {
  private PancakeRowHelper() {
  }


  static boolean leftSideIsBlank(PancakeRow row, int index) {
    return range(0, index)
        .mapToObj(row::getSideAt)
        .noneMatch(PancakeSide.HAPPY::equals);
  }

  static boolean rightSideIsBlank(PancakeRow row, int index) {
    return range(index, row.getSize())
        .mapToObj(row::getSideAt)
        .noneMatch(PancakeSide.HAPPY::equals);
  }

  static boolean rangeIsBlank(PancakeRow row, int start, int end) {
    return range(start, end).mapToObj(row::getSideAt).noneMatch(PancakeSide.HAPPY::equals);
  }

  static int findNextBlank(PancakeRow row, int index) {
    return range(index + 1, row.getSize()).filter(i -> PancakeSide.BLANK.equals(row.getSideAt(i))).findFirst().orElse(-1);
  }

  static IntStream rowRange(PancakeRow row) {
    return range(0, row.getSize());
  }

  static boolean hasOnlyHappySides(PancakeRow row) {
    return rowRange(row).mapToObj(row::getSideAt).noneMatch(PancakeSide.BLANK::equals);
  }
}
