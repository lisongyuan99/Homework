public class Main {

  public static final int CHILDREN_NUM = 5;
  public static final String FILE_NAME = "data.bin";
  public static final String INPUT_FILE_PATH = "data_files/input/" + FILE_NAME;
  public static final String TEMP_FILE_PATH = "data_files/temp/" + FILE_NAME;
  public static final String OUTPUT_FILE_PATH = "data_files/output/" + FILE_NAME;

  public static void main(String[] args) {
    sortTest();
  }

  public static void bTreeTest() {
    Read read = new Read(INPUT_FILE_PATH);
    BTree bTree = generateBTree(read, CHILDREN_NUM);
    BTree bTree2 = generateBTree(read, CHILDREN_NUM);
    bTree.delete(15);
    System.out.println(bTree);
    System.out.println(bTree2);
  }

  public static void sortTest(){
    Read in = new Read(INPUT_FILE_PATH);
    Write out = new Write(OUTPUT_FILE_PATH);
    Sort.sort(in, out, 64);
  }

  public static BTree generateBTree(Read read, int childrenNumber) {
    System.out.println(childrenNumber + "阶B树");

    BTree bTree = new BTree(childrenNumber);
    RecordWithPosition recordWithPosition = null;
    while (true) {
      recordWithPosition = read.nextWithPosition();
      if (recordWithPosition == null) {
        break;
      }
      bTree.add(recordWithPosition);
    }
    read.toStartPosition();
    System.out.println("深度" + bTree.getDepth());
    return bTree;
  }

  public static void search(Read read, BTree bTree, int value) {
    Index position = bTree.search(value);
    if (position != null) {
      System.out.println(read.readRecordAt(position.getPosition()));
      System.out.println("所在文件的位置" + position.getPosition());
    } else {
      System.out.println("记录不存在");
    }
  }
}
