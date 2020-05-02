import java.util.ArrayList;
import java.util.List;

public class BTreeNode {

  private List<Index> indexes; //每个节点的数据
  private List<BTreeNode> children;//每个节点的子节点

  public BTreeNode() {
    indexes = new ArrayList<>();
    children = new ArrayList<>();
  }

  public List<Index> getIndexes() {
    return indexes;
  }

  public List<BTreeNode> getChildren() {
    return children;
  }

  // 在叶节点上按照索引的值大小添加索引
  public int addIndex(Index index) {
    for (int i = 0; i < indexes.size(); i++) {
      if (indexes.get(i).getIndexValue() > index.getIndexValue()) {
        indexes.add(i, index);
        return i;
      }
    }
    indexes.add(index);
    return indexes.size()-1;// 这里已经添加过了
  }

  // 判断是否是叶子节点
  public boolean isLeaf() {
    return this.children.size() == 0;
  }

  // 给定一个索引值, 指出要插入的子树
  public BTreeNode getChild(int value) {
    // 如果是叶节点 返回自己
    if (this.getChildren().size() == 0) {
      return this;
    }
    // 否则返回要添加的子树
    for (int i = 0; i < indexes.size(); i++) {
      if (indexes.get(i).getIndexValue() > value) {
        return this.getChildren().get(i);
      }
    }
    return this.getChildren().get(this.getChildren().size()-1);
  }

  @Override
  public String toString() {
    return this.getIndexes().toString();
  }
}
