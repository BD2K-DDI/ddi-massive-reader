package uk.ac.ebi.ddi.massive.extws.massive.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 08/11/15
 */
public class CharsetPostProcessor extends StringHttpMessageConverter {

    public CharsetPostProcessor(){

        List<MediaType> types = Arrays.asList(
                new MediaType("text", "html", DEFAULT_CHARSET),
                new MediaType("application", "json", DEFAULT_CHARSET),
                new MediaType("application", "*+json", DEFAULT_CHARSET),
                new MediaType("application", "octet-stream", DEFAULT_CHARSET),
                new MediaType("text", "html", Charset.forName("ISO-8859-1"))
        );
        super.setSupportedMediaTypes(types);

    }


}