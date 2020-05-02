import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

public class Generate {

  public static void main(String[] args) {
    int num = 1000000;
    int strLen = 12;
    int[] index = new int[num];
    for (int i = 0; i < num; i++) {
      index[i] = i*2;
    }
    shuffle(index);
    try {
      File outputFile = new File("data_1M.bin");
      OutputStream outputStream = new FileOutputStream(outputFile);
      for (int i = 0; i < num; i++) {
        outputStream.write(intToByteArray(index[i]));
        //outputStream.write(randomString(strLen));
        outputStream.write(addPrefix(index[i], strLen));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // byte转为大端序的int
  public static int byteArrayToInt(byte[] b) {
    return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
  }

  // int转为大端序的byte数组
  public static byte[] intToByteArray(int a) {
    return new byte[]{(byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
        (byte) ((a) & 0xFF)};
  }

  // 随机生成byte字符串数组
  public static byte[] randomString(int length) {
    byte[] result = new byte[length];
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      byte temp = (byte) ('A' + random.nextInt(26));
      if (random.nextBoolean()) {
        temp += 32;
      }
      result[i] = temp;
    }
    return result;
  }

  // 打乱数组
  public static void shuffle(int[] list) {
    Random random = new Random();
    for (int i = list.length - 1; i > 0; i--) {
      int ran = random.nextInt(i);
      int temp = list[i];
      list[i] = list[ran];
      list[ran] = temp;
    }
  }

  // 把1234 改成  ____1234
  public static byte[] addPrefix(int num, int strNum) {
    byte[] originList = String.valueOf(num).getBytes();
    int prefixLen = strNum - originList.length;
    byte[] newList = new byte[strNum];
    for (int i = 0; i < prefixLen; i++) {
      newList[i] = ' ';
    }
    for (int i = prefixLen; i < strNum; i++) {
      newList[i] = originList[i-prefixLen];
    }
    return newList;
  }
}