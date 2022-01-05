
package com.airdit.idp.vendorregconfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airdit.idp.vendorregconfig.model.Attachments;

@Repository
public interface AttachmentsRepository extends JpaRepository<Attachments, String> {
	
}


