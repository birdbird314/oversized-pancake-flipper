package pl.januszekodu.pancakeflip.counter;

import java.util.OptionalInt;

import static java.lang.String.valueOf;
import static java.util.stream.IntStream.range;
import static pl.januszekodu.pancakeflip.counter.PancakeRowHelper.rangeIsBlank;
import static pl.januszekodu.pancakeflip.counter.PancakeRowHelper.rowRange;

public class FlipCounter {

  private static final String IMPOSSIBLE = "IMPOSSIBLE";

  private final int flipperSize;

  FlipCounter(int flipperSize) {
    this.flipperSize = flipperSize;
  }

  public String count(PancakeRow row) {
    return count(row, 0);
  }

  private String count(PancakeRow row, int alreadyDoneFlips) {
    OptionalInt groupToFlipIndex = getGroupToFlipIndex(row);
    if (groupToFlipIndex.isPresent()) {
      return countFlipsForRowFlippedAt(row, alreadyDoneFlips, groupToFlipIndex.getAsInt());
    }
    else if (PancakeRowHelper.hasOnlyHappySides(row)) {
      return valueOf(alreadyDoneFlips);
    }
    else {
      return IMPOSSIBLE;
    }
  }

  private String countFlipsForRowFlippedAt(PancakeRow row, int alreadyDoneFlips, int flipIndex) {
    return count(row.flip(flipIndex, flipperSize), alreadyDoneFlips + 1);
  }

  private OptionalInt getGroupToFlipIndex(PancakeRow row) {
    OptionalInt blankGroupIndex = indexOfBlankGroup(row);
    OptionalInt happyGroupIndex = indexOfHappyGroup(row);
    return blankGroupIndex.isPresent() ? blankGroupIndex : happyGroupIndex;
  }

  private OptionalInt indexOfBlankGroup(PancakeRow row) {
    return rowRange(row).filter(i -> isIndexOfBlankGroup(row, i)).findFirst();
  }

  private boolean isIndexOfBlankGroup(PancakeRow row, int index) {
    return index + flipperSize <= row.getSize()
        && rangeIsBlank(row, index, index + flipperSize);
  }

  private OptionalInt indexOfHappyGroup(PancakeRow row) {
     return range(0, row.getSize() - 1)
        .filter(index -> isIndexOfHappyGroup(row, index))
        .findFirst();
  }

  private boolean isIndexOfHappyGroup(PancakeRow row, int index) {
    return isIndexOfRightSidedHappyGroup(row, index)
        || isIndexOfLeftSidedHappyGroup(row, index);
  }

  //++-
  private boolean isIndexOfLeftSidedHappyGroup(PancakeRow row, int index) {
    return PancakeSide.HAPPY.equals(row.getSideAt(index))
        && PancakeRowHelper.findNextBlank(row, index) - index == flipperSize - 1
        && PancakeRowHelper.rightSideIsBlank(row, PancakeRowHelper.findNextBlank(row, index));
  }

  //-++
  private boolean isIndexOfRightSidedHappyGroup(PancakeRow row, int index) {
    return PancakeSide.BLANK.equals(row.getSideAt(index))
        && PancakeRowHelper.findNextBlank(row, index) - index == flipperSize
        && PancakeRowHelper.leftSideIsBlank(row, index);
  }

}
