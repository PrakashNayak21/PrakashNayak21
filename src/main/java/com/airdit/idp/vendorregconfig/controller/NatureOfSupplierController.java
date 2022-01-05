package com.airdit.idp.vendorregconfig.controller;

import java.util.List;
import java.util.NoSuchElementException;

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
import com.airdit.idp.vendorregconfig.model.NatureOfSupplier;
import com.airdit.idp.vendorregconfig.service.NatureOfSupplierService;

@RestController
@RequestMapping("/natureofsupplier")
public class NatureOfSupplierController {
	@Autowired
	private NatureOfSupplierService natureOfSupplierService;

	@GetMapping("/natureofsupplier")
	public List<NatureOfSupplier> getNatureOfSupplier() throws AttchmentsRecordNotFoundException {
		return this.natureOfSupplierService.getNatureOfSupplier();
	}

	@GetMapping("/natureofsupplier/{naturecode}")
	public NatureOfSupplier getNatureofSupplier(@PathVariable("naturecode") @NotBlank @Size(max = 10) String naturecode)
			throws RecordNotFoundException,NoSuchElementException {
				return natureOfSupplierService.getNatureCode(naturecode) ;
//            NatureOfSupplier ntr = natureOfSupplierService.getNatureCode(naturecode);
//            System.out.println(ntr.getNatureCode());
//		return natureOfSupplierService.getNatureCode(naturecode);
          

	}

	@PostMapping("/addnatureofsupplier")
	public NatureOfSupplier insertNatureOfSupplier(@Valid @RequestBody NatureOfSupplier natureOfSupplier)
			throws ResourceNotFoundException {
		return this.natureOfSupplierService.insert(natureOfSupplier);
	}

	@PutMapping("/updatenatureofsupplier")
	public NatureOfSupplier updateNatureOfSupplier(@Valid @RequestBody NatureOfSupplier natureOfSupplier)
			throws RecordNotFoundException {
		return this.natureOfSupplierService.updateNatureOfSupplier(natureOfSupplier);
	}

	@DeleteMapping("/deletenatureofsupplier/{naturecode}")
	public void deleteNatureOfSupplier(@PathVariable("naturecode") String naturecode) throws RecordNotFoundException {
		this.natureOfSupplierService.deleteNatureOfSupplier(naturecode);
	}
	@DeleteMapping("/deleteNatureOfSupplierPartially/{naturecode}")
	public String deleteNatureOfSupplierPartially(@PathVariable("naturecode") String naturecode) throws RecordNotFoundException,NoSuchElementException {
		return this.natureOfSupplierService.deleteNatureOfSupplierPartially(naturecode);
	}
	
}
	
	