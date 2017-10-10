package retry;

/**
 * Created by xc on 2017/10/10.
 */
public class Try {

    public static void main(String[] args) {
        retry:
        for(int i=0;i<10;i++){
            while ( i == 5){
                continue retry;
            }
            System.out.println("i="+i);
        }
    }
}
