package com.selectquotereview.twitservice;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class OAuthConst {
	private static final String BUNDLE_NAME = "com.selectquotereview.twitservice.oauth"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private OAuthConst() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
