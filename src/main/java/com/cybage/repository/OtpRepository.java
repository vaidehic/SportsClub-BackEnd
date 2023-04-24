package com.cybage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.entity.Otp;
import com.cybage.entity.Users;
@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {

	Otp findByUser(Users user);
}
