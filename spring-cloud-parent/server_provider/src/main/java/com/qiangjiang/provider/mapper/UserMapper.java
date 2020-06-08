package com.qiangjiang.provider.mapper;

import com.qiangjiang.provider.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insert(User user);
}
