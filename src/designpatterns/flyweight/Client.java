package designpatterns.flyweight;

/**
 * @author machenggong
 * @date 2020/12/27
 * @description
 */
public class Client {

    public static void main(String[] args) {
        WebSiteFactory factory = new WebSiteFactory();
        WebSite webSite1 = factory.getWebSiteFactory("新闻");
        webSite1.use(new User("tom"));
        WebSite webSite2 = factory.getWebSiteFactory("博客");
        webSite2.use(new User("jack"));
        WebSite webSite3 = factory.getWebSiteFactory("博客");
        webSite3.use(new User("king"));
    }

}
