import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

public class BTree {

  private BTreeNode root;
  private int maxChildrenNum;
  private int minChildrenNum;
  private int maxIndexNum;
  private int minIndexNum;

  public BTree(int maxChildrenNum) {
    this.root = new BTreeNode();
    this.maxChildrenNum = maxChildrenNum;
    if (maxChildrenNum % 2 == 0) {
      this.minChildrenNum = maxChildrenNum / 2;
    } else {
      this.minChildrenNum = maxChildrenNum / 2 + 1;
    }
    this.minIndexNum = minChildrenNum - 1;
    this.maxIndexNum = minChildrenNum - 1;
  }

  public void add(Index index) {
    Stack<BTreeNode> stack = new Stack<>(); // 记录从根节点到叶子节点的路径
    stack.push(root);
    while (!stack.peek().isLeaf()) {
      stack.push(stack.peek().getInsertingChild(index));
    }
    stack.peek().addIndex(index);
    if (stack.peek().getIndexes().size() > maxIndexNum) {
      // TODO 分裂节点
    }
  }
}
