//package com.microservice.ordermanagement.service;
//
//import org.springframework.stereotype.Component;
//
//import feign.Response;
//import feign.codec.ErrorDecoder;
//
////@Component
//public class MyErrorDecoder implements ErrorDecoder {
//
//    private final ErrorDecoder defaultErrorDecoder = new Default();
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        return defaultErrorDecoder.decode(methodKey, response);
//    }
//
//}
