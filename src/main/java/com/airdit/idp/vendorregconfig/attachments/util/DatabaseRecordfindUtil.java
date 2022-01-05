
package com.airdit.idp.vendorregconfig.attachments.util;

import java.util.ArrayList;
import java.util.List;

import com.airdit.idp.vendorregconfig.model.Attachments;
import com.airdit.idp.vendorregconfig.model.Countries;
import com.airdit.idp.vendorregconfig.model.LegalStatus;
import com.airdit.idp.vendorregconfig.model.NatureOfSupplier;

public class DatabaseRecordfindUtil {
	
	public List<Attachments> checkAttachmentrecordAvilableinDatabaseTable(List<Attachments> attachlist) {
		List<Attachments> existattachmentlist = new ArrayList<Attachments>();
		if (attachlist.size() > 0) {
			for (Attachments attachmentlist : attachlist) {
				if (attachmentlist.isDelete() != true) {
					existattachmentlist.add(attachmentlist);
				}
			}
			System.out.println(existattachmentlist.size());
			return existattachmentlist;
		} else {
			return null;
		}
	}

	public List<LegalStatus> checkLegalStatusrecordAvilableinDatabaseTable(List<LegalStatus> legalststuslist) {
		List<LegalStatus> existlegalstatuslist = new ArrayList<LegalStatus>();
		if (legalststuslist.size() > 0) {
			for (LegalStatus legal : legalststuslist) {
				if (legal.isDelete() != true) {
					existlegalstatuslist.add(legal);
				}
			}
			return existlegalstatuslist;
		} else {
			return null;
		}
	}

	public List<NatureOfSupplier> CheckNatureOfSupplierRecordAvilableinDatabaseTable(List<NatureOfSupplier> natureoflist) {
		List<NatureOfSupplier> existnatureOfsupplierlist = new ArrayList<NatureOfSupplier>();
		if (natureoflist.size() > 0) {
			for (NatureOfSupplier nature : natureoflist) {
				if (nature.isDelete() != true) {
					existnatureOfsupplierlist.add(nature);
				}
			}
			return existnatureOfsupplierlist;
		} else {
			return null;
		}
	}
	public List<Countries> CheckCountriesRecordAvilableinDatabaseTable(List<Countries>Countrieslist) {
		List<Countries> existCountrieslist = new ArrayList<Countries>();
		if (Countrieslist.size() > 0) {
			for (Countries country : Countrieslist) {
				if (country.isDelete() != true) {
					existCountrieslist.add(country);
				}
			}
			return existCountrieslist;
		} else {
			return null;
		}
	}
}
