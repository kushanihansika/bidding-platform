package com.wonderSoft.bidding.services;

import com.wonderSoft.bidding.dtos.UserDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<?> saveUser(UserDTO userDTO);

}
