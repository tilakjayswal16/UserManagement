package com.bitbuy.usermanagement.dao;

import com.bitbuy.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUuid(String uuid);

    User findByUsername(String username);

    @Modifying
    @Query(value = "update User u set u.name = ?1, u.email = ?2, u.phone = ?3 where u.uuid = ?4",nativeQuery=true)
    @Transactional
    void updateUserByUUID(String name,String email,long phone, String uuid);
}
	