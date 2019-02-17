package com.arjunsk.springsecurity.persistance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arjunsk.springsecurity.persistance.entities.RoleEntity;
import com.arjunsk.springsecurity.persistance.entities.RoleName;

public interface RoleRepository  extends JpaRepository<RoleEntity,Long>{
	
    Optional<RoleEntity> findByName(RoleName roleName);

}
