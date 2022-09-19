package invokedynamic;

import java.util.stream.IntStream;

/**
 * @author machenggong
 * @since 2022/9/15
 */
public class LambdaTest {

    public static void main(String[] args) {
        IntStream.of(1,2,3).map(i->i*2);
    }

}
