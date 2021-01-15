package com.wonderSoft.bidding.repositories;

import com.wonderSoft.bidding.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,String> {
}
