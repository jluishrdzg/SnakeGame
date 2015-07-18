package snakegame;

public class Messages {
    String message;
    int horizontal;
    int vertical;
    
    public Messages(String message, int horizontal) {
        this.message = message;
        this.horizontal = horizontal;
        this.vertical = 400;
    }
    
    public Messages() {
        message = "";
        horizontal = 0;
        vertical = 400;
    } 
}
