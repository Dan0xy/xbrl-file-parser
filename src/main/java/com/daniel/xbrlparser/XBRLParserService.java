package com.daniel.xbrlparser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class XBRLParserService {

    private static final Logger logger = LoggerFactory.getLogger(XBRLParserService.class);

    /**
     * Parses an XBRL file to extract nonNumeric and nonFraction elements, performs financial calculations,
     * and returns a map containing the extracted elements and calculated values.
     *
     * @param  file  the XBRL file to parse
     * @return       a map containing extracted elements and calculated values
     */
    public Map<String, Object> parseXBRLFile(File file) throws Exception {
        logger.info("Starting to parse XBRL file: {}", file.getAbsolutePath());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        Map<String, Object> jsonMap = new HashMap<>();

        // Parse ix:nonNumeric elements
        NodeList nonNumericNodes = document.getElementsByTagName("ix:nonNumeric");
        parseElements(jsonMap, nonNumericNodes);

        // Parse ix:nonFraction elements
        NodeList nonFractionNodes = document.getElementsByTagName("ix:nonFraction");
        parseElements(jsonMap, nonFractionNodes);

        // Perform financial calculations
        Map<String, Object> calculations = performCalculations(jsonMap);

        logger.info("Finished parsing XBRL file: {}", file.getAbsolutePath());
        jsonMap.putAll(calculations);
        return jsonMap;
    }

    protected void parseElements(Map<String, Object> jsonMap, NodeList nonNumericNodes) {
        for (int i = 0; i < nonNumericNodes.getLength(); i++) {
            Node node = nonNumericNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getAttribute("name");
                String value = element.getTextContent();
                jsonMap.put(name, value);
            }
        }
    }

    /**
     * Performs calculations on the given JSON map and returns a map of calculated values.
     *
     * @param  jsonMap  a map containing financial data in JSON format
     * @return          a map of calculated values including Total Assets, Total Liabilities, Cash in Bank, Net Assets, and Debt Ratio
     */
    private Map<String, Object> performCalculations(Map<String, Object> jsonMap) {
        Map<String, Object> calculations = new HashMap<>();

        double fixedAssets = parseDouble(jsonMap.get("core:FixedAssets"));
        double investmentProperty = parseDouble(jsonMap.get("core:InvestmentProperty"));
        double currentAssets = parseDouble(jsonMap.get("core:CurrentAssets"));
        double tradeCreditors = parseDouble(jsonMap.get("core:TradeCreditorsTradePayables"));
        double accruedLiabilities = parseDouble(jsonMap.get("core:AccruedLiabilitiesDeferredIncome"));
        double otherCreditors = parseDouble(jsonMap.get("core:OtherCreditors"));
        double amountsOwedToGroup = parseDouble(jsonMap.get("core:AmountsOwedToGroupUndertakings"));
        double otherBorrowings = parseDouble(jsonMap.get("core:OtherRemainingBorrowings"));
        double cashInBank = parseDouble(jsonMap.get("core:CashBankOnHand"));

        // Calculate Total Assets
        double totalAssets = fixedAssets + investmentProperty + currentAssets;
        calculations.put("TotalAssets", totalAssets);
        logger.info("Total Assets: {}" , totalAssets);
        // Calculate Total Liabilities
        double totalLiabilities = tradeCreditors + accruedLiabilities + otherCreditors + amountsOwedToGroup + otherBorrowings;
        calculations.put("TotalLiabilities", totalLiabilities);
        logger.info("Total Liabilities: {}", totalLiabilities);
        // Cash in Bank
        calculations.put("CashInBank", cashInBank);
        logger.info("Cash in Bank: {} " , cashInBank);
        // Calculate Net Assets
        double netAssets = totalAssets - totalLiabilities;
        calculations.put("NetAssets", netAssets);
       logger.info("Net Assets: {}" , netAssets);
        // Calculate Debt Ratio
        double debtRatio = (totalLiabilities / totalAssets) * 100;
        calculations.put("DebtRatio", debtRatio);
        logger.info("Debt Ratio: {} " ,debtRatio);
        return calculations;
    }

    private double parseDouble(Object value) {
        if (value != null) {
            try {
                return Double.parseDouble(value.toString().replace(",", ""));
            } catch (NumberFormatException e) {
                logger.error("Error parsing value: {}", value, e);
            }
        }
        return 0.0;
    }
}