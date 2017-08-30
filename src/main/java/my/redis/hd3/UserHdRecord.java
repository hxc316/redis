package my.redis.hd3;

/**
 * Created by xc on 2017/8/30.
 */
public class UserHdRecord {
    private Integer id;
    private String name;
    private String price;

    public UserHdRecord(Integer id,String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
