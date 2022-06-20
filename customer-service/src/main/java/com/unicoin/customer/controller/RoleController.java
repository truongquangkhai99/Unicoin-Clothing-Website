package com.unicoin.customer.controller;

import com.unicoin.customer.entity.Role;
import com.unicoin.customer.form.AddRoleForm;
import com.unicoin.customer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("admin/customer/role")
public class RoleController {
    @Autowired
    RoleService roleService;

@PostMapping("/save")
    public ResponseEntity<Role> creatRole(@RequestBody Role role) {
        roleService.save(role);
        return ResponseEntity.ok(role);
    }
@GetMapping("")
    public ResponseEntity<List<Role>> getRoles(){
    if(roleService.findAll().size() <= 0){
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(roleService.findAll());
}
@PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id,@RequestBody Role role){
    if(!roleService.existsById(id)){
        return ResponseEntity.notFound().build();
    }
    roleService.save(role);
    return ResponseEntity.ok(role);
}

}
