export JAVA_HOME=`/usr/libexec/java_home`
export GEMFIRE_HOME=/usr/local/Cellar/gemfire/8.1.0/libexec
export CLASSPATH=../bin:./bin:./target/classes:../target/classes:$JAVA_HOME/lib/tools.jar:./xml



# Run the Loader client (LoadRegions) to load the data
export REPO=~/.m2/repository
CLASSPATH=$CLASSPATH:"$REPO"/org/springframework/data/spring-data-gemfire/1.5.0.RELEASE/spring-data-gemfire-1.5.0.RELEASE.jar
CLASSPATH=$CLASSPATH:"$REPO"/antlr/antlr/2.7.7/antlr-2.7.7.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/slf4j/jcl-over-slf4j/1.7.6/jcl-over-slf4j-1.7.6.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/slf4j/slf4j-api/1.7.6/slf4j-api-1.7.6.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/aspectj/aspectjweaver/1.8.2/aspectjweaver-1.8.2.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/springframework/data/spring-data-commons/1.9.0.RELEASE/spring-data-commons-1.9.0.RELEASE.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/springframework/spring-core/3.2.11.RELEASE/spring-core-3.2.11.RELEASE.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/springframework/spring-beans/3.2.11.RELEASE/spring-beans-3.2.11.RELEASE.jar
CLASSPATH=$CLASSPATH:"$REPO"/com/gemstone/gemfire/gemfire/8.0.0/gemfire-8.0.0.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/springframework/spring-tx/3.2.11.RELEASE/spring-tx-3.2.11.RELEASE.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/codehaus/jackson/jackson-mapper-asl/1.9.12/jackson-mapper-asl-1.9.12.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/codehaus/jackson/jackson-core-asl/1.9.12/jackson-core-asl-1.9.12.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/springframework/spring-aop/3.2.11.RELEASE/spring-aop-3.2.11.RELEASE.jar
CLASSPATH=$CLASSPATH:"$REPO"/aopalliance/aopalliance/1.0/aopalliance-1.0.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/springframework/spring-context-support/3.1.2.RELEASE/spring-context-support-3.1.2.RELEASE.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/springframework/spring-context/3.2.11.RELEASE/spring-context-3.2.11.RELEASE.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/springframework/spring-expression/3.2.11.RELEASE/spring-expression-3.2.11.RELEASE.jar
CLASSPATH=$CLASSPATH:"$REPO"/log4j/log4j/1.2.17/log4j-1.2.17.jar
CLASSPATH=$CLASSPATH:"$REPO"/cglib/cglib/2.2.2/cglib-2.2.2.jar
CLASSPATH=$CLASSPATH:"$REPO"/asm/asm/3.3.1/asm-3.3.1.jar
CLASSPATH=$CLASSPATH:"$REPO"/org/slf4j/slf4j-log4j12/1.7.5/slf4j-log4j12-1.7.5.jar
CLASSPATH=$CLASSPATH:"$REPO"/com/gopivotal/training/server-config/1.0.0.CI-SNAPSHOT/server-config-1.0.0.CI-SNAPSHOT.jar
export CLASSPATH

