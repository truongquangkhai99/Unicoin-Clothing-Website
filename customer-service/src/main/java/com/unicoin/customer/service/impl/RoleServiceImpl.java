package com.unicoin.customer.service.impl;

import com.unicoin.customer.entity.Role;
import com.unicoin.customer.repository.RoleRepository;
import com.unicoin.customer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public <S extends Role> S save(S entity) {
        return roleRepository.save(entity);
    }

    @Override
    public void deleteById(Long integer) {

    }

    @Override
    public <S extends Role> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public boolean existsById(Long integer) {
        return false;
    }

    @Override
    public Optional<Role> findById(Long integer) {
        return Optional.empty();
    }

}
