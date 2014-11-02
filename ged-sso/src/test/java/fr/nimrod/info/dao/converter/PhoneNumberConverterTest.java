package fr.nimrod.info.dao.converter;

import org.junit.Assert;
import org.junit.Test;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneNumberConverterTest {

    private PhoneNumberConverter instanceUnderTest = new PhoneNumberConverter();
    private PhoneNumber validPhoneNumber;
    private String validPhoneNumberConverted = "06 98 86 74 54";
    private PhoneNumber invalidPhoneNumber;
    private String invalidPhoneNumberConverted = "040506";
    
    {
        try {
            validPhoneNumber = PhoneNumberUtil.getInstance().parse(validPhoneNumberConverted, "FR");
            invalidPhoneNumber = PhoneNumberUtil.getInstance().parse(invalidPhoneNumberConverted, "FR");
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void phoneNumberToString() {
        
        String convertPhoneNumber = instanceUnderTest.convertToDatabaseColumn(validPhoneNumber);
        Assert.assertEquals("numero valide", convertPhoneNumber, validPhoneNumberConverted);
        String convertInvalidPhoneNumber = instanceUnderTest.convertToDatabaseColumn(invalidPhoneNumber);
        Assert.assertNull("numero invalide", convertInvalidPhoneNumber);
    }
    
    @Test
    public void stringToPhoneNumber() {
        PhoneNumber convertValidString = instanceUnderTest.convertToEntityAttribute(validPhoneNumberConverted);
        Assert.assertEquals("numero valide", convertValidString, validPhoneNumber);
        PhoneNumber convertInvalidString = instanceUnderTest.convertToEntityAttribute(invalidPhoneNumberConverted);
        Assert.assertNull("numero invalide", convertInvalidString);
    }
}
