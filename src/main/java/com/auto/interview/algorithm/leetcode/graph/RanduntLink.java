package com.auto.interview.algorithm.leetcode.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author : jihai
 * @date : 2020/9/17
 * @description :
 * 685. 冗余连接 II
 * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
 *
 * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 *
 * 结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
 *
 * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
 *
 * 示例 1:
 *
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的有向图如下:
 *   1
 *  / \
 * v   v
 * 2-->3
 * 示例 2:
 *
 * 输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * 输出: [4,1]
 * 解释: 给定的有向图如下:
 * 5 <- 1 -> 2
 *      ^    |
 *      |    v
 *      4 <- 3
 * 注意:
 *
 * 二维数组大小的在3到1000范围内。
 * 二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。
 */
public class RanduntLink {


    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1,2},{2,3},{3,4},{4,1},{1,5}
        };


        int[][] arr2 = new int[][]{
                {1,2},{1,3},{2,3}
        };
        RanduntLink randuntLink = new RanduntLink();
//        randuntLink.findRedundantDirectedConnection(arr);
        randuntLink.findRedundantDirectedConnection3(arr);

    }


    /**
     * 自己写的，十分的坎坷，效率及其底下
     */
    int total = 0;
    public int[] findRedundantDirectedConnection2(int[][] edges) {
        if (edges == null) return null;
        Set<Integer> set = new HashSet<>();
        for (int[] arr : edges) {
            set.add(arr[0]);
            set.add(arr[1]);
        }

        total = set.size();
        for (int i = edges.length - 1;i >= 0;i --) {
            if (isValid(edges, i)) {
                return edges[i];
            }
        }
        return null;
    }

    public boolean isValid(int[][] edges, int index){
        Integer root = null;
        // int rootCount = 0;
        for (int i = 0;i < edges.length;i ++) {
            if (i == index) continue;
            int t = edges[i][0];
            boolean isRoot = true;
            for (int j = 0;j < edges.length;j ++) {
                if (j == index) continue;
                if (edges[i][0] == edges[j][1] && edges[i][1] == edges[j][0]) return false;
                if (edges[j][1] == t) {
                    isRoot = false;
                    break;
                }
            }
            if (isRoot) {
                if (root != null && root != t) return false;
                root = t;
            }
        }
        if (root == null) return false;
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);

        while (! queue.isEmpty()) {
            Integer node = queue.poll();
            if (seen.contains(node)) return false;
            seen.add(node);
            for (int i = 0;i < edges.length;i ++) {
                if (i == index) continue;
                if (node == edges[i][0]) {
                    queue.offer(edges[i][1]);
                }
            }
        }
        return seen.size() == total;
    }


    private int []parent=null;

    private int find(int u){

        while(u!=parent[u]){
            //压缩路径
            parent[u]=parent[parent[u]];
            u=parent[u];

        }
        return u;
    }

    /**
     *
     * @param edges
     * @return
     * 需要考虑有没有环和重复的父节点
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {


        int [] backedge=new int[2];//存放最后一个后向边(环)
        int [] pending=new int[2];//存放最后一个重复的父节点
        parent = new int[edges.length + 1];

        for(int []edge:edges){

            if(parent[edge[1]]==0){//合并有向边
                parent[edge[1]]=edge[0];
            }else{//有重复的父节点
                pending=new int[]{edge[0],edge[1]};
                backedge=new int[]{parent[edge[1]],edge[1]};
                edge[1]=0;
            }
        }
        for (int i = 0; i <=edges.length; i++) {
            parent[i] = i;
        }

        for(int[]e:edges){

            if(e[1]==0){
                continue;
            }
            //判断有没有环
            if(find(e[0])==e[1]){
                return backedge[0]!=0?backedge:e;
            }
            parent[e[1]]=e[0];
        }

        return pending;
    }


    /**
     * 有向根树如果加一条边
     * 要了解树节点的入度和出度的概念
     * 如果是新加了一条边u->v,如果v是根节点，必然形成一个环。如果是v不是根节点，可能形成环也可能导致某个节点的入度为2。
     */
    public int[] findRedundantDirectedConnection3(int[][] edges) {
        // 节点数量，因为添加了一条额外的边，因此边数即为节点数
        int nodesCount = edges.length;
        UnionFind uf = new UnionFind(nodesCount + 1);
        int[] parent = new int[nodesCount + 1];
        for (int i = 1; i <= nodesCount; ++i) {
            parent[i] = i;
        }
        int conflict = -1;
        int cycle = -1;
        for (int i = 0; i < nodesCount; ++i) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (parent[node2] != node2) {
                conflict = i;
            } else {
                parent[node2] = node1;
                if (uf.find(node1) == uf.find(node2)) {
                    cycle = i;
                } else {
                    uf.union(node1, node2);
                }
            }
        }
        if (conflict < 0) {
            int[] redundant = {edges[cycle][0], edges[cycle][1]};
            return redundant;
        } else {
            int[] conflictEdge = edges[conflict];
            if (cycle >= 0) {
                int[] redundant = {parent[conflictEdge[1]], conflictEdge[1]};
                return redundant;
            } else {
                int[] redundant = {conflictEdge[0], conflictEdge[1]};
                return redundant;
            }
        }
    }

}

/**
 * 并查集类
 */
class UnionFind {
    // 存放并查集合
    int[] ancestor;

    /**
     * 构造序列的集合
     */
    public UnionFind(int n) {
        ancestor = new int[n];
        for (int i = 0; i < n; ++i) {
            ancestor[i] = i;
        }
    }

    public void union(int index1, int index2) {
        ancestor[find(index1)] = find(index2);
    }

    /**
     * 这个方法
     */
    public int find(int index) {
        if (ancestor[index] != index) {
            ancestor[index] = find(ancestor[index]);
        }
        return ancestor[index];
    }
}

