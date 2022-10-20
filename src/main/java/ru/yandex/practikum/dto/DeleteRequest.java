package ru.yandex.practikum.dto;

public class DeleteRequest {

    private String id;

    public DeleteRequest(Integer id) {
        this.id = Integer.toString(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
