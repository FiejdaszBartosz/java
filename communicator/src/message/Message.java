package message;

import java.io.Serializable;

public class Message implements Serializable {
    public String name;
    public String message;
    public String toWho;


    public Message(String name, String content, String toWho) {
        this.name = name;
        this.message = content;
        this.toWho = toWho;
    }

    public Message(String name, String content) {
        this.name = name;
        this.message = content;
        this.toWho = "To Everyone";
    }

    public String presentMessage() {
        return this.name + " : " + this.message;
    }


}
