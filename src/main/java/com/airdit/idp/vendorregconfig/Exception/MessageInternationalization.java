package com.airdit.idp.vendorregconfig.Exception;

import java.util.Locale;
import java.util.ResourceBundle;

//@Component
public class MessageInternationalization {

	public ResourceBundle getResourceBundel() {

		// ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle", Locale.US);
		// changing the default locale to indonasian
		Locale.setDefault(new Locale("in", "IN"));
		ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle_in_IN");
		System.out.println("Message in " + Locale.getDefault() + ":" + bundle.getString("greeting"));
		return bundle;
	}

}
