package com.cg.oam.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.oam.dto.UserDTO;
import com.cg.oam.entity.User;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.IUserRepository;
import com.cg.oam.service.IUserServiceImpl;

@SpringBootTest
@DisplayName("Validations for User Entity")
public class UserTestUtil {
	
	@Mock
	IUserRepository userRepository;
	
	@InjectMocks
	IUserServiceImpl userService;
	
	@Test
	@DisplayName("Check Adding New User")
	public void addAdmin() throws InvalidDataException{		
		UserDTO user = new UserDTO(1,"harry123","administrator");
		List<User> users = new ArrayList<>();
		User newUser = new User();
		
		Mockito.when(userRepository.findByuserName(user.getUserName())).thenReturn(users);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(newUser);
		Assertions.assertEquals(user, userService.addUser(user));
	}
	
	
	
	@Test
	@DisplayName("Check Adding Duplicate User")
	public void addDuplicateAdmin() throws InvalidDataException{
		UserDTO user = new UserDTO(1,"harry123","administrator");
		User dupUser = new User(1,"harry123","administrator");
		
		List<User> users = new ArrayList<>();
		List<User> dupUsers = new ArrayList<>();
		
		
		dupUsers.add(dupUser);
		User newAdmin = new User();
		
		Mockito.when(userRepository.findByuserName(user.getUserName())).thenReturn(users);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(newAdmin);
		userService.addUser(user);
		
		Mockito.when(userRepository.findByuserName(user.getUserName())).thenReturn(dupUsers);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> userService.addUser(user));
		Assertions.assertEquals("Service.USER FOUND", e.getMessage());
	}
	
	@Test
	@DisplayName("Check Updating Existing User")
	public void updateAdmin() throws InvalidDataException{
		UserDTO user = new UserDTO(1,"harry123","administrator");
		UserDTO user1 = new UserDTO(1,"harry1234","administrator");
		Optional<User> dupUser = Optional.of(new User(1,"harry123","administrator"));
		
		List<User> users = new ArrayList<>();
		
		User newUser = new User();
		
		Mockito.when(userRepository.findByuserName(user.getUserName())).thenReturn(users);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(newUser);
		userService.addUser(user);
		
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(dupUser);
		Assertions.assertEquals(user1, userService.updateUser(user1));
		}
	
	@Test
	@DisplayName("Check Updating Non-Existant User")
	public void updateAdminNotPresent() throws InvalidDataException{
		UserDTO user = new UserDTO(1,"harry123","administrator");
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> userService.updateUser(user));
		Assertions.assertEquals("Service.USER_NOT_FOUND", e.getMessage());	}
	
	/*
	@Test
	@DisplayName("Check Removing Existing User")
	public void removeAdmin() throws InvalidDataException{
		UserDTO user = new UserDTO(1,"harry123");
		List<User> user = new ArrayList<>();
		User newUser = new User();
		
		Mockito.when(userRepository.findByPassword(user.getPassword())).thenReturn(users);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(newUser);
		userService.addUser(user);
		
		Mockito.verify(userRepository, times(3)).deleteById(1);
	}
	*/
	
	@Test
	@DisplayName("Check Removing Non-Existant User")
	public void removeAdminNotPresent(){
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> userService.removeUser(2));
		Assertions.assertEquals("Service.USER_NOT_FOUND", e.getMessage());
	}
	
	@Test
	@DisplayName("Check Validating Existing User Credentials")
	public void validateCredentials() throws InvalidDataException{
		UserDTO user = new UserDTO(1,"harry123","administrator");
		Optional<User> dupUser = Optional.of(new User(1,"harry123","administrator"));
		
		List<User> users = new ArrayList<>();
		
		User newUser = new User();
		
		Mockito.when(userRepository.findByuserName(user.getUserName())).thenReturn(users);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(newUser);
		userService.addUser(user);
		
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(dupUser);
		Assertions.assertTrue(userService.validateUser(user.getUserId(),user.getUserName()));
	}
	
	@Test
	@DisplayName("Check Validating Existing User Wrong Credentials")
	public void invalidateCredentials() throws InvalidDataException{
		UserDTO user = new UserDTO(1,"harry123","administrator");
		Optional<User> dupUser = Optional.of(new User(1,"harry123","administrator"));
		
		List<User> users = new ArrayList<>();
		
		User newUser = new User();
		
		Mockito.when(userRepository.findByuserName(user.getUserName())).thenReturn(users);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(newUser);
		userService.addUser(user);
		
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(dupUser);		
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, 
								() -> userService.validateUser(user.getUserId(),"harry1234"));
		Assertions.assertEquals("Service.INVALID_CREDENTIALS", e.getMessage());
	}

}