package com.expressflow.security;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.servlet.ServletContext;

import com.google.gdata.util.common.util.Base64;

public class KeyHelper {
	public static PrivateKey getPrivateKey(String privKeyFileName)
			throws Exception {
		FileInputStream fis = new FileInputStream(new File(privKeyFileName));
		DataInputStream dis = new DataInputStream(fis);

		byte[] privKeyBytes = new byte[(int) new File(privKeyFileName).length()];
		dis.read(privKeyBytes);
		dis.close();
		fis.close();

		String BEGIN = "-----BEGIN PRIVATE KEY-----";
		String END = "-----END PRIVATE KEY-----";
		String str = new String(privKeyBytes);
		if (str.contains(BEGIN) && str.contains(END)) {
			str = str.substring(BEGIN.length(), str.lastIndexOf(END));
		}

		KeyFactory fac = KeyFactory.getInstance("RSA");
		EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(Base64.decode(str));
		return fac.generatePrivate(privKeySpec);
	}
}
