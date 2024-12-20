package com.syf.blog1.service;

import com.syf.blog1.NotFoundException;
import com.syf.blog1.dao.TypeRepository;
import com.syf.blog1.po.Type;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServicelmpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return null;
    }


    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }
    @Transactional
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
//    public List<Type> listTypeTop(Integer size) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
//        Pageable pageable=PageRequest.of(0, size,sort);
//
//        return typeRepository.findTop(pageable);
//    }
    public List<Type> listTypeTop(Integer size) {
        Pageable pageable = PageRequest.of(0, size); // 无需排序，查询中已经处理
        return typeRepository.findTop(pageable);
    }

    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.getOne(id);
        if (t == null) {
            throw new NotFoundException("不存在");

        }
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);

    }
}

