import java.util.Stack;

public class BTree {

  private BTreeNode root;
  private int maxChildrenNum;
  private int minChildrenNum;
  private int maxIndexNum;
  private int minIndexNum;

  // 构建B树
  public BTree(int maxChildrenNum) {
    this.root = new BTreeNode();
    this.maxChildrenNum = maxChildrenNum;
    if (maxChildrenNum % 2 == 0) {
      this.minChildrenNum = maxChildrenNum / 2;
    } else {
      this.minChildrenNum = maxChildrenNum / 2 + 1;
    }
    this.minIndexNum = minChildrenNum - 1;
    this.maxIndexNum = maxChildrenNum - 1;
  }

  // B树深度
  public int getDepth() {
    int count = 1;
    BTreeNode pos = root;
    while (pos.getChildren().size() != 0) {
      count++;
      pos = pos.getChildren().get(0);
    }
    return count;
  }

  // 添加
  public void add(Record record) {
    Index index = new Index(record.getA(), record.getPosition());
    Stack<BTreeNode> stack = new Stack<>(); // 记录从根节点到叶子节点的路径
    stack.push(root);
    while (!stack.peek().isLeaf()) {
      stack.push(stack.peek().getChild(index.getIndexValue()));
    }
    stack.peek().addIndex(index);
    while (!stack.isEmpty()&&stack.peek().getIndexes().size() > maxIndexNum) {
      // 递归分裂要分裂的节点
      BTreeNode splitNode = split(stack.peek());
      Index newIndex = splitNode.getIndexes().get(0);
      BTreeNode leftTree = splitNode.getChildren().get(0);
      BTreeNode rightTree = splitNode.getChildren().get(1);
      stack.pop();
      BTreeNode father;
      if (stack.isEmpty()) {
        // 栈是空的 表示根节点要分裂
        father = new BTreeNode();
        this.root = father;
        this.root.addIndex(newIndex);
        this.root.getChildren().add(leftTree);
        this.root.getChildren().add(rightTree);
      } else {
        father = stack.peek();
        int fatherPosition = father.addIndex(newIndex);
        father.getChildren().remove(fatherPosition);
        father.getChildren().add(fatherPosition, rightTree);
        father.getChildren().add(fatherPosition, leftTree);
      }
    }
  }

  // 分裂节点
  public BTreeNode split(BTreeNode splitNode) {
    // 分裂节点
    int position = maxIndexNum / 2;
    BTreeNode leftTree = new BTreeNode();
    BTreeNode rightTree = new BTreeNode();
    Index newIndex = splitNode.getIndexes().get(position);
    // 分裂索引
    for (int i = 0; i < position; i++) {
      leftTree.addIndex(splitNode.getIndexes().get(i));
    }
    for (int i = position + 1; i < splitNode.getIndexes().size(); i++) {
      rightTree.addIndex(splitNode.getIndexes().get(i));
    }
    // 分裂子树
    if (!splitNode.isLeaf()) {
      for (int i = 0; i <= position; i++) {
        leftTree.getChildren().add(splitNode.getChildren().get(i));
      }
      for (int i = position + 1; i < splitNode.getChildren().size(); i++) {
        rightTree.getChildren().add(splitNode.getChildren().get(i));
      }
    }
    BTreeNode result = new BTreeNode();
    result.addIndex(newIndex);
    result.getChildren().add(leftTree);
    result.getChildren().add(rightTree);
    return result;
  }

  // 搜索 返回在文件所在的位置
  long search(int value) {
    BTreeNode pos = this.root;
    while (!pos.isLeaf()) {
      pos = pos.getChild(value);
    }

    for (int i = 0; i < pos.getIndexes().size(); i++) {
      if (pos.getIndexes().get(i).getIndexValue() == value) {
        return pos.getIndexes().get(i).getPosition();
      }
    }
    return -1;
  }
}
