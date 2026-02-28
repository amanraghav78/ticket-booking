package com.ticketbooking.system.util;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class ResponseHandler {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ResponseHandler.class);

    private ResponseHandler(){};

    public static Map<String, Object> generateResponse(String message, HttpStatus status, Boolean success, Object respObj){
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("message", message);
            map.put("status", status);
            map.put("success", success);
            map.put("data", respObj);
            map.put("timeStamp", new Date());
        } catch (Exception e){
            map.clear();
            map.put("message", e.getMessage());
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("timeStamp", new Date());
        }
        return map;
    }

    public static void write(OutputStream outputStream, Object obj){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(outputStream, obj);
        } catch (Exception ie){
            logger.warning("Error in json mapper on response handler class");
        }

    }
}
