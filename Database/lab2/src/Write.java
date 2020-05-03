import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

public class Write {
  public static final int RECORD_LEN = 16;
  public static final int STR_LEN = 12;
  RandomAccessFile randomAccessFile;
  File file;
  long filePosition;

  public Write(String filePath) {
    try {
      this.file = new File(filePath);
      if (file.exists()) {
        if(!file.delete()){
          throw new Exception();
        }
      }

      if(!file.createNewFile()){
        throw new Exception();
      }
      this.randomAccessFile = new RandomAccessFile(file, "rw");
      filePosition = 0;
    } catch (Exception e) {
      e.printStackTrace();
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

  public boolean write(Record record) {
    try {
      randomAccessFile.writeInt(record.getA());
      randomAccessFile.write(record.getB());
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean write(List<Record> records) {
    for (Record record : records) {
      if (!write(record)) {
        return false;
      }
    }
    return true;
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
