package fr.nimrod.info.crypto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Issuer {

    private String countryName;
    
    private String organizationName;
    
    private String organizationalUnitName;

    @Override
    public String toString() {
        return "C=" + countryName + ", O=" + organizationName
                + ", OU=" + organizationalUnitName + "]";
    }
    
    
}
