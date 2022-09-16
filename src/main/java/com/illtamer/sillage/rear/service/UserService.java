package com.illtamer.sillage.rear.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.illtamer.sillage.rear.mapper.UserMapper;
import com.illtamer.sillage.rear.pojo.User;
import com.illtamer.sillage.rear.vo.MinUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    /**
     * 查询最简用户 VO
     * */
    public MinUser getMinUserById(Integer id) {
        final LambdaQueryWrapper<User> wrapper = getWrapper()
                .select(User::getId, User::getNick, User::getAvatar)
                .eq(User::getId, id);
        final User user = getOne(wrapper);
        final MinUser minUser = new MinUser();
        BeanUtils.copyProperties(user, minUser);
        return minUser;
    }

    private static LambdaQueryWrapper<User> getWrapper() {
        return new LambdaQueryWrapper<>();
    }

}
