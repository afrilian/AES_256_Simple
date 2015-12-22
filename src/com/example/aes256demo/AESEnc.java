package com.example.aes256demo;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESEnc {

	private String mode = "AES/ECB/PKCS5Padding";
	private final String KEY_ALGORITHM = "AES";
	private SecretKey aesKey;
	private byte[] key;

	public AESEnc() {
		
		key = generateAesKey();		
		aesKey = new SecretKeySpec(key, KEY_ALGORITHM);
	}

	public AESEnc(byte[] k) {
		
		aesKey = new SecretKeySpec(k, KEY_ALGORITHM);
	}
	
	private byte[] generateAesKey(){


		KeyGenerator kgen = null;
		try {
			kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kgen.init(256);		
		SecretKey secretKey = kgen.generateKey();		
		
		return secretKey.getEncoded();
	}

	public byte[] encrypt(byte[] src) throws Exception {

		try {
			Cipher c = Cipher.getInstance(mode);
			c.init(Cipher.ENCRYPT_MODE, aesKey);

			return c.doFinal(src);
		} catch (Exception ex) {
			throw ex;
		}
	}

	public byte[] decrypt(byte[] src) throws Exception {

		try {
			Cipher c = Cipher.getInstance(mode);
			c.init(Cipher.DECRYPT_MODE, aesKey);
			
			return c.doFinal(src);
		} catch (Exception ex) {
			throw ex;
		}
	}
	


	public byte[] getKey() {
		return key;
	}

}