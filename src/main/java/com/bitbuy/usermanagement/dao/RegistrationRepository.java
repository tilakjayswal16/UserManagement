package com.bitbuy.usermanagement.dao;

import com.bitbuy.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<User, Long> {

}
	