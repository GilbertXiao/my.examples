#!/bin/sh

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# DO NOT MODIFY THE TEMPLATE FILE.  INSTEAD, COPY THIS FILE AS setenv.bat
# AND MAKE THE NECESSARY CHANGES IN THE LOCAL (VIEW PRIVATE) FILE.
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

# - - - - - - - - - - - - - - - - - - - - - - -
# Set JAVA_HOME to a valid JDK installation dir
# 	e.g.: set JAVA_HOME=/apps/java/1.5.1
# - - - - - - - - - - - - - - - - - - - - - - -
export JAVA_HOME=/u01/tools/jdk/jdk1.6.0_30

# - - - - - - - - - - - -  - - - - - - - - - -
# Set ANT_HOME to a valid ANT installation dir
# 	e.g.: set ANT_HOME=/apps/ant/1.8.2
# - - - - - - - - - - - -  - - - - - - - - - -
export ANT_HOME=/u01/tools/ant-1.8.2

# - - - - - - - - - - - -  - - - - - - - - - - - - - - - - -
# TMS specific settings - others leave this empty
# Set HOS_LIB_PATH to a valid path to libHOSLibrary.so
# 	e.g.: set HOS_LIB_PATH=/apps/scpp/data/so/HOS/aix
# - - - - - - - - - - - -  - - - - - - - - - - - - - - - - -
export HOS_LIB_PATH=

export LIBPATH=$HOS_LIB_PATH:$LIBPATH
export LD_LIBRARY_PATH=$HOS_LIB_PATH:$LD_LIBRARY_PATH


# - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Set PATH to include bin directories from JDK and ANT
# - - - - - - - - - - - - - - - - - - - - - - - - - - -
export PATH=$JAVA_HOME/bin:$ANT_HOME/bin:$LIBPATH:$PATH

# - - - - - - - - - - - - - - - - - - - - - - - -
# Set ANT_OPTS to increase the ANT runtime memory
# - - - - - - - - - - - - - - - - - - - - - - - -
export ANT_OPTS="-Xmx2048m -XX:MaxPermSize=256m"
