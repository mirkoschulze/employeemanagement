package de.netgain.employeemanagementapplication.security;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author MirkoSchulze
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="authorities")
public class Authority {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    

}
