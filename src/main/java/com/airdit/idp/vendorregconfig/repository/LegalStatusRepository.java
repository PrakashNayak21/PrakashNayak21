 package com.airdit.idp.vendorregconfig.repository;
  
  import org.springframework.data.jpa.repository.JpaRepository;

import com.airdit.idp.vendorregconfig.model.LegalStatus;
  
  public interface LegalStatusRepository extends JpaRepository<LegalStatus,String>{
  
  }