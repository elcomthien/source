package com.elcom.ehotel.admin.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encryptor {

	public static String encryptPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			return number.toString(16);
		} catch (java.security.NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String encryptPasswordBySHA(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes());
			byte[] digestedPassword = md.digest();
			return new String(digestedPassword);
		} catch (java.security.NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	protected static SecretKey getSecretKey() throws InvalidKeyException,
			UnsupportedEncodingException, NoSuchAlgorithmException,
			InvalidKeySpecException {
		DESKeySpec keySpec = new DESKeySpec(
				"User Management Project".getBytes("UTF8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		return keyFactory.generateSecret(keySpec);
	}

	protected static String encryptPasswordDES(String password)
			throws InvalidKeyException, UnsupportedEncodingException,
			NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		SecretKey key = getSecretKey();
		BASE64Encoder base64encoder = new BASE64Encoder();

		// ENCODE plainTextPassword String
		byte[] cleartext = password.getBytes("UTF8");

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		String encrypedPwd = base64encoder.encode(cipher.doFinal(cleartext));
		return encrypedPwd;
	}

	protected static String decryptPasswordDES(String encryptedPassword)
			throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException, IOException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		SecretKey key = getSecretKey();
		BASE64Decoder base64decoder = new BASE64Decoder();

		// DECODE encryptedPwd String
		byte[] encrypedPwdBytes = base64decoder.decodeBuffer(encryptedPassword);

		Cipher cipher = Cipher.getInstance("DES");// cipher is not thread safe
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plainTextPwdBytes = (cipher.doFinal(encrypedPwdBytes));

		return new String(plainTextPwdBytes);
	}

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
//		System.err.println(encryptPassword("admin"));
//		System.err.println(encryptPasswordBySHA("admin"));
		System.out.println(encryptPassword("123123"));
//		System.out.println(encryptPasswordBySHA("flcquynhon"));
//		System.out.println(decryptPasswordDES("5f118ae3092171eedf4b33079d6a250f"));
	}
}
