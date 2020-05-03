import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort {
  public static final int RECORD_LEN = 16;
  public static final int STR_LEN = 12;
  public static final String FILE_NAME = "data.bin";
  public static final String TEMP_FILE_PATH = "data_files/temp/" + FILE_NAME;

  public static void sort(Read read, Write write, long memorySize) {
    long blockSize = memorySize / RECORD_LEN; // 一次最多读入
    int count = 0;
    Write tempFile = new Write(TEMP_FILE_PATH);
    Record record = null;
    List<Record> blockInMemory = new ArrayList<>();
    while (true) {
      record = read.next();
      if (record == null) {
        Collections.sort(blockInMemory);
        tempFile.write(blockInMemory);
        break;
      }
      blockInMemory.add(record);
      count++;
      if (count >= blockSize) {
        count=0;
        Collections.sort(blockInMemory);
        tempFile.write(blockInMemory);
        blockInMemory = new ArrayList<>();
      }
    }
    tempFile.close();
  }
}
