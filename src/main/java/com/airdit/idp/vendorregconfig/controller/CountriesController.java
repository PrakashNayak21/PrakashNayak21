package com.airdit.idp.vendorregconfig.controller;

import static java.nio.file.Paths.get;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airdit.idp.vendorregconfig.Exception.AttchmentsRecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.RecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.ResourceNotFoundException;
import com.airdit.idp.vendorregconfig.model.Countries;
import com.airdit.idp.vendorregconfig.service.CountriesService;

@RestController
@RequestMapping("/countries")
public class CountriesController {

	public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/Flags";
	
	@Autowired
	private CountriesService countriesService;
	
	@GetMapping("/countrieslist")
	public List<Countries> getCountries() throws AttchmentsRecordNotFoundException{
		return this.countriesService.getCountries();
		
	}
	@GetMapping("/countries/{CountriesId}") 
	public Countries getCountriesCode(@PathVariable("CountriesId") @NotBlank @Size(max = 10) String CountriesId) throws RecordNotFoundException {
		return this.countriesService.getCountriesCode(CountriesId);
		
	}
	@PostMapping("/addcountries")
	public Countries insertCountries(@Valid @RequestBody Countries countries) throws ResourceNotFoundException {
		return this.countriesService.insert(countries);
		
	}
	
	@PutMapping("/updatecountries")
	public Countries updateCountries( @Valid @RequestBody Countries countries) throws ResourceNotFoundException, RecordNotFoundException {
		return this.countriesService.updateCountries( countries);
		
	}
	@DeleteMapping("/deletecountries/{CountriesId}")
	public void deleteLegalStatus(@PathVariable("CountriesId") String CountriesId) throws RecordNotFoundException {
		this.countriesService.deleteCountries(CountriesId);
		
	}
	
	@DeleteMapping("/deletecountriesPartially/{CountriesId}")
	public String deleteLegalStatusPartially(@PathVariable("CountriesId") String CountriesId) throws RecordNotFoundException,NoSuchElementException, AttchmentsRecordNotFoundException {
		return this.countriesService.deleteCountriesPartially(CountriesId);
		
	}
	
	@GetMapping("view/{filename}")
	public void downloadFiles(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("filename") String filename) throws IOException {
		Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
		if (!Files.exists(filePath))
			throw new FileNotFoundException(filename + " was not found on the server");
		byte[] imagebytes = Files.readAllBytes(Paths.get(filePath.toString()));
		if (imagebytes != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(imagebytes);
		}
		response.getOutputStream().close();
	}
}
