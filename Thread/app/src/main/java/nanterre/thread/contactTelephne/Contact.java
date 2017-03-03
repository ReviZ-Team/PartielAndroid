package nanterre.thread.contactTelephne;

/**
 * Created by adrie_000 on 03/02/2017.
 */
public class Contact {

    private String num;
    private String name;

    public Contact(String num,String name) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
