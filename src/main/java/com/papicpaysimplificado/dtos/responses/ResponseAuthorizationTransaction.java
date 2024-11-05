package com.papicpaysimplificado.dtos.responses;


public class ResponseAuthorizationTransaction {

    public String status;
    public Data data;

    public static class Data {
        public boolean authorization;
    }
}
