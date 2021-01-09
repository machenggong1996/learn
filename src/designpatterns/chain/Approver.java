package designpatterns.chain;

/**
 * @author machenggong
 * @date 2021/1/9
 * @description
 */
public abstract class Approver {

    /**
     * 下一个处理着
     */
    protected Approver approver;

    /**
     * 名字
     */
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    /**
     * 处理审批请求的方法 得到一个请求 处理是子类完成
     * @param request
     */
    public abstract void processRequest(PurchaseRequest request);

    /**
     * 下一个处理着
     * @param approver
     */
    public void setApprover(Approver approver) {
        this.approver = approver;
    }
}
