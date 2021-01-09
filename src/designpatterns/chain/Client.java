package designpatterns.chain;

/**
 * @author machenggong
 * @date 2021/1/9
 * @description
 */
public class Client {

    public static void main(String[] args) {
        PurchaseRequest request = new PurchaseRequest(1,1000,1);
        DepartmentApprover departmentApprover = new DepartmentApprover("张主任");
        CollegeApprover collegeApprover = new CollegeApprover("李院长");
        ViceSchoolApprover viceSchoolApprover = new ViceSchoolApprover("王副校长");
        SchoolMasterApprover schoolMasterApprover = new SchoolMasterApprover("童校长");

        //将各个审批级别的下一个人设置好
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(viceSchoolApprover);
        viceSchoolApprover.setApprover(schoolMasterApprover);
        schoolMasterApprover.setApprover(departmentApprover);

        schoolMasterApprover.processRequest(request);

    }

}
