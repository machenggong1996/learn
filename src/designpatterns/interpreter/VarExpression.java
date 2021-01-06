package designpatterns.interpreter;

import java.util.HashMap;

/**
 * @author machenggong
 * @date 2021/1/5
 * @description 变量的解析器
 */
public class VarExpression extends Expression {

    private final String key;//key=a,key=b,key=c

    public VarExpression(String key) {
        this.key = key;
    }

    /**
     * 通过变量名称返回值
     * @param var
     * @return
     */
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(key);
    }
}
