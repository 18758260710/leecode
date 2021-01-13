package problems;

import problems.P94_BinaryTreeInorderTraversal.TreeNode;

/**
 * @author wengtao
 * @date 2020/3/25
 **/
public class P297_SerializeandDeserializeBinaryTree {
    //内存超出
    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        if (root == null) return "";
//        List<TreeNode> list = new ArrayList<>();
//        list.add(root);
//        int index = 0;
//        while (index < list.size()) {
//            if (list.get(index) != null) {
//                while (list.size()<2 * index + 1){
//                    list.add(null);
//                }
//                list.add(2 * index + 1, list.get(index).left);
//                list.add(2 * index + 2, list.get(index).right);
//            }
//            index++;
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        for (TreeNode treeNode : list) {
//            if (treeNode == null) {
//                stringBuilder.append("null");
//            } else{
//                stringBuilder.append(treeNode.val);
//            }
//            stringBuilder.append(',');
//        }
//        return stringBuilder.substring(0, stringBuilder.length() - 1).toString();
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        if (data.isEmpty()) return null;
//        String[] datas = data.split(",");
//        List<TreeNode> list = new ArrayList<>();
//        for (String dataa:datas){
//            if (dataa.equals("null"))list.add(null);
//            else list.add(new TreeNode(Integer.parseInt(dataa)));
//        }
//        for (int i=0;i<list.size()/2+2;i++){
//            TreeNode treeNode = list.get(i);
//            if (treeNode!=null){
//                treeNode.left = list.get(2*i+1);
//                treeNode.right = list.get(2*i+2);
//            }
//        }
//        return list.get(0);
//    }

    //用链表slow
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        return rserialize(root, "");
//    }
//
//    //preorder
//    private String rserialize(TreeNode root, String str) {
//        if (root == null) {
//            str += "null,";
//        } else {
//            str += root.val + ",";
//            str = rserialize(root.left, str);
//            str = rserialize(root.right, str);
//        }
//        return str;
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        String[] dataArray = data.split(",");
//        List<String> dataList = new LinkedList<>(Arrays.asList(dataArray));
//        return rdeserialize(dataList);
//    }
//
//    private TreeNode rdeserialize(List<String> dataList) {
//        if (dataList.get(0).equals("null")) {
//            dataList.remove(0);
//            return null;
//        }
//        TreeNode root = new TreeNode(Integer.parseInt(dataList.get(0)));
//        dataList.remove(0);
//        root.left = rdeserialize(dataList);
//        root.right = rdeserialize(dataList);
//
//        return root;
//    }

    //数组代替链表 fast 但index需要初始化
    private int index = -1;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = hepler(root, new StringBuilder());
        return sb.toString();
    }
    public StringBuilder hepler(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append("null,");
            return sb;
        }
        sb.append(root.val);
        sb.append(",");
        sb = hepler(root.left, sb);
        sb = hepler(root.right, sb);
        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] roots = data.split(",");
        TreeNode root = hepler1(roots);
        return root;
    }
    public TreeNode hepler1(String[] roots){
        index++;
        if(index < roots.length){
            if(roots[index].equals("null"))
                return null;
            TreeNode root = new TreeNode(Integer.parseInt(roots[index]));
            root.left = hepler1(roots);
            root.right = hepler1(roots);
            return root;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
//        root.left.left = new TreeNode(2);
//        root.left.right = new TreeNode(0);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(2);
        String a = new P297_SerializeandDeserializeBinaryTree().serialize(root);
        System.out.println(a);
        TreeNode aa = new P297_SerializeandDeserializeBinaryTree().deserialize(a);
        System.out.println(aa);

    }
}
