#include <stdio.h>
#include <stdlib.h>

#include <jni.h>

#include "../include/jni_Natives.h"

#define CB_CLASS "jni/Natives"

#define CB_CLASS_MSG_CB "OnMessage"
#define CB_CLASS_MSG_SIG "(Ljava/lang/String;I)V"

int lib_main(int argc, char **argv);

const int getArrayLen(JNIEnv *env, jobjectArray jarray);

void jni_printf(char *format, ...);

static JavaVM *g_VM;

static jclass jNativesCls;

JNIEXPORT jint JNICALL Java_jni_Natives_LibMain
    (JNIEnv *env, jclass class, jobjectArray jargv){
        (*env)->GetJavaVM(env, &g_VM);

        jsize clen = getArrayLen(env, jargv);

        char *args[(int)clen];

        jstring jrow; int i;
        for( i = 0; i < clen; i++){
            jrow = (jstring)(*env)->GetObjectArrayElement(env, jargv, i);
            const char *row = (*env)->GetStringUTFChars(env, jrow, 0);

            args[i] = malloc(strlen(row) +1);
            strcpy(args[i], row);

            jni_printf("Main argvs[%d] = %s", i, args[i]);

            (*env)->ReleaseStringUTFChars(env, jrow, row);
        }

        jNativesCls = (*env) ->FindClass(env, CB_CLASS);
        if(jNativesCls == 0){
            jni_printf("Unable to find class: %s", CB_CLASS);
            return -1;
        }

        lib_main(clen, args);
        return 0;
    }

    jmethodID mSendStr;

    static void jni_send_str(const char* text, int level){
        JNIEnv *env;

        if(!g_VM){
            printf("I_JNI-NOVM: %s\n", text);
            return;
        }

        (*g_VM) ->AttachCurrentThread(g_VM, &env, NULL);

        if(!jNativesCls){
            jNativesCls = (*env)->FindClass(env, CB_CLASS);

            if(jNativesCls == 0){
                printf("Unable to find class: %s", CB_CLASS);
                return;
            }
        }

        if(!mSendStr){
            mSendStr = (*env)->GetStaticMethodID(env, jNativesCls
                , CB_CLASS_MSG_CB
                , CB_CLASS_MSG_SIG);
        }

        if(mSendStr){
            (*env)->CallStaticVoidMethod(env, jNativesCls
                    , mSendStr
                    , (*env)->NewStringUTF(env, text)
                    , (jint) level);
        } else {
            printf("Unable to find method: %s, signature: %s\n", CB_CLASS_MSG_CB, CB_CLASS_MSG_SIG);
        }
    }

    void  jni_printf(char *format, ...){
        va_list argptr;
        static char string[1024];

        va_start(argptr, format);
        vsprintf(string, format, argptr);
        va_end(argptr);

        jni_send_str(string, 0);
    }

    const int getArrayLen(JNIEnv *env, jobjectArray jarray){
        return (*env) -> GetArrayLength(env, jarray);
    }

    int lib_main(int argc, char **argv){
        int i;

        jni_printf("Entering LIB MAIN");

        for(i = 0; i < argc; i++){
            jni_printf("Lib Main argv[%d] = %s",i, argv[i]);
        }

        return 0;
    }