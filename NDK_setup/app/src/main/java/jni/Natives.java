package jni;

/**
 * Created by pengfei on 15-6-14.
 */
public class Natives {
    public static native int LibMain(String[] argv);

    @SuppressWarnings("unused")
    private static void OnMessage(String text, int level){
        System.out.println("OnMessage text:"+text+" Level ="+level);
    }
}

