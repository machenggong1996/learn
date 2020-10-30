package thread.threadlocal;

public class TestContext {

    private static ThreadLocal<TestContext> localContext = new ThreadLocal<>();

    private String val;

    public static void start() {
        TestContext context = new TestContext();
        localContext.set(context);
    }

    public static TestContext get() {
        return localContext.get();
    }

    public static void remove() {
        localContext.remove();
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}