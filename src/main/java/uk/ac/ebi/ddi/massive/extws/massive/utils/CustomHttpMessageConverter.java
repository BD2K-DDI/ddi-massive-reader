package uk.ac.ebi.ddi.mw.extws.mw.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;


import java.io.IOException;
import java.io.InputStream;


/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 19/05/2015
 */

public class CustomHttpMessageConverter extends AbstractJackson2HttpMessageConverter {


    protected CustomHttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        InputStream istream = inputMessage.getBody();
        String responseString = IOUtils.toString(istream);

        if(responseString.isEmpty())
            return null;
        JavaType javaType = getJavaType(null, clazz);
        try {
            return this.objectMapper.readValue(responseString, javaType);
        } catch (Exception ex) {
            throw new HttpMessageNotReadableException(responseString);
        }
    }
}
