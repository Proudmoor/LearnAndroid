#include <stdio.h>
#include <stdlib.h>

#include <jni.h>

#include "include/jni_Native.h"
#define CB_CLASS "jni/Natives"

#define CB_CLASS_MSG_CB "OnMessage"
#define CB_CLASS_MSG_SIG "(Ljava/lang/String;I)V"

int lib_main(int argc, char** argv);

const int getArrayLen(JNIEnv *env, jobjectArray jarray);

void jni_printf(char *format, ...);

static jclass jNativesCls;

static JavaVM *g_VM;

JNIEXPORT jint JNICALL Java_jni_Natives_LibMain
(JNIEnv *env, jclass class, jobjectArray jargv){
    (*env)->GetJavaVM(env, &g_VM);

    jsize clen = getArrayLen(env, jargv);

    char *args[(int)clen];

   int i;
   jstring jrow;
   for(i = 0; i < clen; i++){
    jrow = (jstring) (*env)->GetObjectArrayElemnt(env, jargv, i);
    const char *row = (*env) -> GetStringUTFChars(env, jrow, 0);

    args[i] = malloc(strlen(row) +1);
    strcpy(args[i],row);

    jni_printf("Main argv[%d] = %s", i, args[i]);

    (*env)->ReleaseStringUTFChars(env, jrow, row);
   }

   jNativesCls = (*env) -> FindClass(env, CB_CLASS);
   if(jNativesCls == 0){
    jni_printf("Unable to find class: %s", CB_CLASS);
    return -1;
   }

   lib_main(clen, args);
   return 0;

}

jmethodID mSendStr;

static void jni_send_str(const char *text, int level){
    JNIEnv *env;

    if(!g_VM){
        printf("I_JNI_NOVM: %s\n", text);
        return;
    }
    (*g_VM)->AttachCurrentThread(g_VM, (void **) &env, NULL);

    if(!jNativesCls){
        jNativesCls = (*env)->FindClass(env, CB_CLASS);
        if(jNativesCls == 0){
            printf("Unable to find class: %s", CB_CLASS);
            return;
        }
    }

    if(!mSendStr){
        mSendStr = (*env) -> GetStaticMethodID(env, jNativesCls
            , CB_CLASS_MSG_CB
            , CB_CLASS_MSG_SIG);
    }

    if(mSendStr){
        (*env)->CallStaticVoidMethod(env,
    }
}
