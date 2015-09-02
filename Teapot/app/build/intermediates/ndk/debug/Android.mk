LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := TeapotNativeActivity
LOCAL_SRC_FILES := \
	C:\Users\zpff\Documents\GitHub\LearnAndroid\Teapot\app\src\main\jni\Android.mk \
	C:\Users\zpff\Documents\GitHub\LearnAndroid\Teapot\app\src\main\jni\Application.mk \
	C:\Users\zpff\Documents\GitHub\LearnAndroid\Teapot\app\src\main\jni\teapot.inl \
	C:\Users\zpff\Documents\GitHub\LearnAndroid\Teapot\app\src\main\jni\TeapotNativeActivity.cpp \
	C:\Users\zpff\Documents\GitHub\LearnAndroid\Teapot\app\src\main\jni\TeapotRenderer.cpp \

LOCAL_C_INCLUDES += C:\Users\zpff\Documents\GitHub\LearnAndroid\Teapot\app\src\main\jni
LOCAL_C_INCLUDES += C:\Users\zpff\Documents\GitHub\LearnAndroid\Teapot\app\src\debug\jni

include $(BUILD_SHARED_LIBRARY)
