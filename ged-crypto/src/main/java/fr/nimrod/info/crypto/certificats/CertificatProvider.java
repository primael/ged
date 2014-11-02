package fr.nimrod.info.crypto.certificats;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v1CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v1CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

public enum CertificatProvider {

    INSTANCE;
    
    public Certificate createMasterCert(KeyPair keyPair) throws OperatorCreationException, CertificateException{
        X509v1CertificateBuilder certificateBuilder = new JcaX509v1CertificateBuilder( //
                new X500Name("CN=Storage Certificate"), //
                BigInteger.valueOf(System.currentTimeMillis()), //
                Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()), //
                Date.from(LocalDateTime.parse("01/01/2100", DateTimeFormatter.ofPattern("dd/mm/YYYY")).atZone(ZoneId.systemDefault()).toInstant()), //
                new X500Name("CN=Storage Certificate"), //
                keyPair.getPublic());
        
        JcaContentSignerBuilder contentSignerBuilder = new JcaContentSignerBuilder("SHA256withRSA");
        ContentSigner contentSigner = contentSignerBuilder.build(keyPair.getPrivate());
        
        X509CertificateHolder certificateHolder = certificateBuilder.build(contentSigner);
        
        JcaX509CertificateConverter certificateConverter = new JcaX509CertificateConverter();
        return certificateConverter.getCertificate(certificateHolder);
    }
    
    
    
    
}
