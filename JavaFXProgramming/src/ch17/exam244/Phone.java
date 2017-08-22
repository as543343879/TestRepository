
package ch17.exam244;

public class Phone {
    private String image;
    private String name;
    private String content;
    
    public Phone() {}

    public Phone(String image, String name, String content) {
        this.image = image;
        this.name = name;
        this.content = content;
    }
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    

}
