package com.airdit.idp.vendorregconfig.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airdit.idp.vendorregconfig.Exception.AttchmentsRecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.RecordNotFoundException;
import com.airdit.idp.vendorregconfig.Exception.ResourceNotFoundException;
import com.airdit.idp.vendorregconfig.attachments.util.DatabaseRecordfindUtil;
import com.airdit.idp.vendorregconfig.model.Countries;
import com.airdit.idp.vendorregconfig.repository.CountriesRepository;
import com.airdit.idp.vendorregconfig.service.CountriesService;

@Service
public class CountriesServiceimpl implements CountriesService {
	DatabaseRecordfindUtil Countriesutil = new DatabaseRecordfindUtil();

	@Autowired
	private CountriesRepository countriesRepository;

	@Override
	public Countries insert(Countries countries) throws ResourceNotFoundException {
		Optional<Countries> exiscountries = Optional.ofNullable(countriesRepository.save(countries));
		if (exiscountries.isPresent()) {
			return exiscountries.get();
		} else {
			throw new ResourceNotFoundException("{database.connection.error}");
		}
	}

	@Override
	public List<Countries> getCountries() throws AttchmentsRecordNotFoundException {
		List<Countries> CountriesList = countriesRepository.findAll();

		if (CountriesList.size() > 0) {

			List<Countries> Countrieslist = Countriesutil.CheckCountriesRecordAvilableinDatabaseTable(CountriesList);
			if (Countrieslist != null && Countrieslist.size() > 0) {
				return Countrieslist;
			} else {
				throw new AttchmentsRecordNotFoundException("{module.record.id.doesnot.Exist}");
			}
		} else {
			throw new AttchmentsRecordNotFoundException("{module.record.id.doesnot.Exist}");
		}

	}

	@Override
	public Countries getCountriesCode(String CountriesId) throws RecordNotFoundException {
		Optional<Countries> countries = (countriesRepository.findById(CountriesId));
		Countries countrie = countriesRepository.findById(CountriesId).get();
		if (countries.isPresent() && countrie.isDelete() != true) {
			System.out.println(countrie.getCode());
			return countrie;

		} else {
			throw new RecordNotFoundException("{record.id.not.Exist}");
		}
	}

	@Override
	public void deleteCountries(String CountriesId) throws RecordNotFoundException {
		Optional<Countries> countries = (countriesRepository.findById(CountriesId));
		if (countries.isPresent()) {
			countriesRepository.deleteById(CountriesId);
			System.out.println(countries + " id is deleted");
		} else {
			throw new RecordNotFoundException("{record.id.not.Exist}");
		}

	}

	@Override
	public String deleteCountriesPartially(String CountriesId)
			throws NoSuchElementException, AttchmentsRecordNotFoundException {
		Optional<Countries> countries = Optional.ofNullable(countriesRepository.getOne(CountriesId));
		Countries country = countriesRepository.findById(CountriesId).get();
		if (countries.isPresent() && !country.isDelete()) {
			country.setDelete(true);

			countriesRepository.save(country);
			return "record delete sucessfully";

		} else if (country != null && country.isDelete() == true) {
			return "no record found";
		}

		else {
			System.out.println("throw");
			throw new AttchmentsRecordNotFoundException("{attchments.record.id.doesnot.Exist}");
		}

	}
	
	@Override
	public Countries updateCountries(Countries countries) throws ResourceNotFoundException, RecordNotFoundException {
		Optional<Optional<Countries>> existcountries = Optional
				.ofNullable(countriesRepository.findById(countries.getCode()));

		if (existcountries.isPresent()) {

			if (existcountries.get().get().isDelete() != true && countries.isDelete() != true) {
				return countriesRepository.save(countries);
			} else {
				throw new RecordNotFoundException("{record.id.not.Exist}");
			}

		} else {
			throw new RecordNotFoundException("{record.id.not.Exist}");
		}
	}

}
