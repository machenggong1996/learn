package bishi;

import java.io.IOException;

/**
 * Created by mache on 2019/11/4.
 */
public class JDBishi {

    public static void main(String[] args) throws Exception, CustomException {
        switch (2) {
            default:
                System.out.print(4);
            case 1:
                System.out.print(1);
            case 2:
                System.out.print(2);
            case 3:
                System.out.print(3);
        }

        try{
            throw new Exception("1");
        }catch (IOException e){
            throw new Exception("2");
        }catch (Throwable e) {
            throw new Exception("3");
        }finally {
            throw new CustomException("4");
        }

    }

}
