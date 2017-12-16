package pl.januszekodu.pancakeflip;

import java.util.OptionalInt;

import static java.lang.String.valueOf;
import static java.util.stream.IntStream.range;
import static pl.januszekodu.pancakeflip.PancakeRowHelper.findNextBlank;
import static pl.januszekodu.pancakeflip.PancakeRowHelper.leftSideIsBlank;
import static pl.januszekodu.pancakeflip.PancakeRowHelper.rangeIsBlank;
import static pl.januszekodu.pancakeflip.PancakeRowHelper.rightSideIsBlank;
import static pl.januszekodu.pancakeflip.PancakeRowHelper.rowRange;
import static pl.januszekodu.pancakeflip.PancakeSide.BLANK;
import static pl.januszekodu.pancakeflip.PancakeSide.HAPPY;

class FlipCounter {

  private static final String IMPOSSIBLE = "IMPOSSIBLE";

  private final int flipperSize;

  FlipCounter(int flipperSize) {
    this.flipperSize = flipperSize;
  }

  String count(PancakeRow row) {
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
    return HAPPY.equals(row.getSideAt(index))
        && findNextBlank(row, index) - index == flipperSize - 1
        && rightSideIsBlank(row, findNextBlank(row, index));
  }

  //-++
  private boolean isIndexOfRightSidedHappyGroup(PancakeRow row, int index) {
    return BLANK.equals(row.getSideAt(index))
        && findNextBlank(row, index) - index == flipperSize
        && leftSideIsBlank(row, index);
  }

}
