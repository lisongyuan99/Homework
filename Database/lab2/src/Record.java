public class Record implements Comparable<Record>{

  private int a;
  private byte[] b;

  public Record(int a, byte[] b) {
    this.a = a;
    this.b = b;
  }

  public int getA() {
    return a;
  }

  public void setA(int a) {
    this.a = a;
  }

  public byte[] getB() {
    return b;
  }

  public void setB(byte[] b) {
    this.b = b;
  }

  @Override
  public String toString() {
    return "Record{" + a + ',' + new String(b) + '}';
  }

  @Override
  public int compareTo(Record o) {
    return this.a - o.getA();
  }
}
