package fr.nimrod.info.dao.converter;

import javax.persistence.AttributeConverter;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {

    @Override
    public String convertToDatabaseColumn(PhoneNumber entityValue) {
        if(PhoneNumberUtil.getInstance().isValidNumber(entityValue)){
            return PhoneNumberUtil.getInstance().format(entityValue, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
        }
        return null;
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String databaseValue) {
        try {
            PhoneNumber phoneNumber = PhoneNumberUtil.getInstance().parse(databaseValue, "FR");
            if(PhoneNumberUtil.getInstance().isValidNumber(phoneNumber)){
                return phoneNumber;
            }
        } catch (NumberParseException e) {
            return null;
        }
        
        return null;
        
    }

}
