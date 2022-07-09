package daw;

public class Utils {

  public static int intBetween(int lowerBoundIncl, int toCheck, int upperBoundIncl)
      throws IllegalArgumentException {
    if (toCheck < lowerBoundIncl || toCheck > upperBoundIncl) {
      throw new IllegalArgumentException("Argument " + toCheck + " not in bounds of [" + "," +
          upperBoundIncl + "]");
    }
    return toCheck;
  }

  public static <O> O notNull(O toCheck) {
    if (toCheck == null)
      throw new IllegalArgumentException("Argument " + toCheck.toString() +" must not be null");
    return toCheck;
  }

}
