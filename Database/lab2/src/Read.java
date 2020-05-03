import java.io.File;
import java.io.RandomAccessFile;

public class Read {

  public static final int RECORD_LEN = 16;
  public static final int STR_LEN = 12;

  RandomAccessFile randomAccessFile;
  long filePosition;
  File file;

  public Read(String filePath) {
    try {
      this.file = new File(filePath);
      this.randomAccessFile = new RandomAccessFile(file, "rw");
      filePosition = 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 读取在文件自定偏移量的数据
  public RecordWithPosition readRecordAt(long position) {
    try {
      long oldPosition = filePosition;
      randomAccessFile.seek((long) position * RECORD_LEN);
      int index = randomAccessFile.readInt();
      byte[] bytes = new byte[STR_LEN];
      randomAccessFile.read(bytes);
      randomAccessFile.seek(filePosition);
      return new RecordWithPosition(index, bytes, oldPosition / RECORD_LEN);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  // 文件指针回到开始
  public void toStartPosition() {
    try {
      randomAccessFile.seek(0);
      filePosition = randomAccessFile.getFilePointer();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 读取下一条带有文件所在位置的记录,若没有记录返回null;
  public RecordWithPosition nextWithPosition() {
    try {
      long oldPosition = filePosition;
      int index = randomAccessFile.readInt();
      byte[] bytes = new byte[STR_LEN];
      randomAccessFile.read(bytes);
      filePosition = randomAccessFile.getFilePointer();
      return new RecordWithPosition(index, bytes, oldPosition / RECORD_LEN);
    } catch (java.io.EOFException e) {
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Record next() {
    try {
      long oldPosition = filePosition;
      int index = randomAccessFile.readInt();
      byte[] bytes = new byte[STR_LEN];
      randomAccessFile.read(bytes);
      filePosition = randomAccessFile.getFilePointer();
      return new Record(index, bytes);
    } catch (java.io.EOFException e) {
      System.out.println("读取结束");
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public boolean close(){
    try {
      randomAccessFile.close();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
