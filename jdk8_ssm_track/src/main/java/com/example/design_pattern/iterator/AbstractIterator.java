package com.example.design_pattern.iterator;

import java.util.List;

/**
 * 迭代器模式
 * 抽象迭代器
 * Created by Administrator on 2017/4/7.
 */
public interface AbstractIterator {
    /**
     * 移至下一个元素
     */
    public void next();

    /**
     * 移至上一个元素
     */
    public void previous();

    /**
     * 判断是否为第一个元素
     * @return
     */
    public boolean isFirst();

    /**
     * 判断是否为最后一个元素
     * @return
     */
    public boolean isLast();

    /**
     * 获取下一个元素
     * @return
     */
    public Object getNextItem();

    /**
     * 获取上一个元素
     * @return
     */
    public Object getPreviousItem();

}


//商品迭代器（具体迭代器）
class ProductIterator implements AbstractIterator{

    private ProductList productList ;
    private List<Object> products ;
    private int cursor1; // 定义一个游标，用于记录正向遍历的位置
    private int cursor2; // 定义一个游标，用于记录逆向遍历的位置

    public ProductIterator(ProductList list) {
        this.setProductList(list);
        this.products = list.getObjects(); // 获取集合对象
        cursor1 = 0; // 设置正向遍历游标的初始值
        cursor2 = products.size() - 1; // 设置逆向遍历游标的初始值
    }

    @Override
    public void next() {
        if(cursor1 < products.size()){
            cursor1++;
        }
    }

    @Override
    public void previous() {
        if(cursor2 > -1){
            cursor2--;
        }
    }

    @Override
    public boolean isFirst() {
        return (cursor2 == -1);
    }

    @Override
    public boolean isLast() {
        return (cursor1 == products.size());
    }

    @Override
    public Object getNextItem() {
        return products.get(cursor1);
    }

    @Override
    public Object getPreviousItem() {
        return products.get(cursor2);
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }
}