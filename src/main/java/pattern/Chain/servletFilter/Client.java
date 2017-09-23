package pattern.Chain.servletFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * descrption:测试模拟的Filter
 * authohr: wangji
 * date: 2017-09-23 13:31
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        ConcreteFileterA concreteFileterA = new ConcreteFileterA();
        ConcreteFileterB concreteFileterB = new ConcreteFileterB();
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(concreteFileterA)
                    .addFilter(concreteFileterB);

        // 待处理消息
        String msg = "This is an analog filter，my request msg is:wangji is a boy";
        Request request = new Request();
        request.setRequest(msg);

        Response response = new Response();
        response.setResponse("Response");

        filterChain.doFilter(request,response);

        log.info("file end request str:"+request.getRequest());
        log.info("file end reponse str:"+response.getResponse());
    }
}
//2017-09-23 13:37:54,551  INFO [ConcreteFileterA.java:13] : ConcreteFileterA replace  wangji to 汪吉 begin
//2017-09-23 13:37:54,554  INFO [ConcreteFileterB.java:9] : ConcreteFileterB add  String: "add a String"; begin
//2017-09-23 13:37:54,555  INFO [ConcreteFileterB.java:14] : ConcreteFileterB add  String: "add a String"; end
//2017-09-23 13:37:54,555  INFO [ConcreteFileterA.java:18] : ConcreteFileterA replace  wangji to 汪吉 end
//2017-09-23 13:37:54,555  INFO [Client.java:29] : file end request str:This is an analog filter，my request msg is:汪吉 is a boy add a String
//2017-09-23 13:37:54,556  INFO [Client.java:30] : file end reponse str:Response--->add  String: "add a String";--->replace  wangji to 汪吉
