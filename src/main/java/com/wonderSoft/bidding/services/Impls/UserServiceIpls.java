package com.wonderSoft.bidding.services.Impls;

import com.wonderSoft.bidding.dtos.UserDTO;
import com.wonderSoft.bidding.entities.UserAccount;
import com.wonderSoft.bidding.repositories.UserAccountRepository;
import com.wonderSoft.bidding.services.UserService;
import com.wonderSoft.bidding.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;


public class UserServiceIpls implements UserService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserServiceIpls(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public ResponseEntity<?> saveUser(UserDTO userDTO) {
        CommonResponse commonResponse = new CommonResponse();
        String id = userAccountRepository.save(new UserAccount(userDTO.getName(),userDTO.getRole())).getUId();
        HashMap<String, Object> response = new HashMap<>();
        response.put("userID",id);
        commonResponse.setPayload(Arrays.asList(response));
        commonResponse.setStatus(HttpStatus.CREATED);
        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }
}
