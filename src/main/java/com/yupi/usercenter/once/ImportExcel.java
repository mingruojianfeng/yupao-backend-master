package com.yupi.usercenter.once;

import com.alibaba.excel.EasyExcel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
* 导入excel
*
* */
public class ImportExcel {
    public static void main(String[] args) {

        String fileName = "C:\\Users\\黄健\\Desktop\\testExecl.xlsx";
         listenerRead(fileName);

        synchronousRead(fileName);
    }

        /**
         * 监听器读
         *
         * @param fileName
         */
        public static void listenerRead (String fileName){
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
            EasyExcel.read(fileName, XingQiuTableUserInfo.class, new TableListener()).sheet().doRead();
        }

        /**
         * 同步读
         *
         * @param fileName
         */
        public static void synchronousRead (String fileName){
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
            List<XingQiuTableUserInfo> userInfoListlist = EasyExcel.read(fileName).head(XingQiuTableUserInfo.class).sheet().doReadSync();
            for (XingQiuTableUserInfo data : userInfoListlist) {
                System.out.println(data);
            }
            //根据用户名分组
            Map<String, List<XingQiuTableUserInfo>> collect = userInfoListlist.stream().collect(Collectors.groupingBy(XingQiuTableUserInfo::getUsername));
            System.out.println("不重复昵称数量："+collect.keySet().size());
        }
    }

