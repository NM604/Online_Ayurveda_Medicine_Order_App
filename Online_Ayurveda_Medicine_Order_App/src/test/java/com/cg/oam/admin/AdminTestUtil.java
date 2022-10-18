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

/**
 * The Class AdminTestUtil.
 */
@SpringBootTest
@DisplayName("Validations for Admin Entity")
public class AdminTestUtil {
	
	/** The admin repository. */
	@Mock
	IAdminRepository adminRepository;
	
	/** The admin service. */
	@InjectMocks
	IAdminServiceImpl adminService;
	
	/**
	 * Adds an admin.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
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
	
	
	
	/**
	 * Adds a duplicate admin.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
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
	
	/**
	 * Update admin.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
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
	
	/**
	 * Update admin that is not present.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Updating Non-Existant Admin")
	public void updateAdminNotPresent() throws InvalidDataException{
		AdminDTO admin = new AdminDTO(2,"harry123");
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> adminService.updateAdmin(admin));
		Assertions.assertEquals("Service.ADMIN_NOT_FOUND", e.getMessage());	}
	
	
	/**
	 * Removes the admin.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Removing Existing Admin")
	public void removeAdmin() throws InvalidDataException{
		AdminDTO admin = new AdminDTO(1,"harry123");
		List<Admin> admins = new ArrayList<>();
		Admin newAdmin = new Admin(1,"harry123");
		
		
		Mockito.when(adminRepository.findByPassword(admin.getPassword())).thenReturn(admins);
		Mockito.when(adminRepository.save(Mockito.any())).thenReturn(newAdmin);
		adminService.addAdmin(admin);
		
		Mockito.when(adminRepository.findById(admin.getId())).thenReturn(Optional.of(newAdmin));
		Assertions.assertEquals(admin, adminService.removeAdmin(1));
		Mockito.verify(adminRepository).deleteById(1);
	}
	
	
	/**
	 * Removes an admin that is not present.
	 */
	@Test
	@DisplayName("Check Removing Non-Existant Admin")
	public void removeAdminNotPresent(){
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> adminService.removeAdmin(2));
		Assertions.assertEquals("Service.ADMIN_NOT_FOUND", e.getMessage());
	}
	
	/**
	 * Validate credentials.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
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
	
	/**
	 * Invalidate credentials.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
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
