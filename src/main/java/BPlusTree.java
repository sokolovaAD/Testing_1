import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BPlusTree {

    private int maxDegree;

    private Node root;

    public BPlusTree(int maxDegree) {
        if (maxDegree <= 2 || maxDegree > 6)
            throw new IllegalArgumentException("Illegal Max. Degree: "
                    + maxDegree + " Please choose between 2 and 6");
        this.maxDegree = maxDegree;
        root = new LeafNode();
    }

    public int search(int key) {
        return root.getValue(key);
    }

    public List<Integer> searchRange(int key1, int key2) {
        return root.getRange(key1, key2);
    }

    public void insert(int key) {
        root.insertValue(key);
    }

    public void delete(int key) {
        root.deleteValue(key);
    }

    public String toString() {
        Queue<List<Node>> queue = new LinkedList<List<Node>>();
        queue.add(Arrays.asList(root));
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Queue<List<Node>> nextQueue = new LinkedList<List<Node>>();
            while (!queue.isEmpty()) {
                List<Node> nodes = queue.remove();
                sb.append('{');
                Iterator<Node> it = nodes.iterator();
                while (it.hasNext()) {
                    Node node = it.next();
                    sb.append(node.toString());
                    if (it.hasNext())
                        sb.append(", ");
                    if (node instanceof BPlusTree.InternalNode)
                        nextQueue.add(((InternalNode) node).children);
                }
                sb.append('}');
                if (!queue.isEmpty())
                    sb.append(", ");
                else if (!nextQueue.isEmpty())
                sb.append(" -> ");
            }
            queue = nextQueue;
        }

        return sb.toString();
    }

    private abstract class Node {
        List<Integer> keys;

        int keyNumber() {
            return keys.size();
        }

        abstract int getValue(int key);

        abstract void deleteValue(int key);

        abstract void insertValue(int key);

        abstract int getFirstLeafKey();

        abstract List<Integer> getRange(int key1,  int key2);

        abstract void merge(Node sibling);

        abstract Node split();

        abstract boolean isOverflow();

        abstract boolean isUnderflow();

        public String toString() {
            return keys.toString();
        }
    }

    private class InternalNode extends Node {
        List<Node> children;

        InternalNode() {
            this.keys = new ArrayList<Integer>();
            this.children = new ArrayList<Node>();
        }

        @Override
        int getValue(int key) {
            return getChild(key).getValue(key);
        }

        @Override
        void deleteValue(int key) {
            Node child = getChild(key);
            child.deleteValue(key);
            if (child.isUnderflow()) {
                Node childLeftSibling = getChildLeftSibling(key);
                Node childRightSibling = getChildRightSibling(key);
                Node left = childLeftSibling != null ? childLeftSibling : child;
                Node right = childLeftSibling != null ? child
                        : childRightSibling;
                left.merge(right);
                deleteChild(right.getFirstLeafKey());
                if (left.isOverflow()) {
                    Node sibling = left.split();
                    insertChild(sibling.getFirstLeafKey(), sibling);
                }
                if (root.keyNumber() == 0)
                    root = left;
            }
        }

        @Override
        void insertValue(int key) {

            Node child = getChild(key);
            child.insertValue(key);
            if (child.isOverflow()) {
                Node sibling = child.split();
                insertChild(sibling.getFirstLeafKey(), sibling);
            }
            if (root.isOverflow()) {
                Node sibling = split();
                InternalNode newRoot = new InternalNode();
                newRoot.keys.add(sibling.getFirstLeafKey());
                newRoot.children.add(this);
                newRoot.children.add(sibling);
                root = newRoot;
            }
        }

        @Override
        int getFirstLeafKey() {
            return children.get(0).getFirstLeafKey();
        }

        @Override
        List<Integer> getRange(int key1, int key2) {
            return getChild(key1).getRange(key1, key2);
        }

        @Override
        void merge(Node sibling) {
            InternalNode node = (InternalNode) sibling;
            keys.add(node.getFirstLeafKey());
            keys.addAll(node.keys);
            children.addAll(node.children);

        }

        @Override
        Node split() {
            int from = keyNumber() / 2 + 1, to = keyNumber();
            InternalNode sibling = new InternalNode();
            sibling.keys.addAll(keys.subList(from, to));
            sibling.children.addAll(children.subList(from, to + 1));

            keys.subList(from - 1, to).clear();
            children.subList(from, to + 1).clear();

            return sibling;
        }

        @Override
        boolean isOverflow() {
            return children.size() > maxDegree;
        }

        @Override
        boolean isUnderflow() {
            return children.size() < (maxDegree + 1) / 2;
        }

        Node getChild(int key) {
            int loc = Collections.binarySearch(keys, key);
            int childIndex = loc >= 0 ? loc + 1 : -loc - 1;
            return children.get(childIndex);
        }

        void deleteChild(int key) {
            int loc = Collections.binarySearch(keys, key);
            if (loc >= 0) {
                keys.remove(loc);
                children.remove(loc + 1);
            }
        }

        void insertChild(int key, Node child) {
            int loc = Collections.binarySearch(keys, key);
            int childIndex = loc >= 0 ? loc + 1 : -loc - 1;
            if (loc >= 0) {
                children.set(childIndex, child);
            } else {
                keys.add(childIndex, key);
                children.add(childIndex + 1, child);
            }
        }

        Node getChildLeftSibling(int key) {
            int loc = Collections.binarySearch(keys, key);
            int childIndex = loc >= 0 ? loc + 1 : -loc - 1;
            if (childIndex > 0)
                return children.get(childIndex - 1);

            return null;
        }

        Node getChildRightSibling(int key) {
            int loc = Collections.binarySearch(keys, key);
            int childIndex = loc >= 0 ? loc + 1 : -loc - 1;
            if (childIndex < keyNumber())
                return children.get(childIndex + 1);

            return null;
        }
    }

    private class LeafNode extends Node {
        List<Integer> values;
        LeafNode next;

        LeafNode() {
            keys = new ArrayList<Integer>();
            values = new ArrayList<Integer>();
        }

        @Override
        int getValue(int key) {
            int loc = Collections.binarySearch(keys, key);
            return loc >= 0 ? values.get(loc) : Integer.parseInt(null);
        }

        @Override
        void deleteValue(int key) {
            int loc = Collections.binarySearch(keys, key);
            if (loc >= 0) {
                keys.remove(loc);
                values.remove(loc);
            }
        }

        @Override
        void insertValue(int key) {
            int value = key;
            int loc = Collections.binarySearch(keys, key);
            int valueIndex = loc >= 0 ? loc : -loc - 1;
            if (loc >= 0) {
                values.set(valueIndex, value);
            } else {
                keys.add(valueIndex, key);
                values.add(valueIndex, value);
            }
            if (root.isOverflow()) {
                Node sibling = split();
                InternalNode newRoot = new InternalNode();
                newRoot.keys.add(sibling.getFirstLeafKey());
                newRoot.children.add(this);
                newRoot.children.add(sibling);
                root = newRoot;
            }
        }

        @Override
        int getFirstLeafKey() {
            return keys.get(0);
        }

        @Override
        List<Integer> getRange(int key1, int key2) {
            List<Integer> result = new LinkedList<Integer>();
            LeafNode node = this;
            while (node != null) {
                Iterator<Integer> kIt = node.keys.iterator();
                Iterator<Integer> vIt = node.values.iterator();
                while (kIt.hasNext()) {
                    int key = kIt.next();
                    int value = vIt.next();
                    if (key >= key1
                            && key <= key2)
                        result.add(value);
                    else if (key >= key2)
                        return result;
                }
                node = node.next;
            }
            return result;
        }

        @Override
        void merge(Node sibling) {
            @SuppressWarnings("unchecked")
            LeafNode node = (LeafNode) sibling;
            keys.addAll(node.keys);
            values.addAll(node.values);
            next = node.next;
        }

        @Override
        Node split() {
            LeafNode sibling = new LeafNode();
            int from = (keyNumber() + 1) / 2, to = keyNumber();
            sibling.keys.addAll(keys.subList(from, to));
            sibling.values.addAll(values.subList(from, to));

            keys.subList(from, to).clear();
            values.subList(from, to).clear();

            sibling.next = next;
            next = sibling;
            return sibling;
        }

        @Override
        boolean isOverflow() {
            return values.size() > maxDegree - 1;
        }

        @Override
        boolean isUnderflow() {
            return values.size() < maxDegree / 2;
        }
    }
}