package com.solvd.developmentCompany.utils;

import com.solvd.developmentCompany.models.inventory.Machines;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StaxParserUtil {

    public static List<Machines> parseMachines(InputStream inputStream) {
        List<Machines> machinesList = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();

        Long id = null, contractorId = null, currentProjectId = null;
        String machineName = null, plateNumber = null, serialNumber = null, value = null;
        boolean isCompanyProperty = false, isAssigned = false;

        try {
            XMLEventReader eventReader = factory.createXMLEventReader(inputStream);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String tagName = startElement.getName().getLocalPart();

                    switch (tagName) {
                        case "machine":
                            id = Long.parseLong(startElement.getAttributeByName(new QName("id")).getValue());
                            break;
                        case "machineName":
                            machineName = eventReader.nextEvent().asCharacters().getData();
                            break;
                        case "contractorId":
                            contractorId = Long.parseLong(eventReader.nextEvent().asCharacters().getData());
                            break;
                        case "currentProjectId":
                            currentProjectId = Long.parseLong(eventReader.nextEvent().asCharacters().getData());
                            break;
                        case "isCompanyProperty":
                            isCompanyProperty = Boolean.parseBoolean(eventReader.nextEvent().asCharacters().getData());
                            break;
                        case "isAssigned":
                            isAssigned = Boolean.parseBoolean(eventReader.nextEvent().asCharacters().getData());
                            break;
                        case "plateNumber":
                            plateNumber = eventReader.nextEvent().asCharacters().getData();
                            break;
                        case "serialNumber":
                            serialNumber = eventReader.nextEvent().asCharacters().getData();
                            break;
                        case "value":
                            value = eventReader.nextEvent().asCharacters().getData();
                            break;
                    }
                }


                if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("machine")) {
                    machinesList.add(new Machines(
                            id, machineName, contractorId, currentProjectId,
                            isCompanyProperty, isAssigned, plateNumber, serialNumber, value
                    ));
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return machinesList;
    }
}