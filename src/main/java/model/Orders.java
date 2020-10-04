package model;

public class Orders {
    private Long id;
    private String text;
    private String comment;

    public Orders(Long id, String text, String comment) {
        this.id = id;
        this.text = text;
        this.comment = comment;
    }

    public Orders(String text, String comment) {
        this.text = text;
        this.comment = comment;
    }

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
