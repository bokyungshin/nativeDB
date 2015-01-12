LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_PRELINK_MODULE := false
LOCAL_MODULE    := nativeDB
LOCAL_CFLAGS 	:= -w -std=gnu99 -O2 -DNULL=0 -DSOCKLEN_T=socklen_t -DLOCALE_NOT_USED -D_LARGEFILE_SOURCE=1 -D_FILE_OFFSET_BITS=64
LOCAL_CFLAGS 	+= -Drestrict='' -D__EMX__ -DOPUS_BUILD -DFIXED_POINT -DUSE_ALLOCA -DHAVE_LRINT -DHAVE_LRINTF -fno-math-errno
LOCAL_CFLAGS 	+= -DANDROID_NDK -DDISABLE_IMPORTGL -fno-strict-aliasing -fprefetch-loop-arrays -DAVOID_TABLES -DANDROID_TILE_BASED_DECODE -DANDROID_ARMV6_IDCT -DHAVE_STRCHRNUL=0
LOCAL_CPPFLAGS 	:= -DBSD=1 -ffast-math -O2 -funroll-loops
LOCAL_LDLIBS    := -llog -landroid -lEGL -lGLESv1_CM -ljnigraphics
LOCAL_SRC_FILES := \
./jni.c \
./sqlite/sqlite3.c \
./sqlite_cursor.c \
./sqlite_database.c \
./sqlite_statement.c \
./sqlite.c

include $(BUILD_SHARED_LIBRARY)
