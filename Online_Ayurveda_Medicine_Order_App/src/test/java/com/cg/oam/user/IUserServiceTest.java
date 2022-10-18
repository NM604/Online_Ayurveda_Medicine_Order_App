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

/**
 * The Class UserTestUtil.
 */
@SpringBootTest
@DisplayName("Validations for User Entity")
public class IUserServiceTest {
	
	/** The user repository. */
	@Mock
	IUserRepository userRepository;
	
	/** The user service. */
	@InjectMocks
	IUserServiceImpl userService;
	
	/**
	 * Adds a new User.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Adding New User")
	public void addUser() throws InvalidDataException{		
		UserDTO user = new UserDTO(1,"harry123","administrator");
		List<User> users = new ArrayList<>();
		User newUser = new User();
		
		Mockito.when(userRepository.findByuserName(user.getUserName())).thenReturn(users);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(newUser);
		Assertions.assertEquals(user, userService.addUser(user));
	}
	
	
	
	/**
	 * Adds a duplicate User.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Adding Duplicate User")
	public void addDuplicateUser() throws InvalidDataException{
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
		Assertions.assertEquals("Service.USER_FOUND", e.getMessage());
	}
	
	/**
	 * Update User.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Updating Existing User")
	public void updateUser() throws InvalidDataException{
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
	
	/**
	 * Update User that is not present.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Updating Non-Existant User")
	public void updateUserNotPresent() throws InvalidDataException{
		UserDTO user = new UserDTO(1,"harry123","administrator");
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> userService.updateUser(user));
		Assertions.assertEquals("Service.USER_NOT_FOUND", e.getMessage());	}
	
	/**
	 * Removes the user.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Removing Existing User")
	public void removeUser() throws InvalidDataException{
		UserDTO user = new UserDTO(1,"harry123","administrator");
		List<User> userList = new ArrayList<>();
		User newUser = new User(1,"harry123","administrator");
		
		Mockito.when(userRepository.findByuserName(user.getUserName())).thenReturn(userList);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(newUser);
		userService.addUser(user);
		
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(newUser));
		Assertions.assertEquals(user, userService.removeUser(1));
		Mockito.verify(userRepository).deleteById(1);
	}
	
	/**
	 * Removes a User that is not present.
	 */
	@Test
	@DisplayName("Check Removing Non-Existant User")
	public void removeUserNotPresent(){
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> userService.removeUser(2));
		Assertions.assertEquals("Service.USER_NOT_FOUND", e.getMessage());
	}
	
	/**
	 * Validate credentials.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
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
	
	/**
	 * Invalidate credentials.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
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