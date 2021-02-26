package org.json;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Iterator;

public class JSONNode implements TreeNode, Iterable<JSONNode> {

    @Override
    public Iterator<JSONNode> iterator() {
        return null;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public TreeNode getParent() {
        return null;
    }

    @Override
    public int getIndex(TreeNode node) {
        return 0;
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return null;
    }
}
