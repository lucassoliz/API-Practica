package com.example.springSecurityBasic.controller;


import com.example.springSecurityBasic.model.Permission;
import com.example.springSecurityBasic.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionService.findAll();
        return ResponseEntity.ok(permissions);
    }
//Esto porque si no lo hago, al crear un nuevo permiso, no me lo va a encontrar en la base de datos
// y no me lo va a guardar en la lista del rol, por lo tanto, el rol se va a crear sin permisos asociados.
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id) {
        Optional<Permission> permission = permissionService.findById(id);
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
//Esto porque si no lo hago, al crear un nuevo permiso, no me lo va a encontrar en la base de datos
// y no me lo va a guardar en la lista del rol, por lo tanto, el rol se va a crear sin permisos asociados.
    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        Permission newPermission = permissionService.save(permission);
        return ResponseEntity.ok(newPermission);
    }

}

