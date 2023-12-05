package korolev;

/**
 * Class for registering users.
 * @author Nikita Korolev
 * @version 1.0
 */
public class Users {
    private boolean STATUS;
    private String login;
    private int ID;

    public Users(boolean s) {
        this.STATUS = s;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSTATUS(boolean s) {
        this.STATUS = s;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public boolean isSTATUS() {
        return STATUS;
    }

    public String getLogin() {
        return login;
    }

    public int getID() {
        return ID;
    }

}
