package beans;

public class Url {
    private int id;
    private int user_id;
    private String base;
    private String shortcut;
    private String starting_date;
    private String create_at;
    private String password;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getUserId() { return user_id; }

    public void setUserId(int user_id) { this.user_id = user_id; }

    public String getBase() { return base; }

    public void setBase(String base) { this.base = base; }

    public String getShortcut() { return shortcut; }

    public void setShortcut(String shortcut) { this.shortcut = shortcut; }

    public String getStartingDate() { return starting_date; }

    public void setStartingDate(String starting_date) { this.starting_date = starting_date; }

    public String getCreateAt() { return create_at; }

    public void setCreateAt(String create_at) { this.create_at = create_at; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
