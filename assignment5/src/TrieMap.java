//Note: All of your TrieMapInterface method implementations must function recursively
//I have left the method signatures from my own solution, which may be useful hints in how to approach the problem
//You are free to change/remove/etc. any of the methods, as long as your class still supports the TrieMapInterface
import java.util.ArrayList;
import java.util.HashMap;

public class TrieMap implements TrieMapInterface {
  TrieMapNode root;

  public TrieMap() {
    root = new TrieMapNode();
  }

  public void put(String key, String value) {
    put(root, key, value);
  }

  public void put(TrieMapNode current, String curKey, String value) {
    if (curKey.length() == 0) {
      current.setValue(value);
      return;
    } else if (!(current.getChildren().containsKey(curKey.charAt(0)))) {
      TrieMapNode newNode = new TrieMapNode();
      current.getChildren().put(curKey.charAt(0), newNode);
    }
    put(current.getChildren().get(curKey.charAt(0)), curKey.substring(1), value);
  }

  public String get(String key) {
    return get(root, key);
  }

  public String get(TrieMapNode current, String curKey) {
    if (curKey.length() == 0) {
      return current.getValue();
    } else if (!(current.getChildren().containsKey(curKey.charAt(0)))) {
      return null;
    }
    return get(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
  }

  @Override
  public boolean containsKey(String key) {
    return containsKey(root, key);
  }

  public boolean containsKey(TrieMapNode current, String curKey) {
    if (curKey.length() == 0) {
      if (current.getValue() != null) {
        return true;
      } else {
        return false;
      }
    } else if (!(current.getChildren().containsKey((curKey.charAt(0))))) {
      return false;
    }
    return containsKey((current.getChildren().get(curKey.charAt(0))), curKey.substring(1));
  }

  public ArrayList<String> getKeysForPrefix(String prefix) {
    TrieMapNode node = findNode(root, prefix);
    if (node != null) {
      return getSubtreeKeys(node);
    }
    else {
      return new ArrayList<String>();
    }
  }

  public TrieMapNode findNode(TrieMapNode current, String curKey) {
    if (curKey.length() == 0) {
      return current;
    }
    else if (!(current.getChildren().containsKey(curKey.charAt(0)))) {
      return null;
    }
    return findNode(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
  }

  public ArrayList<String> getSubtreeKeys(TrieMapNode current) {
    ArrayList<String> keys = new ArrayList<String>();
    for (TrieMapNode node : current.getChildren().values()) {
      keys.addAll(getSubtreeKeys(node));
    }
    if (current.getValue() != null) {
      keys.add(current.getValue());
    }
    return keys;
  }

  public void print() {
    print(root);
  }

  public void print(TrieMapNode current) {
    for (TrieMapNode node : current.getChildren().values()) {
      print(node);
    }
    if (current.getValue() != null) {
      System.out.print(current);
    }
  }

  public static void main(String[] args) {
    //You can add some code in here to test out your TrieMap initially
    //The TrieMapTester includes a more detailed test
  }
}