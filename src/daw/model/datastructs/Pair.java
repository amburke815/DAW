package daw.model.datastructs;

import daw.Utils;

public class Pair<X,Y> {
  public X first;
  public Y second;

  public Pair(X first, Y second) {
    this.first = Utils.notNull(first);
    this.second = Utils.notNull(second);
  }
}
