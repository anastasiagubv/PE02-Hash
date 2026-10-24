public class Usuari {
    private String userName;
    private String salt;
    private String hash;

    public Usuari(String userName, String salt, String hash) {
        setUserName(userName);
        setSalt(salt);
        setHash(hash);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
