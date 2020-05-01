import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Random;

public class Read {

  public static final int RECORD_LEN = 16;
  public static final int STR_LEN = 12;
  public static Record readRecordAt(String filePath, int position) {
    try {
      File file = new File(filePath);
      RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
      randomAccessFile.seek((long)position*RECORD_LEN);
      int index = randomAccessFile.readInt();
      byte[] bytes = new byte[STR_LEN];
      randomAccessFile.read(bytes);
      return new Record(index, bytes);
    } catch (Exception e) {
      return null;
    }
  }
}
