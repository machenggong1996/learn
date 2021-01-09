package designpatterns.chain;

/**
 * @author machenggong
 * @date 2021/1/9
 * @description
 */
public class DepartmentApprover extends Approver {


    public DepartmentApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getPrice() <= 5000) {
            System.out.println("请求编号=" + request.getId() + "被" + this.name + "处理");
        } else {
            approver.processRequest(request);
        }
    }
}
