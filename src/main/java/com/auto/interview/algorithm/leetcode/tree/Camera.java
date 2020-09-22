package com.auto.interview.algorithm.leetcode.tree;

import com.auto.interview.algorithm.leetcode.base.TreeNode;
import com.auto.interview.algorithm.leetcode.utils.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jihai
 * @date : 2020/9/22
 * @description :
 */
public class Camera {


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(0);
        treeNode.left = new TreeNode(0);
        treeNode.left.left = new TreeNode(0);
        treeNode.left.left.left = new TreeNode(0);

        treeNode.left.left.left.right = new TreeNode(0);

        Camera camera = new Camera();
        Assert.assertTrue(camera.minCameraCover(treeNode) == 2);


        TreeNode t2 = new TreeNode(0);
        t2.left = new TreeNode(0);
        t2.right = new TreeNode(0);
        t2.left.left = new TreeNode(0);
        t2.left.right = new TreeNode(0);
        Assert.assertTrue(camera.minCameraCover(t2) == 2);


        TreeNode t3 = new TreeNode(0);
        t3.left = new TreeNode(0);
        t3.right = new TreeNode(0);
        t3.left.left = new TreeNode(0);
        t3.left.right = new TreeNode(0);
        t3.right.right = new TreeNode(0);
        Assert.assertTrue(camera.minCameraCover(t3) == 2);


        TreeNode t4 = new TreeNode(0);
        t4.right = new TreeNode(0);
        t4.right.right = new TreeNode(0);
        t4.right.right.right = new TreeNode(0);
        t4.right.right.right.right = new TreeNode(0);
        t4.right.right.right.right.left = new TreeNode(0);
        t4.right.right.right.right.right = new TreeNode(0);
        t4.right.right.right.right.right.left = new TreeNode(0);
        t4.right.right.right.right.right.right = new TreeNode(0);
        Assert.assertTrue(camera.minCameraCover(t4) == 3);
    }

    /**
     *      0
     *     0  0
     *    0 0
     *
     *      0
     *     0  0
     *    0  0  0
     *
     * @param root
     * @return
     */

    Map<TreeNode, Integer> map = new HashMap<>();
    public int minCameraCover(TreeNode root) {
        if (map.get(root) != null) return map.get(root);
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int a = Integer.MAX_VALUE,b = Integer.MAX_VALUE, c = Integer.MAX_VALUE;

        if (root.left != null || root.right != null) a = 1;

        // case 1 root is camera
        if (root.left != null) {
            a += minCameraCover(root.left.left) + minCameraCover(root.left.right);
        }
        if (root.right != null) {
            a += minCameraCover(root.right.left) + minCameraCover(root.right.right);
        }

        // case 2 left is camera
        if (root.left != null) {
            b = 1;
            int b2 = 0;
            int b3 = 0;
            if (root.left.left != null) {
                b2 += minCameraCover(root.left.left.left);
                b2 += minCameraCover(root.left.left.right);
                b2 = Math.min(minCameraCover(root.left.left), b2);
            }
            if (root.left.right != null) {
                b3 += minCameraCover(root.left.right.left);
                b3 += minCameraCover(root.left.right.right);

                b3 = Math.min(minCameraCover(root.left.right), b3);
            }
            int rightCount = minCameraCover(root.right);
            b += b2 + b3 + rightCount;
        }

        // case 3 right is camera
        if (root.right != null) {
            c = 1;
            int c2 = 0;
            int c3 = 0;
            if (root.right.left != null) {
                c2 += minCameraCover(root.right.left.left);
                c2 += minCameraCover(root.right.left.right);
                c2 = Math.min(c2, minCameraCover(root.right.left));
            }
            if (root.right.right != null) {
                c3 += minCameraCover(root.right.right.left);
                c3 += minCameraCover(root.right.right.right);
                c3 = Math.min(c3, minCameraCover(root.right.right));
            }
            int leftCount = minCameraCover(root.left);
            c += c2 + c3 + leftCount;
        }
        int min = Math.min(Math.min(a, b), c);
        map.put(root, min);
        return min;
    }


    /**
     * 别人好的答案，值得学习
     */

    //NO_CAMERA表示的是子节点没有相机，当前节点也没放相机
    private final int NO_CAMERA = 0;
    //HAS_CAMERA表示当前节点有一个相机
    private final int HAS_CAMERA = 1;
    //NO_NEEDED表示当前节点没有相机，但他的子节点有一个相机，把它给
    //覆盖了，所以它不需要了。或者他是一个空的节点也是不需要相机的
    private final int NO_NEEDED = 2;

    //全局的，统计有多少相机
    int res = 0;

    public int minCameraCover2(TreeNode root) {
        //边界条件判断
        if (root == null)
            return 0;
        //如果最后返回的是NO_CAMERA，表示root节点的子节点也没有相机，
        //所以root节点要添加一个相机
        if (dfs(root) == NO_CAMERA)
            res++;
        //返回结果
        return res;
    }

    public int dfs(TreeNode root) {
        //如果是空的，就不需要相机了
        if (root == null)
            return NO_NEEDED;
        int left = dfs(root.left), right = dfs(root.right);
        //如果左右子节点有一个是NO_CAMERA，表示的是子节点既没相机，也没相机覆盖它，
        //所以当前节点需要有一个相机
        if (left == NO_CAMERA || right == NO_CAMERA) {
            //在当前节点放一个相机，统计相机的个数
            res++;
            return HAS_CAMERA;
        }
        //如果左右子节点只要有一个有相机，那么当前节点就不需要相机了，否则返回一个没有相机的标记
        return left == HAS_CAMERA || right == HAS_CAMERA ? NO_NEEDED : NO_CAMERA;
    }

}
