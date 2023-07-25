package de.netgain.employeemanagementapplication.security;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author MirkoSchulze
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    public Optional<Role> getRoleByName(String name);

}
