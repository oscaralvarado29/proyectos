package com.medicine.register.infraestructure.util;

import com.medicine.register.infraestructure.config.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
public class Encryption {
    //@Value("${alias}")
    protected String alias="registro";
    //@Value("${password}")
    protected String keystorePassword="#RegistroStore4620";
    //@Value("${path}")
    protected String keystorePath="E:\\proyectos\\Desarrollo Software\\sistema_medico\\Backend\\Registro\\src\\main\\resources\\registro.jks";
    //@Value("${privateKey.password}")
    protected String privateKeyPassword="#KeyRegistro8426";
    private Configuration config;
    protected PublicKey getPublicKey() {
       try (FileInputStream fis = new FileInputStream(keystorePath)){
            KeyStore keyStore = KeyStore.getInstance(Constants.TYPE_KEYSTORE);
            keyStore.load(fis, keystorePassword.toCharArray());
            Certificate cert = keyStore.getCertificate(alias);
            return cert.getPublicKey();
        } catch (Exception e) {
            log.error(e.getMessage(),e.getCause());
            return null;
        }
    }

    protected PrivateKey getPrivateKey() {
        try (FileInputStream fis = new FileInputStream(keystorePath)) {
            KeyStore keyStore = KeyStore.getInstance(Constants.TYPE_KEYSTORE);
            keyStore.load(fis, keystorePassword.toCharArray());

            KeyStore.PasswordProtection keyPassword = new KeyStore.PasswordProtection(privateKeyPassword.toCharArray());
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, keyPassword);

            return privateKeyEntry.getPrivateKey();
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            return null;
        }
    }
    public String encryptWithPublicKey(String text) {
        PublicKey publicKey = getPublicKey();
        try {
            if (publicKey != null) {
                Cipher cipher = Cipher.getInstance(Constants.TRANSFORMATION);
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);

                byte[] textoCifrado = cipher.doFinal(text.getBytes());
                return Base64.getEncoder().encodeToString(textoCifrado);
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(),e.getCause());
            return null;
        }
    }

    public String desencryptWithPrivateKey(String textoCifrado) {
        PrivateKey privateKey = getPrivateKey();
        try {
            Cipher cipher = Cipher.getInstance(Constants.TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] cipherTextBytes = Base64.getDecoder().decode(textoCifrado);
            byte[] decryptedTextBytes = cipher.doFinal(cipherTextBytes);

            return new String(decryptedTextBytes);
        } catch (Exception e) {
            log.error(e.getMessage(),e.getCause());
            return null;
        }
    }
}
