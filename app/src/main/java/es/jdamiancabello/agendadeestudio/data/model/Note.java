package es.jdamiancabello.agendadeestudio.data.model;

import java.time.LocalDateTime;

public class Note {
    private String title;
    private String content;
    private LocalDateTime date;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
