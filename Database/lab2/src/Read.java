import java.io.File;
import java.io.RandomAccessFile;

public class Read {

  public static final int RECORD_LEN = 16;
  public static final int STR_LEN = 12;
  RandomAccessFile randomAccessFile;
  long filePosition;

  public Read(String filePath) {
    try {
      File file = new File(filePath);
      this.randomAccessFile = new RandomAccessFile(file, "r");
      filePosition = 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 读取在文件自定偏移量的数据
  public Record readRecordAt(long position) {
    try {
      long oldPosition = filePosition;
      randomAccessFile.seek((long) position * RECORD_LEN);
      int index = randomAccessFile.readInt();
      byte[] bytes = new byte[STR_LEN];
      randomAccessFile.read(bytes);
      randomAccessFile.seek(filePosition);
      return new Record(index, bytes, oldPosition/RECORD_LEN);
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

  // 读取下一条记录,若没有记录返回null;
  public Record next() {
    try {
      long oldPosition = filePosition;
      int index = randomAccessFile.readInt();
      byte[] bytes = new byte[STR_LEN];
      randomAccessFile.read(bytes);
      filePosition = randomAccessFile.getFilePointer();
      return new Record(index, bytes, oldPosition/RECORD_LEN);
    } catch (java.io.EOFException e){
      System.out.println("读取结束");
      return  null;
    }  catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
