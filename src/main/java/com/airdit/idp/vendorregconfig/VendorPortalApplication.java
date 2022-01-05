package com.airdit.idp.vendorregconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class VendorPortalApplication {
	public static void main(String[] args) {
		SpringApplication.run(VendorPortalApplication.class, args);
	}
}
