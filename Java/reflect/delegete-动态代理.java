/**
 * Man �ӿ���
 */
public interface Man {
    void run();
}

/**
 * RunMan �˶�Ա
 */
public class RunMan implements Man {
    public void run() {
        System.out.println("RunMan running");
    }
}

/**
 * ProxyMan
 * ������
 */
public class ProxyMan implements InvocationHandler {
    private Object proxyed;

    public ProxyMan(Object proxyed) {
        this.proxyed = proxyed;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(proxyed, args);
    }
}

// ----------------- ������

public static void main(String[] args) {
    Man runMan = new RunMan();
    Man proxy = (Man) Proxy.newProxyInstance(Man.class.getClassLoader(),
            new Class[]{Man.class}, new ProxyMan(runMan));

    proxy.run();
}