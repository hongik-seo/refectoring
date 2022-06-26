package com.company.design.proxy;

import com.company.design.proxy.Html;
import com.company.design.proxy.IBrowser;

public class AopBrowser implements IBrowser {

    private String url;
    private Html html;
    private Runnable before;
    private Runnable after;

    public AopBrowser(String url, Runnable before, Runnable after){
        this.url = url;
        this.before = before;
        this.after= after;
    }

    @Override
    public Html show() {

        before.run();
        if(html==null){

            System.out.println("AopBrowserProxy loading html From::"+url);
            html =  new Html(url);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        after.run();;
        System.out.println("AopBrowserProxy use cache html From::"+url);
        return html;
    }
}
