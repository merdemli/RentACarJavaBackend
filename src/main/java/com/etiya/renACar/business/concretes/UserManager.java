package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.UserService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateUserRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateUserRequest;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.User;
import com.etiya.renACar.repository.abstracts.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private UserRepository userRepository;

    private ModelMapperService modelMapperService;

    public UserManager(UserRepository userRepository, ModelMapperService modelMapperService) {
        this.userRepository = userRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateUserRequest createUserRequest) {

        checkIfUserExistsByEmail(createUserRequest.getEmail());

        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        this.userRepository.save(user);
        return new SuccessResult(BusinessMessages.UserMessages.USER_ADDED_SUCCESSFULLY);
    }

    public Result update(UpdateUserRequest updateUserRequest) {

        checkIfUserExists(updateUserRequest.getUserId());

        User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
        this.userRepository.save(user);
        return new SuccessResult(BusinessMessages.UserMessages.USER_UPDATED_SUCCESSFULLY);
    }


    private void checkIfUserExists(int userId){
        if(this.userRepository.existsById(userId)){
            throw new BusinessException(BusinessMessages.UserMessages.EMAIL_ALREADY_EXISTS);
        }
    }

    private void checkIfUserExistsByEmail(String email){

        if(this.userRepository.existsByEmail(email)){
            throw new BusinessException(BusinessMessages.UserMessages.USER_ALREADY_EXISTS);
        }
    }
}
