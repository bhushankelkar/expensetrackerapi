package in.bhushan.expensetrackerapi.service;

import in.bhushan.expensetrackerapi.entity.User;
import in.bhushan.expensetrackerapi.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);
    User read(Long id);
    User update(UserModel user,Long id);
    void delete(Long id);
}
