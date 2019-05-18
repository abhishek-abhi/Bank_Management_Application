/**
 * 
 */
package com.epam.BankingApplication.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.BankingApplication.modals.User;

/**
 * @author Abhishek_Kumar_Manda
 *
 */
@Repository
public interface UserDaoService extends JpaRepository<User, Long> {

}
