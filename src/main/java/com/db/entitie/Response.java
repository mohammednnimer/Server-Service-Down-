package com.db.entitie;
public class Response {
    private jakarta.ws.rs.core.Response.Status Status;
    private String ErrorMsg;

    public Response() {
    }

    public Response(jakarta.ws.rs.core.Response.Status Status, String msg) {
        this.Status = Status;
        this.ErrorMsg = msg;
    }

    public jakarta.ws.rs.core.Response.Status getStatus() {
        return Status;
    }

    public void setStatus(jakarta.ws.rs.core.Response.Status Status) {
        this.Status = Status;
    }

    public String getMsg() {
        return ErrorMsg;
    }

   public void setMsg(String msg) {
        this.ErrorMsg = msg;
    }
}
