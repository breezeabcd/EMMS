package com.wzydev.mapper;

import com.wzydev.pojo.UserExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserExprMapper {
    /**
     * 批量保存用户工作经历
     * @param userExprList
     */
    void insertBatch(List<UserExpr> userExprList);

    /**
     * 根据用户id批量删除用户工作经历
     * @param userIds
     */
    void deleteByUserIds(List<Integer> userIds);
}
