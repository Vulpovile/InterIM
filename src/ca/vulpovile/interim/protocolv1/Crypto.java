package ca.vulpovile.interim.protocolv1;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;

public class Crypto {
	public KeyPair getKeyPair() throws NoSuchAlgorithmException
	{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		SecureRandom secureRandom = new SecureRandom();
		keyPairGenerator.initialize(1024, secureRandom);
		return keyPairGenerator.genKeyPair();
	}
	public void decrypt(byte[] input, PrivateKey key)
	{
		
	}
}

