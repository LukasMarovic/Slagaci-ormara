package com.progi.progi.repository;

import com.progi.progi.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    public List<Users> findByEmail(@Param("email") String email);
}
