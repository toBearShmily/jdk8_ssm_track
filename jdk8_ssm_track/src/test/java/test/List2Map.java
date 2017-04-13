package test;

import com.alibaba.fastjson.JSON;
import com.shmily.util.ReturnListGB2Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */
public class List2Map {
    public static void main(String[] args) {
        User user = new User(1,"11",12);
        User user1 = new User(2,"22",13);
        User user2 = new User(3,"33",14);
        User user3 = new User(4,"44",12);
        User user4 = new User(5,"55",14);
        User user5 = new User(6,"66",12);
        User user6 = new User(7,"77",13);
        User user7 = new User(8,"88",12);

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        list.add(user7);

        System.out.println("分组前："+list);
        Map<Integer,List<User>> map = new HashMap<>();
        ReturnListGB2Map.listGroup2Map(list,map,User.class,"getAge");
        System.out.println("分组完成，分组后："+ map);
    }
}
