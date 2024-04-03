package com.example.userservicejan24.service;

import com.example.userservicejan24.models.Role;
import com.example.userservicejan24.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findRolesByIds(List<Long> roleIds) {
        return StreamSupport.stream(roleRepository.findAllById(roleIds).spliterator(), false)
                .collect(Collectors.toList());
    }

    public Role getDefaultRole() {
        return roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
    }
}