package com.cg.oam;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDetailDTO;
import com.cg.oam.service.IOrderDetailService;

@SpringBootApplication
public class OnlineAyurvedaMedicineOrderAppApplication{
	

	public static void main(String[] args) {
		SpringApplication.run(OnlineAyurvedaMedicineOrderAppApplication.class, args);
	}

}
