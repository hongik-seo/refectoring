package com.company.design;

import com.company.design.adapter.*;
import com.company.design.decorator.*;
import com.company.design.facade.SftpClient;
import com.company.design.observer.Button;
import com.company.design.observer.IButtonListener;
import com.company.design.proxy.AopBrowser;
import com.company.design.proxy.Browser;
import com.company.design.proxy.BrowserProxy;
import com.company.design.proxy.IBrowser;
import com.company.design.singleton.Aclazz;
import com.company.design.singleton.Bclazz;
import com.company.design.singleton.SocketClient;
import com.company.design.strategy.Base64Strategy;
import com.company.design.strategy.Encoder;
import com.company.design.strategy.EncodingStrategy;
import com.company.design.strategy.NormalStrategy;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {

        /* //SingleTon
        Aclazz aclazz = new Aclazz();
        Bclazz bclazz =new Bclazz();

        SocketClient aClient = aclazz.getSocketClient();
        SocketClient bClient = bclazz.getSocketClient();

        System.out.println(aClient.equals(bClient)); // true
        */

        /* //adapter
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Vacuum vacuum  = new Vacuum();
        Electronic110V adapter = new SocketAdapter(vacuum);
        connect(adapter);

        AirConditioner airConditioner = new AirConditioner();
        Electronic110V airAdapter = new SocketAdapter(airConditioner);
        connect(airAdapter);
         */
        /*
        //proxy
        IBrowser browser = new BrowserProxy("www.proxy.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();


        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();
        IBrowser aopBrowser = new AopBrowser(
                "www.naver.com",

                ()->{
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },

                ()->{
                    long now = System.currentTimeMillis();
                    System.out.println("after");
                    end.set(now - start.get() );

                }

        );

        aopBrowser.show();
        System.out.println("loading time:::"+end.get());

        aopBrowser.show();
        System.out.println("loading time:::"+end.get());;
        */

        /* //decorator
        ICar audi = new Audi(1000);
        audi.showPrice();

        ICar audi3 = new A3(audi, "A3");
        audi3.showPrice();

        ICar audi4 = new A4(audi, "A4");
        audi4.showPrice();

        ICar audi5 = new A5(audi, "A5");
        audi5.showPrice();
         */

        /*
        //observer
        Button button = new Button("버튼");

        button.addListener(new IButtonListener() {
            @Override
            public void clickEvent(String event) {
                System.out.println(event);
            }
        });

        button.click("메시지 전달 :: click1");
        button.click("메시지 전달 :: click2");
        button.click("메시지 전달 :: click3");
        button.click("메시지 전달 :: click4");
        */

        /*
        //facade
        SftpClient sftpClient = new SftpClient("www.foo.co.kr", 22, "/home/etc","text.tmp");

        sftpClient.connect();
        sftpClient.read();
        sftpClient.write();
        sftpClient.disConnect();
         */

        Encoder encoder = new Encoder();

        //base64

        EncodingStrategy base64 = new Base64Strategy();

        //normal

        EncodingStrategy normal = new NormalStrategy();

        String message = "hello java";

        encoder.setEncodingStrategy(base64);
        String base64Result = encoder.getMessage(message);

        encoder.setEncodingStrategy(normal);
        String normalResult = encoder.getMessage(message);

        System.out.println("base64::"+base64Result);
        System.out.println("normal::"+normalResult);



    }

    /* adepter
    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }
    */


}
