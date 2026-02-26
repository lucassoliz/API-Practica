package com.example.springSecurityBasic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    //exigencia de Spring Security para el manejo de usuarios, si el usuario esta habilitado, si
    // la cuenta esta expirada, si la cuenta esta bloqueada, si las credenciales estan expiradas
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
//Un usuario puede tener muchos roles, un rol puede tener muchos usuarios, por eso es ManyToMany
    //Un rol puede tener muchos permisos, un permiso puede tener muchos roles, por eso es ManyToMany
    //Lo que si Usuario y Permiso no tienen relacion directa, ya que un usuario no tiene permisos
    // directamente, sino a traves de los roles
    //Usamos Set para evitar duplicados, ya que un usuario puede tener varios roles y permisos
    //List permite repetidos

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //EAGER: carga los roles y permisos junto
                            // con el usuario, LAZY: carga los roles y permisos solo cuando se accede a ellos
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> rolesList = new HashSet<>();

    public boolean isAccountNotExpired() {
        return accountNonExpired;
    }

    public boolean isCredentialNotExpired() {
        return credentialsNonExpired;
    }

    public boolean isAccountNotLocked() {
        return accountNonLocked;
    }

}
