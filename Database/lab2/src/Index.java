public class Index implements Comparable<Index> {

  private int indexValue;
  private long position;

  public int getIndexValue() {
    return indexValue;
  }

  public void setIndexValue(int indexValue) {
    this.indexValue = indexValue;
  }

  public long getPosition() {
    return position;
  }

  public void setPosition(long position) {
    this.position = position;
  }

  public Index(int indexValue, long position) {
    this.indexValue = indexValue;
    this.position = position;
  }

  @Override
  public int compareTo(Index o) {
    return this.indexValue - o.indexValue;
  }

  @Override
  public String toString() {
    return String.valueOf(indexValue);
  }
}
