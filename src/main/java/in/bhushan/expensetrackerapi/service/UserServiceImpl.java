package in.bhushan.expensetrackerapi.service;

import in.bhushan.expensetrackerapi.entity.User;
import in.bhushan.expensetrackerapi.entity.UserModel;
import in.bhushan.expensetrackerapi.exceptions.ItemAlreadyExistsException;
import in.bhushan.expensetrackerapi.exceptions.ResourceNotFoundException;
import in.bhushan.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel) {
        if(userRepository.existsByEmail(userModel.getEmail())){
            throw new ItemAlreadyExistsException("User is already registered with email: " + userModel.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(userModel,user);
        return userRepository.save(user);
    }

    @Override
    public User read(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for the id: "+id));
    }

    @Override
    public User update(UserModel user, Long id) throws ResourceNotFoundException{
        User oUser = read(id);
        oUser.setName(user.getName() != null ? user.getName() : oUser.getName());
        oUser.setEmail(user.getEmail() != null ? user.getEmail() : oUser.getEmail());
        oUser.setPassword(user.getPassword() != null ? user.getPassword() : oUser.getPassword());
        oUser.setAge(user.getAge() != null ? user.getAge() : oUser.getAge());
        return userRepository.save(oUser);
    }

    @Override
    public void delete(Long id) {
        User user = read(id);
        userRepository.delete(user);
    }
}
