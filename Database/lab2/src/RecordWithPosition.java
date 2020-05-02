import java.util.Arrays;

public class RecordWithPosition {

  private int a;
  private byte[] b;
  private long position;

  public RecordWithPosition(int a, byte[] b, long position) {
    this.a = a;
    this.b = b;
    this.position = position;
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

  public long getPosition() {
    return position;
  }

  public void setPosition(long position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return "Record{" + a + ',' + new String(b) + '}';
  }
}
