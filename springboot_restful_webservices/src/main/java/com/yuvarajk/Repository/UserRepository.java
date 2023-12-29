package com.yuvarajk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuvarajk.entity.User;

public interface UserRepository extends JpaRepository <User, Long>
{

    
    
}
