package com.yupi.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.usercenter.model.domain.Tag;
import com.yupi.usercenter.service.TagService;
import com.yupi.usercenter.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author 黄健
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2023-11-02 20:01:59
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




