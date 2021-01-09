package designpatterns.chain;

/**
 * @author machenggong
 * @date 2021/1/9
 * @description
 */
public class ViceSchoolApprover extends Approver{


    public ViceSchoolApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getPrice() <= 30000 && request.getPrice() > 10000) {
            System.out.println("请求编号=" + request.getId() + "被" + this.name + "处理");
        } else {
            approver.processRequest(request);
        }
    }
}
