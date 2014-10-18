package fr.nimrod.info.trace.validator.common;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;

public class TimeStampValidatorTest {

    private TimeStampValidator validator = new TimeStampValidator() {};
    
    @Test
    public void timeStampValidatorSimpleTest(){
        ChronoLocalDateTime<?> chronos = LocalDateTime.now();
        String chronosString = chronos.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"));
        
        Assert.assertEquals("Transformation en string", "[" + chronosString + "]", validator.validateAndTransform(chronos));
        Assert.assertEquals("Transformation en date", chronos, validator.logToObject(chronosString));
    }
}
