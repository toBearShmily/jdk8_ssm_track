package com.shmily.common.tree;

/**
 * 无限级节点模型
 * Created by wuxubiao on 2017/5/4.
 */
public class Node {
    /**
     * 节点id
     */
    private Integer id;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 父节点id
     */
    private Integer parentId;
    public Node() {
    }
    Node(Integer id, Integer parentId) {
        this.id = id;
        this.parentId = parentId;
    }
    Node(Integer id, String nodeName, Integer parentId) {
        this.id = id;
        this.nodeName = nodeName;
        this.parentId = parentId;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
