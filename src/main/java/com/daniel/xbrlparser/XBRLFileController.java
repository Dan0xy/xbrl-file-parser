package com.daniel.xbrlparser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
public class XBRLFileController {

    private static final Logger logger = LoggerFactory.getLogger(XBRLFileController.class);

    private final XBRLParserService xbrlParserService;

    public XBRLFileController(XBRLParserService xbrlParserService) {
        this.xbrlParserService = xbrlParserService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadXBRLFile(@RequestParam("file") MultipartFile file) {
        try {
            File tempFile = File.createTempFile("uploaded", ".html");
            file.transferTo(tempFile);

            Map<String, Object> jsonMap = xbrlParserService.parseXBRLFile(tempFile);

            tempFile.delete();

            return ResponseEntity.status(HttpStatus.OK).body(jsonMap);
        } catch (Exception e) {
            logger.error("Failed to parse XBRL file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to parse XBRL file: " + e.getMessage()));
        }
    }
}