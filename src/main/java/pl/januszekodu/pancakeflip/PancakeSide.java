package pl.januszekodu.pancakeflip;

public enum PancakeSide {
  HAPPY, BLANK;

  public static PancakeSide revert(PancakeSide side) {
    return HAPPY.equals(side) ? BLANK : HAPPY;
  }
}
