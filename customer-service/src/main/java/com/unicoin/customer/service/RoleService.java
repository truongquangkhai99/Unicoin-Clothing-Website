package com.unicoin.customer.service;

import com.unicoin.customer.entity.Role;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {

    <S extends Role> S save(S entity);

    void deleteById(Integer integer);

    <S extends Role> Page<S> findAll(Example<S> example, Pageable pageable);

    List<Role> findAll();

    boolean existsById(Integer integer);
}
