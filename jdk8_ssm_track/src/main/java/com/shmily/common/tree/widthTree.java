package com.shmily.common.tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 宽度遍历
 * Created by wuxubiao on 2017/5/4.
 */
public class widthTree {

    public static void main(String[] args) {
        List<Node> nodeList = new ArrayList<Node>();
        Node node1 = new Node(1, "蔬菜", 0);
        Node node2 = new Node(2, "水产", 0);
        Node node3 = new Node(3, "畜牧", 0);
        Node node4 = new Node(4, "瓜类", 1);
        Node node5 = new Node(5, "叶类", 1);
        Node node6 = new Node(6, "丝瓜", 4);
        Node node7 = new Node(7, "黄瓜", 4);
        Node node8 = new Node(8, "白菜", 1);
        Node node9 = new Node(9, "虾", 2);
        Node node10 = new Node(10, "鱼", 2);
        Node node11 = new Node(11, "牛", 3);

        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        nodeList.add(node4);
        nodeList.add(node5);
        nodeList.add(node6);
        nodeList.add(node7);
        nodeList.add(node8);
        nodeList.add(node9);
        nodeList.add(node10);
        nodeList.add(node11);

        Map<Integer,List<Node>> map = new widthTree().traverse(2,nodeList);
        System.out.println(JSON.toJSONString(map));

    }

    /**
     * @param pid 要遍历的顶层节点
     * @param list
     * @return
     */
    public Map<Integer,List<Node>> traverse(Integer pid,List<Node> list){
        Map<Integer,List<Node>> map = new HashMap<>();
        List<Node> childs = null;
        //parent的所有直接子节点
        childs = getChilds(list,pid);
        map.put(pid,childs);
        List<Node> tempChilds = null;
        while ((null != childs && childs.size() > 0)) {
            tempChilds = new ArrayList<>();
            for (Node child : childs) { // 遍历子节点
                // ------------
                // 获取child内容，或其他处理
                // ------------
                if (getChilds(list, child.getId()) != null && getChilds(list, child.getId()).size() > 0) {
                    List<Node> l = getChilds(list, child.getId());
                    map.put(child.getId(),l);
                    for(Node no : l){
                        tempChilds.add(no);
                    }
                }
            }
            childs = tempChilds;
        }
        return map;
    }

    /**
     * 给定父节点获取直接子节点(仅获取到下一级)
     * @param pid 父节点
     * @return
     */
    private List<Node> getChilds(List<Node> list ,Integer pid){
        List<Node> resultList = new ArrayList<>();

        for(Node n : list){
            if(n.getParentId() == pid){
                resultList.add(n);
            }
        }
        return resultList;
    }
}