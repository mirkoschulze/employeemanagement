package de.netgain.employeemanagementapplication.security;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author MirkoSchulze
 */
public interface UserRepository extends JpaRepository<User, Long>{
    
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public Optional<User> getUserByUsername(String username);

}
