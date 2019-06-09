package com.zhongqihong.client.view;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
 
/**
 * 
 *
 */
public class AES {
 
	/**
	 * 加密字符串
	 * 
	 * @param content 需要加密的内容
	 * @param password 密码
	 * @return
	 */
	static String cKey = "00b09e37363e596e1f25b23c78e49939238b";
	public static byte[] encrypt(String content, String sKey) {
		try {
			// 初始化 加密器
			Cipher cipher = initAESCipher(sKey, Cipher.ENCRYPT_MODE);
			byte[] byteContent = content.getBytes("utf-8");
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
 
	/**
	 * 解密字符串
	 * 
	 * @param content 
	 * @param password
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String sKey) {
		try {
			// 初始化 加密器
			Cipher cipher = initAESCipher(sKey, Cipher.DECRYPT_MODE);
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
 
	/**
	 * 对文件进行AES加密
	 * 
	 * @param sourceFile
	 * @param fileType
	 * @param sKey
	 * @return
	 */
	public static File encryptFile(File sourceFile, File encrypfile) {
 
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(encrypfile);
			Cipher cipher = initAESCipher(cKey, Cipher.ENCRYPT_MODE);
			// 以加密流写入文件
			CipherInputStream cipherInputStream = new CipherInputStream(
					inputStream, cipher);
			byte[] cache = new byte[1024];
			int nRead = 0;
			while ((nRead = cipherInputStream.read(cache)) != -1) {
				outputStream.write(cache, 0, nRead);
				outputStream.flush();
			}
			cipherInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}
		}
		return encrypfile;
	}
 
 
	/**
	 *  AES方式解密文件
	 * @param sourceFile 要解密的文件路径
	 * @param decryptFile 解密后的文件路径
	 * @param sKey
	 * @return
	 */
	public static File decryptFile(File sourceFile, File decryptFile) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			Cipher cipher = initAESCipher(cKey, Cipher.DECRYPT_MODE);
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(decryptFile);
			CipherOutputStream cipherOutputStream = new CipherOutputStream(
					outputStream, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = inputStream.read(buffer)) >= 0) {
				cipherOutputStream.write(buffer, 0, r);
			}
			cipherOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}
		}
		return decryptFile;
	}
 
	/**
	 * 初始化 AES Cipher
	 * 
	 * @param sKey
	 * @param cipherMode
	 * @return
	 */
	public static Cipher initAESCipher(String sKey, int cipherMode) {
		// 创建Key gen
		KeyGenerator keyGenerator = null;
		Cipher cipher = null;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128, new SecureRandom(sKey.getBytes()));
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] codeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(codeFormat, "AES");
			cipher = Cipher.getInstance("AES");
			// 初始化
			cipher.init(cipherMode, key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (NoSuchPaddingException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (InvalidKeyException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		return cipher;
	}
 
//	public static void main(String[] args) throws Exception {
//		
//		
//		//未加密文件路径
//		File oldfile = new File("E:\\mimaxue\\socket\\entry_GUI\\src\\easy_chatting_sys\\src.txt");
//		//加密后的文件路径
//		File encrypfile = new File("E:\\mimaxue\\socket\\entry_GUI\\src\\easy_chatting_sys\\enc.txt");
//		//解密后的文件路径
//		File decrypfile  = new File("E:\\mimaxue\\socket\\entry_GUI\\src\\easy_chatting_sys\\dec.txt");
//		//加密文件
//		encryptFile(oldfile,encrypfile);
//		//解密文件
//		decryptFile(encrypfile, decrypfile);
// 
//	}
}