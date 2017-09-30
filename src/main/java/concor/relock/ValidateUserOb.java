package concor.relock;

/**
 * Created by xc on 2017/9/30.
 */
public class ValidateUserOb {
    public Integer userId;
    public String userName;

    public volatile StringBuilder msg = new StringBuilder();
    public volatile boolean check = true;
}
