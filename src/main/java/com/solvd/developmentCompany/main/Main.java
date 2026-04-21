package com.solvd.developmentCompany.main;

import com.solvd.developmentCompany.models.inventory.Machines;
import com.solvd.developmentCompany.utils.StaxParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

            // 2. Parse the data using Stax
            logger.info("Parsing machines.xml using StAX...");
            List<Machines> parsedMachines = StaxParserUtil.parseMachines(is);

            // 3. Log the results
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
    }
}