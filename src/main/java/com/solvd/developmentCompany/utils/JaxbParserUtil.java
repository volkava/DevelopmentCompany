package com.solvd.developmentCompany.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

public class JaxbParserUtil {

    public static <T> T unmarshal(InputStream is, Class<T> clazz) throws Exception {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(is);
    }

    public static void marshal(Object object, File file) throws Exception {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(object, file);
    }
}
