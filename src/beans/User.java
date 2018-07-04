package beans;

public class User {
    private int id;
    private String login;
    private String email;
    private String password;
    private String type;
    private String create_at;
    private String modified_at;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getCreateAt() { return create_at; }

    public void setCreateAt(String create_at) { this.create_at = create_at; }

    public String getModifiedAt() { return modified_at; }

    public void setModifiedAt(String modified_at) { this.modified_at = modified_at; }

    public String toString(){
        return "Je suis " + this.getLogin() + ", " + getEmail() + ", " + getId();
    }
}
