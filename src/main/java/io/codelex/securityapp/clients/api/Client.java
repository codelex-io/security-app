package io.codelex.securityapp.clients.api;

public class Client {

    private long id;


    public Client(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
