package designpatterns.strategy;

/**
 * Created by machenggong on 2019/12/13.
 */

/**
 * 作者：alaric
 * 时间：2013-8-5上午11:14:23
 * 描述：网银充值
 */
public class EBankStrategy implements Strategy {

    @Override
    public Double calRecharge(Double charge, RechargeTypeEnum type) {
        return charge * 0.85;
    }
}
