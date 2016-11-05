package com.sysensor.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sysensor.app.model.AppUser;

/**
 * 
 * Distribution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author malalanayake
 * @created Nov 8, 2015 7:47:13 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
