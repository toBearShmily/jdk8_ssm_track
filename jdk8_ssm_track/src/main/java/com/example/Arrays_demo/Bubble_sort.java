package com.example.Arrays_demo;

import org.apache.commons.collections4.map.LinkedMap;

import java.util.Map;

/**
 * 冒泡排序
 * Created by Administrator on 2017/2/17.
 */
public class Bubble_sort {
    public static void main(String[] args){
        int[] a = {9,4,3,1,5,8};
        Bubble_sort.BubbleSort(a);
    }

    public static void BubbleSort(int[] a){
        for(int i = 0;i < a.length-1;i++){
            for(int j = 0;j<a.length-i-1;j++){
                if(a[j] > a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"次排序");
            for(int k = 0;k<a.length;k++){
                System.out.print(a[k]+" ");
            }
            System.out.println();
        }
    }

}
