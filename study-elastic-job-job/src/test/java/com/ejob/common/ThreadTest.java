package com.ejob.common;

/**
 * Created by wanghongxing on 16/2/23.
 */
public class ThreadTest {

    public static void main(String[] args){

        new Thread(() -> {

            for(int i=1;i<=100;i++){

                System.out.println("THREAD VALUE AFTER 1 SECOND IS: "+i);

                try{
                    if(i==3){
                        Thread.currentThread().interrupt();
                        break;
                    }
                    Thread.currentThread().sleep(1000);
                } catch(final Exception e){
                    e.printStackTrace();
                    if(e instanceof InterruptedException) {

                        // just in case this Runnable is actually called directly,
                        // rather than in a new thread, don't want to swallow the
                        // flag:
                        Thread.currentThread().interrupt();
                    }
                    return;
                }
            }

        }).start();


    }
}
