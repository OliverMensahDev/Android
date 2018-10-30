package bawo.com.databinding.data;

public class DataSource {
    private String name;
    public static DataSource get(String name){return  new DataSource(name);}

    public  DataSource(String name){this.name = name;}
    public String getMessage(){return String.format("Hello , %s", name);}
}
