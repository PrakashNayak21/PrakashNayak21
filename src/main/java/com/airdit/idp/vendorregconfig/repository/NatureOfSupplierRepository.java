package com.airdit.idp.vendorregconfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airdit.idp.vendorregconfig.model.NatureOfSupplier;

@Repository
public interface NatureOfSupplierRepository extends JpaRepository<NatureOfSupplier, String> {

}
