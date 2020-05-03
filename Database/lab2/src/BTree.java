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
  public void add(RecordWithPosition recordWithPosition) {
    Index index = new Index(recordWithPosition.getA(), recordWithPosition.getPosition());
    Stack<BTreeNode> stack = new Stack<>(); // 记录从根节点到叶子节点的路径
    stack.push(root);
    while (!stack.peek().isLeaf()) {
      stack.push(stack.peek().getChild(index.getIndexValue()));
    }
    stack.peek().addIndex(index);
    while (!stack.isEmpty() && stack.peek().getIndexes().size() > maxIndexNum) {
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
  public Index search(int value) {
    BTreeNode pos = this.root;
    while (!pos.isLeaf()) {
      pos = pos.getChild(value);
      for (int i = 0; i < pos.getIndexes().size(); i++) {
        if (pos.getIndexes().get(i).getIndexValue() == value) {
          return pos.getIndexes().get(i);
        }
      }
    }
    for (int i = 0; i < pos.getIndexes().size(); i++) {
      if (pos.getIndexes().get(i).getIndexValue() == value) {
        return pos.getIndexes().get(i);
      }
    }
    return null;
  }

  // 删除
  public boolean delete(int indexValue) {
    int deletePositionIndex = 0; //子树所在位置
    BTreeNode deletePosition = null; //节点所在子树
    Stack<BTreeNode> stack = new Stack<>(); // 记录从根节点到叶子节点的路径
    // region 找到要删除的节点的位置
    stack.push(root);
    boolean found = false;
    do {
      for (int i = 0; i < stack.peek().getIndexes().size(); i++) {
        if (stack.peek().getIndexes().get(i).getIndexValue() == indexValue) {
          found = true;
          deletePositionIndex = i;
        }
      }
      if (!found) {
        stack.push(stack.peek().getChild(indexValue));
      }
    } while (!stack.peek().isLeaf() && !found);
    for (int i = 0; i < stack.peek().getIndexes().size(); i++) {
      if (stack.peek().getIndexes().get(i).getIndexValue() == indexValue) {
        found = true;
        deletePositionIndex = i;
        break;
      }
    }
    if (!found) {
      return false;
    }
    deletePosition = stack.peek();
    // endregion
    // region 删除节点
    if (deletePosition.isLeaf()) {
      // 若是叶子节点 删除叶子节点
      deletePosition.getIndexes().remove(deletePositionIndex);
    } else {
      // 否则找到后继节点并添加路径到栈, 后继叶子节点到该节点处, 删除原来的节点
      stack.push(stack.peek().getChild(indexValue));
      while (!stack.peek().isLeaf()) {
        stack.push(stack.peek().getChildren().get(0));
      }
      deletePosition.getIndexes().set(deletePositionIndex, stack.peek().getIndexes().get(0));
      stack.peek().getIndexes().remove(0);
    }
    // endregion
    // region 调整数量少的节点
    while (stack.peek() != root && (stack.peek().getIndexes().size() < minIndexNum)){
      BTreeNode leftBrother = null;
      BTreeNode rightBrother = null;
      BTreeNode self = stack.pop();;
      BTreeNode father = null;
      int childNum = -1; //是父节点的第几个子树
      // 找父节点, 左右节点
      father = stack.peek();
      for (int i = 0; i < father.getChildren().size(); i++) {
        if (father.getChildren().get(i) == self) {
          self = father.getChildren().get(i);
          if (i - 1 >= 0) {
            leftBrother = father.getChildren().get(i - 1);
          }
          if (i + 1 < father.getChildren().size()) {
            rightBrother = father.getChildren().get(i + 1);
          }
          childNum = i;
          break;
        }
      }

      // 有左右节点 且去掉一个还是有足够多 则旋转
      if (leftBrother != null && leftBrother.getIndexes().size() - 1 >= minIndexNum) {
        self.getIndexes().add(0, father.getIndexes().get(childNum - 1));
        father.getIndexes()
            .set(childNum - 1, leftBrother.getIndexes().get(leftBrother.getIndexes().size() - 1));
        leftBrother.getIndexes().remove(leftBrother.getIndexes().size() - 1);
        if (!self.isLeaf()) {
          self.getChildren()
              .add(0, leftBrother.getChildren().get(leftBrother.getChildren().size() - 1));
          leftBrother.getChildren().remove(leftBrother.getChildren().size() - 1);
        }
      } else if (rightBrother != null && rightBrother.getIndexes().size() - 1 >= minIndexNum) {
        self.getIndexes().add(father.getIndexes().get(childNum));
        father.getIndexes().set(childNum, rightBrother.getIndexes().get(0));
        rightBrother.getIndexes().remove(0);
        if (!self.isLeaf()) {
          self.getChildren().add(rightBrother.getChildren().get(0));
          rightBrother.getChildren().remove(0);
        }
      } else {
        // 和上方的索引合并成节点
        BTreeNode newNode = new BTreeNode();
        if (leftBrother != null) {
          // 添加索引
          for (int i = 0; i < leftBrother.getIndexes().size(); i++) {
            newNode.getIndexes().add(leftBrother.getIndexes().get(i));
          }
          newNode.getIndexes().add(father.getIndexes().get(childNum-1));
          for (int i = 0; i < self.getIndexes().size(); i++) {
            newNode.getIndexes().add(self.getIndexes().get(i));
          }
          // 添加子节点
          if (!leftBrother.isLeaf() && rightBrother.isLeaf()) {
            for (int i = 0; i < leftBrother.getChildren().size(); i++) {
              newNode.getChildren().add(leftBrother.getChildren().get(i));
            }
            for (int i = 0; i < self.getChildren().size(); i++) {
              newNode.getChildren().add(self.getChildren().get(i));
            }
          }
          father.getIndexes().remove(childNum - 1);
          father.getChildren().remove(childNum - 1);
          father.getChildren().remove((childNum - 1));
          father.getChildren().add(childNum - 1, newNode);
        } else if (rightBrother != null) {
          // 添加索引
          for (int i = 0; i < self.getIndexes().size(); i++) {
            newNode.getIndexes().add(self.getIndexes().get(i));
          }
          newNode.getIndexes().add(father.getIndexes().get(childNum));
          for (int i = 0; i < rightBrother.getIndexes().size(); i++) {
            newNode.getIndexes().add(rightBrother.getIndexes().get(i));
          }
          // 添加子节点
          if (!self.isLeaf() && !rightBrother.isLeaf()) {
            for (int i = 0; i < self.getChildren().size(); i++) {
              newNode.getChildren().add(self.getChildren().get(i));
            }
            for (int i = 0; i < rightBrother.getChildren().size(); i++) {
              newNode.getChildren().add(rightBrother.getChildren().get(i));
            }
          }
          father.getIndexes().remove(childNum);
          father.getChildren().remove(childNum);
          father.getChildren().remove((childNum));
          father.getChildren().add(childNum, newNode);
        }
      }
    }


    // endregion
    return true;
  }

}
