package designpatterns.strategy;

/**
 * Created by machenggong on 2019/12/13.
 */
/**
 *
 *作者：alaric
 *时间：2013-8-5上午11:14:08
 *描述：商户账号充值
 */
public class BusiAcctStrategy implements Strategy{

    @Override
    public Double calRecharge(Double charge, RechargeTypeEnum type) {
        // TODO Auto-generated method stub
        return charge*0.90;
    }

}
