package beans;

public class Url {
    private int id;
    private int user_id;
    private String absolute;
    private String base;
    private String shortcut;
    private String starting_date;
    private String ending_date;
    private int max_clic;
    private int captcha;
    private String create_at;
    private String password;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getUserId() { return user_id; }

    public void setUserId(int user_id) { this.user_id = user_id; }

    public String getBase() { return base; }

    public void setBase(String base) { this.base = base; }

    public String getAbsolute() { return absolute; }

    public void setAbsolute(String absolute) { this.absolute = absolute; }

    public String getShortcut() { return shortcut; }

    public void setShortcut(String shortcut) { this.shortcut = shortcut; }

    public String getStartingDate() { return starting_date; }

    public void setStartingDate(String starting_date) { this.starting_date = starting_date; }

    public String getEndingDate() { return ending_date; }

    public void setEndingDate(String ending_date) { this.ending_date = ending_date; }

    public int getMaxClic() { return max_clic; }

    public void setMaxClic(int max_clic) { this.max_clic = max_clic; }

    public int getCaptcha() { return captcha; }

    public void setCaptcha(int captcha) { this.captcha = captcha; }


    public String getCreateAt() { return create_at; }

    public void setCreateAt(String create_at) { this.create_at = create_at; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
