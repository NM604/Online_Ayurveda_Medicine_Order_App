package com.cg.oam.admin;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.oam.dto.AdminDTO;
import com.cg.oam.entity.Admin;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.IAdminRepository;
import com.cg.oam.service.IAdminService;
import com.cg.oam.service.IAdminServiceImpl;

@SpringBootTest
@DisplayName("Validations for Admin Entity")
public class AdminTestUtil {
	
	@Mock
	IAdminRepository adminRepository;
	
	@InjectMocks
	IAdminServiceImpl adminService;
	
	@Test
	@DisplayName("Check Adding New Admin")
	public void addAdmin() throws InvalidDataException{		
		AdminDTO admin = new AdminDTO(1,"harry123");
		List<Admin> admins = new ArrayList<>();
		Admin newAdmin = new Admin();
		
		Mockito.when(adminRepository.findByPassword(admin.getPassword())).thenReturn(admins);
		Mockito.when(adminRepository.save(Mockito.any())).thenReturn(newAdmin);
		Assertions.assertEquals(admin, adminService.addAdmin(admin));
	}
	
	
	
	@Test
	@DisplayName("Check Adding Duplicate Admin")
	public void addDuplicateAdmin() throws InvalidDataException{
		AdminDTO admin = new AdminDTO(1,"harry123");
		Admin dupAdmin = new Admin(1, "harry123");
		
		List<Admin> admins = new ArrayList<>();
		List<Admin> dupAdmins = new ArrayList<>();
		
		
		dupAdmins.add(dupAdmin);
		Admin newAdmin = new Admin();
		
		Mockito.when(adminRepository.findByPassword(admin.getPassword())).thenReturn(admins);
		Mockito.when(adminRepository.save(Mockito.any())).thenReturn(newAdmin);
		adminService.addAdmin(admin);
		
		Mockito.when(adminRepository.findByPassword(admin.getPassword())).thenReturn(dupAdmins);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> adminService.addAdmin(admin));
		Assertions.assertEquals("Service.ADMIN_FOUND", e.getMessage());
	}
	
	@Test
	@DisplayName("Check Updating Existing Admin")
	public void updateAdmin() throws InvalidDataException{
		AdminDTO admin = new AdminDTO(1,"harry123");
		AdminDTO admin1 = new AdminDTO(1,"harry1234");
		Optional<Admin> dupAdmin = Optional.of(new Admin(1, "harry123"));
		
		List<Admin> admins = new ArrayList<>();
		
		Admin newAdmin = new Admin();
		
		Mockito.when(adminRepository.findByPassword(admin.getPassword())).thenReturn(admins);
		Mockito.when(adminRepository.save(Mockito.any())).thenReturn(newAdmin);
		adminService.addAdmin(admin);
		
		Mockito.when(adminRepository.findById(admin.getId())).thenReturn(dupAdmin);
		Assertions.assertEquals(admin1, adminService.updateAdmin(admin1));
		}
	
	@Test
	@DisplayName("Check Updating Non-Existant Admin")
	public void updateAdminNotPresent() throws InvalidDataException{
		AdminDTO admin = new AdminDTO(2,"harry123");
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> adminService.updateAdmin(admin));
		Assertions.assertEquals("Service.ADMIN_NOT_FOUND", e.getMessage());	}
	
	/*
	@Test
	@DisplayName("Check Removing Existing Admin")
	public void removeAdmin() throws InvalidDataException{
		AdminDTO admin = new AdminDTO(1,"harry123");
		List<Admin> admins = new ArrayList<>();
		Admin newAdmin = new Admin();
		newAdmin.setId(1);
		newAdmin.setPassword("xyz");
		
		//Mockito.when(adminRepository.findByPassword(admin.getPassword())).thenReturn(admins);
		//Mockito.when(adminRepository.save(Mockito.any())).thenReturn(newAdmin);
		//adminService.addAdmin(admin);
		
		Mockito.when(adminRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(newAdmin));
		//Mockito.verify(adminRepository, times(3)).deleteById(1);
		Mockito.when(adminRepository.deleteById(Mockito.anyInt()));
	}
	*/
	
	@Test
	@DisplayName("Check Removing Non-Existant Admin")
	public void removeAdminNotPresent(){
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> adminService.removeAdmin(2));
		Assertions.assertEquals("Service.ADMIN_NOT_FOUND", e.getMessage());
	}
	
	@Test
	@DisplayName("Check Validating Existing Admin Credentials")
	public void validateCredentials() throws InvalidDataException{
		AdminDTO admin = new AdminDTO(1,"harry123");
		Optional<Admin> dupAdmin = Optional.of(new Admin(1, "harry123"));
		
		List<Admin> admins = new ArrayList<>();
		
		Admin newAdmin = new Admin();
		
		Mockito.when(adminRepository.findByPassword(admin.getPassword())).thenReturn(admins);
		Mockito.when(adminRepository.save(Mockito.any())).thenReturn(newAdmin);
		adminService.addAdmin(admin);
		
		Mockito.when(adminRepository.findById(admin.getId())).thenReturn(dupAdmin);
		Assertions.assertTrue(adminService.validateAdmin(admin.getId(),admin.getPassword()));
	}
	
	@Test
	@DisplayName("Check Validating Existing Admin Wrong Credentials")
	public void invalidateCredentials() throws InvalidDataException{
		AdminDTO admin = new AdminDTO(1,"harry123");
		Optional<Admin> dupAdmin = Optional.of(new Admin(1, "harry123"));
		
		List<Admin> admins = new ArrayList<>();
		
		Admin newAdmin = new Admin();
		
		Mockito.when(adminRepository.findByPassword(admin.getPassword())).thenReturn(admins);
		Mockito.when(adminRepository.save(Mockito.any())).thenReturn(newAdmin);
		adminService.addAdmin(admin);
		
		Mockito.when(adminRepository.findById(admin.getId())).thenReturn(dupAdmin);		
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, 
								() -> adminService.validateAdmin(admin.getId(),"harry1234"));
		Assertions.assertEquals("Service.INVALID_CREDENTIALS", e.getMessage());
	}

}
