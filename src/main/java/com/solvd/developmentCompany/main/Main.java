package com.solvd.developmentCompany.main;

import com.solvd.developmentCompany.models.inventory.Machines;
import com.solvd.developmentCompany.models.inventory.MachinesListWrapper;
import com.solvd.developmentCompany.utils.JaxbParserUtil;
import com.solvd.developmentCompany.utils.StaxParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("The Development Company application is starting...");

        try {
            logger.debug("Attempting to connect to the Hikari Connection Pool");
        } catch (Exception e) {
            logger.error("Database connection failed!", e);
        }

        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("machines.xml")) {

            if (is == null) {
                logger.error("Could not find 'machines.xml' in the resources folder.");
                return;
            }


            logger.info("Parsing machines.xml using StAX...");
            List<Machines> parsedMachines = StaxParserUtil.parseMachines(is);


            if (parsedMachines.isEmpty()) {
                logger.warn("No machines were found in the XML file.");
            } else {
                logger.info("Successfully parsed {} machines from XML.", parsedMachines.size());

                parsedMachines.forEach(m ->
                        logger.info("Parsed Machine -> ID: {}, Name: {}, Serial: {}",
                                m.getId(), m.getMachineName(), m.getSerialNumber())
                );
            }

        } catch (Exception e) {
            logger.error("An unexpected error occurred during execution", e);
        }

        logger.info("Application execution finished.");

        try {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("machines.xml");
            MachinesListWrapper wrapper = JaxbParserUtil.unmarshal(is, MachinesListWrapper.class);

            for (Machines m : wrapper.getMachines()) {
                logger.info("JAXB Parsed: {}", m.getMachineName());
            }
        } catch (Exception e) {
            logger.error("Failed to parse XML using JAXB: ", e);
        }

        try {
            Machines newMachine = new Machines();
            newMachine.setId(999L);
            newMachine.setMachineName("Giant Crane");
            newMachine.setSerialNumber("CRANE-001");

            MachinesListWrapper wrapper = new MachinesListWrapper();
            wrapper.setMachines(List.of(newMachine));

            File outputFile = new File("src/main/resources/exported_machines.xml");

            logger.info("Marshalling Java objects to XML...");
            JaxbParserUtil.marshal(wrapper, outputFile);
            logger.info("XML file created at: {}", outputFile.getAbsolutePath());

        } catch (Exception e) {
            logger.error("Marshalling failed: ", e);
        }
    }


}