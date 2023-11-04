package com.yupi.usercenter.once;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    * 导入星球用户到数据库
    *
    * */
public class ImportXingQiuUser {

    private static Map<String, List<XingQiuTableUserInfo>> listMap;

    public static void main(String[] args) {
        String fileName="C:\\Users\\黄健\\Desktop\\testExecl.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<XingQiuTableUserInfo> userInfoList = EasyExcel.read(fileName).head(XingQiuTableUserInfo.class).sheet().doReadSync();
        for (XingQiuTableUserInfo data : userInfoList) {
            System.out.println(data);
        }
        System.out.println("总数:"+userInfoList.size());
        //根据用户名分组
        listMap = userInfoList
                .stream()
                .filter(userInfo->
                    StringUtils.isNotEmpty(userInfo.getUsername())
                )
                .collect(Collectors.groupingBy(XingQiuTableUserInfo::getUsername));
        System.out.println("不重复昵称数量："+ listMap .keySet().size());
        for (Map.Entry<String, List<XingQiuTableUserInfo>> stringListEntry : listMap.entrySet()) {
            if (stringListEntry.getValue().size() >1){
                System.out.println("重复名称："+stringListEntry.getKey());
                System.out.println("================================");
            }
        }
    }
}
