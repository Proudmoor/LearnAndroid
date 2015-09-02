package jni;

/**
 * Created by zpff on 2015/6/12.
 */
public class Natives {
    public static native int LibMain(String[] argv);

    private static void OnMessage(String text, int level){
        System.out.println("OnMessage text:"+text+" level ="+level);
    }
}

