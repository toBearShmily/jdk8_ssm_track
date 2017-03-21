package com.example.Arrays_demo;

/**
 * 二分法查找
 * Created by Administrator on 2017/2/20.
 */
public class binary_sort {

    public static void main(String[] args){
        int[] b = new int[]{1,2,4,6,8,18,79,199};
        int result = binarySort(b,18);
        System.out.println(result);
    }



    public static int binarySort(int[] arr , int value){
        int low=0;//首位
        int high=arr.length-1;//末尾
        int middle;//中间

        while (low <= high){
            middle = (low+high) / 2;

            if(value == arr[middle]){
                return middle;
            }

            if(value < arr[middle]){
                high = middle - 1;
            }

            if(value > arr[middle]){
                low = middle + 1;
            }
        }
        return -1;
    }
}
