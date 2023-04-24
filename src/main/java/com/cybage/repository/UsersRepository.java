package com.cybage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.dto.ManagerDto;
import com.cybage.entity.Roles;
import com.cybage.entity.UserProfile;
import com.cybage.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByName(String username);
    
    List<Users> findByRole(Roles manager);

    Users findByEmail(String email);

    @Query(value = "select new com.cybage.dto.ManagerDto(m.userId, m.name, m.email, m.phoneNumber, m.role,  s.sportId,\r\n"
    		+ "			s.sportName) from Users m join m.sport s",nativeQuery = false)
    public List<ManagerDto> getAllManagers();

	
}
