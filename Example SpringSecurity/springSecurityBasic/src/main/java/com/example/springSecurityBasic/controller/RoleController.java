package com.example.springSecurityBasic.controller;


import com.example.springSecurityBasic.model.Permission;
import com.example.springSecurityBasic.model.Role;
import com.example.springSecurityBasic.service.IPermissionService;
import com.example.springSecurityBasic.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;
//Esto porque si no lo hago, al crear un nuevo permiso, no me lo va a encontrar en la base de datos
// y no me lo va a guardar en la lista del rol, por lo tanto, el rol se va a crear sin permisos asociados.
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Esto porque si no lo hago, al crear un nuevo permiso, no me lo va a encontrar en la base de datos
    // y no me lo va a guardar en la lista del rol, por lo tanto, el rol se va a crear sin permisos asociados.
    //Por lo tanto, al crear un nuevo rol, tengo que recuperar la Permission/s por su ID, y guardarla en una
    // lista, para luego setear esa lista en el rol, y así poder guardar el rol con sus permisos asociados.
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Set<Permission> permissionList = new HashSet<Permission>();
        Permission readPermission;

        // Recuperar la Permission/s por su ID
        for (Permission per : role.getPermissionsList()) {
            readPermission = permissionService.findById(per.getId()).orElse(null);
            if (readPermission != null) {
                //si encuentro, guardo en la lista
                permissionList.add(readPermission);
            }
        }

        role.setPermissionsList(permissionList);
        Role newRole = roleService.save(role);
        return ResponseEntity.ok(newRole);
    }
}
