LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := native-codec-jni
LOCAL_SRC_FILES := \
	C:\Users\zpff\Documents\GitHub\LearnAndroid\native-codec\app\src\main\jni\Android.mk \
	C:\Users\zpff\Documents\GitHub\LearnAndroid\native-codec\app\src\main\jni\Application.mk \
	C:\Users\zpff\Documents\GitHub\LearnAndroid\native-codec\app\src\main\jni\looper.cpp \
	C:\Users\zpff\Documents\GitHub\LearnAndroid\native-codec\app\src\main\jni\native-codec-jni.cpp \

LOCAL_C_INCLUDES += C:\Users\zpff\Documents\GitHub\LearnAndroid\native-codec\app\src\main\jni
LOCAL_C_INCLUDES += C:\Users\zpff\Documents\GitHub\LearnAndroid\native-codec\app\src\debug\jni

include $(BUILD_SHARED_LIBRARY)
