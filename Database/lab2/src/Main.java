public class Main {

  public static void main(String[] args) {
    int childrenNumber= 5;
    System.out.println(childrenNumber+"阶B树");
    Read read = new Read("data.bin");
    BTree bTree = new BTree(childrenNumber);
    Record record = null;
    while(true){
      record = read.next();
      if (record == null) {
        break;
      }
      bTree.add(record);
    }
    System.out.println("深度"+bTree.getDepth());
    long position = bTree.search(11);
    if (position != -1) {
      System.out.println(read.readRecordAt(position));
      System.out.println("所在文件的位置" + position);
    } else {
      System.out.println("搜索文件不存在");
    }

  }
}
