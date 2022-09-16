package com.illtamer.sillage.rear.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.illtamer.sillage.rear.mapper.TypeMapper;
import com.illtamer.sillage.rear.pojo.Type;
import com.illtamer.sillage.rear.vo.CountType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeService extends ServiceImpl<TypeMapper, Type> {

    private final BlogService blogService;

    public TypeService(BlogService blogService) {
        this.blogService = blogService;
    }

    public List<CountType> listCountType() {
        final List<Type> types = list();
        return types.stream()
                .map(type -> {
                    CountType countType = new CountType();
                    BeanUtils.copyProperties(type, countType);
                    final int blogCount = blogService.countByTypeId(type.getId());
                    countType.setBlogCount(blogCount);
                    return countType;
                })
                .collect(Collectors.toList());
    }

}
