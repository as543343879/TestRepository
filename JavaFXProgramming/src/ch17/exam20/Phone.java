package ch17.exam20;
public class Phone {
    
   private String image;
   private String name;
   private String content;


   
   public void Phone(){
       
   }

    public Phone(String image, String name,String content) {
        this.image = image;
        this.name = name;
        this.content=content;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
