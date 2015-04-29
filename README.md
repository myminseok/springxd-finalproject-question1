# solution for Query1 of Gemfire,SpringXD Training
- @ mkim@pivotal.io
- @ schoi@pivotal.io

# summary
this documentation explains how to run and test this project contents.
Download data from https://www.dropbox.com/s/o1wqgv713n2k1kn/sorted_data.csv.gz?dl=0
This project is based on the challenge below.
http://www.debs2015.org/call-grand-challenge.html

for question #1, we use spring xd for integration and use gemfire for data cache layer.
every time taxi trip data ingested into spring xd, it converted into Trip Java Object using customized spring xd module.
and it pushes into gemfire 'Trip' region which expire record in 30minutes.
due to lack of 'group by' feature in gemfire, this project uses another gemfire region where summary of  taxi trip data is inserted.
TripListener  bound to the 'Trip' region will be called every afterCreate, afterUpdate event of 'Trip' region.
in the TripListener, it will count all count of related to the ingested data and update 'TripStat'.

when CQClient runs, it will be notify update event of TripStat, and it will continuous query and show top 10 count of TripStat records.


# Prerequisite

1. JDK1.7+
1. maven 3 + (with internet connectivity(for initial maven compile).
1. gemfire 8.1 + (http://docs.gopivotal.com/gemfire/)
1. Spring XD     (http://projects.spring.io/spring-xd/)


1.1 for Mac

    ```
    brew install gemfire

    brew install maven
    ```


## build sourcecode

1. download this git repository.

    ```
    git clone [this git repository URL]
    ```

    ```
    cd springxd-finalproject-question1
    mvn clean package
    ```


## start gemfire server

1. go to the downloaded git repository

    ```
    $> cd springxd-finalproject-question1/gemfire-server
    ```
1.  review  setenv.sh contents.
    run gemfire server at local machine.
    it takes time a bit.

    ```
    $> sh startServer.sh
    Starting locator and server...
    ..............................
    Locator in /Users/kimm5/_dev/sptraining/springxd-finalproject-question1/gemfire-server/locator1 on 192.168.0.114[41111] as locator1 is currently online.
    Process ID: 50665
    Uptime: 15 seconds
    GemFire Version: 8.1.0
    Java Version: 1.7.0_75
    Log File: /Users/kimm5/_dev/sptraining/springxd-finalproject-question1/gemfire-server/locator1/locator1.log
    JVM Arguments: -DgemfirePropertyFile=/Users/kimm5/_dev/sptraining/springxd-finalproject-question1/gemfire-server/gemfire.properties -Dgemfire.enable-cluster-configuration=true -Dgemfire.load-cluster-configuration-from-dir=false -Xms50m -Xmx50m -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=60 -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
    Class-Path: /usr/local/Cellar/gemfire/8.1.0/libexec/lib/gemfire.jar:/usr/local/Cellar/gemfire/8.1.0/libexec/lib/locator-dependencies.jar

    Successfully connected to: [host=192.168.0.114, port=1099]

    Cluster configuration service is up and running.

    .....
    Server in /Users/kimm5/_dev/sptraining/springxd-finalproject-question1/gemfire-server/server1 on 192.168.0.114[51508] as server1 is currently online.
    Process ID: 50685
    Uptime: 2 seconds
    GemFire Version: 8.1.0
    Java Version: 1.7.0_75
    Log File: /Users/kimm5/_dev/sptraining/springxd-finalproject-question1/gemfire-server/server1/server1.log
    JVM Arguments: -DgemfirePropertyFile=/Users/kimm5/_dev/sptraining/springxd-finalproject-question1/gemfire-server/gemfire.properties -Dgemfire.use-cluster-configuration=true -XX:OnOutOfMemoryError=kill -KILL %p -Xms50m -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
    Class-Path: /usr/local/Cellar/gemfire/8.1.0/libexec/lib/gemfire.jar:../target/classes:/usr/local/Cellar/gemfire/8.1.0/libexec/lib/server-dependencies.jar

    ......
    Server in /Users/kimm5/_dev/sptraining/springxd-finalproject-question1/gemfire-server/server2 on 192.168.0.114[51537] as server2 is currently online.
    Process ID: 50734
    Uptime: 3 seconds
    GemFire Version: 8.1.0
    Java Version: 1.7.0_75
    Log File: /Users/kimm5/_dev/sptraining/springxd-finalproject-question1/gemfire-server/server2/server2.log
    JVM Arguments: -DgemfirePropertyFile=/Users/kimm5/_dev/sptraining/springxd-finalproject-question1/gemfire-server/gemfire.properties -Dgemfire.use-cluster-configuration=true -XX:OnOutOfMemoryError=kill -KILL %p -Xms50m -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
    Class-Path: /usr/local/Cellar/gemfire/8.1.0/libexec/lib/gemfire.jar:../target/classes:/usr/local/Cellar/gemfire/8.1.0/libexec/lib/server-dependencies.jar

    Loading sample data...
    Got the domain.Trip Region: com.gemstone.gemfire.internal.cache.LocalRegion[path='/Trip';scope=LOCAL';dataPolicy=EMPTY; concurrencyChecksEnabled]
    Inserted a book: Trip [medallion=07290D3599E7A0D62097A346EFCC1FB5, hackLicense=E7750A37CAB07D0DFF0AF7E3573AC141, Tue Jan 01 00:00:00 KST 2013, Tue Jan 01 00:02:00 KST 2013, 25936.5652, 25714.5652
    Inserted a book: Trip [medallion=17290D3599E7A0D62097A346EFCC1FB5, hackLicense=17750A37CAB07D0DFF0AF7E3573AC141, Tue Jan 01 00:00:00 KST 2013, Tue Jan 01 00:02:00 KST 2013, 25714.5652, 25714.5652
    Inserted a book: Trip [medallion=17290D3599E7A0D62097A346EFCC1FB5, hackLicense=17750A37CAB07D0DFF0AF7E3573AC141, Tue Jan 01 00:00:00 KST 2013, Tue Jan 01 00:02:00 KST 2013, 26382.5652, 25714.5652
    Inserted a book: Trip [medallion=47290D3599E7A0D62097A346EFCC1FB5, hackLicense=47750A37CAB07D0DFF0AF7E3573AC141, Tue Jan 01 00:00:00 KST 2013, Tue Jan 01 00:02:00 KST 2013, 25714.5652, 25714.5652

    ```

1. gfsh

    ```
    $> gfsh
    ```

    on gfsh client, connect to gemfire locator and check current status.

    ```
    gfsh>connect --locator=localhost[41111]
    gfsh>connect --locator=localhost[41111]
    Connecting to Locator at [host=localhost, port=41111] ..
    Connecting to Manager at [host=192.168.0.114, port=1099] ..
    Successfully connected to: [host=192.168.0.114, port=1099]

    gfsh>list regions
    List of regions
    ---------------
    Trip
    TripStat

    gfsh>query --query="select distinct * from /TripStat"

    Result     : true
    startCount : 0
    endCount   : 20
    Rows       : 0


    NEXT_STEP_NAME : END

    gfsh>query --query="select distinct * from /Trip"

    Result     : true
    startCount : 0
    endCount   : 20
    Rows       : 0


    NEXT_STEP_NAME : END

    gfsh>

    ```

## start spring XD process

1. go to the downloaded spring XD folder.

1. run as singlenode

    ```
    $> cd spring-xd-1.1.1.RELEASE/xd/bin
    $> xd-singlenode
    ```
1. run xd-shell

    ```
    $> cd spring-xd-1.1.1.RELEASE/shell/bin
    $> xd-shell
    ```

1. [optional] on xd-shell, cleansing old module and stream if exists

    ```
    xd:> stream destroy trip
    xd:> module delete --name processor:trip-converter

    ```
1. upload processor module via xd-shell

    ```
    xd:> module delete --name processor:trip-converter
    xd:> module upload --name trip-converter --type processor --file [PATH_TO_springxd-finalproject-question1]/converter-modules/target/trip-converter-0.0.1-SNAPSHOT.jar

    ```
1. create stream to process input traffic and push to gemfire.

    ```
    xd:> stream create --definition  "http | trip-converter | gemfire-server --host=localhost --port=41111 --useLocator=true --regionName=Trip --keyExpression=payload " --name trip --deploy
    ```


## prepare Continuous Query

1. go to the downloaded git repository

    ```
    $> cd springxd-finalproject-question1/gemfire-server
    ```
1.  review  setenv.sh contents.
    run gemfire server at local machine.

    ```
    $> source setenv.sh
    $> java client.CQClient
    Made new CQ Service
    Press enter to end

    ```

## run data ingestion.

1. go to the data folder.

1. run data ingestion script into spring xd stream which is created just above.

    ```
    $> cd springxd-finalproject-question1/data
    $> python ingest.py

    1 07290D3599E7A0D62097A346EFCC1FB5,E7750A37CAB07D0DFF0AF7E3573AC141,2013-01-01 00:00:00,2013-01-01 00:02:00,120,0.44,-73.956528,40.716976,-73.962440,40.715008,CSH,3.50,0.50,0.50,0.00,0.00,4.50

    2 22D70BF00EEB0ADC83BA8177BB861991,3FF2709163DE7036FCAA4E5A3324E4BF,2013-01-01 00:02:00,2013-01-01 00:02:00,0,0.00,0.000000,0.000000,0.000000,0.000000,CSH,27.00,0.00,0.50,0.00,0.00,27.50
    ...


    ```

1. go to Continous Query and see the response as following.

    ```
    Made new CQ Service
    Press enter to end
    pickup:2013-01-01 00:00:00, dropoff:2013-01-01 00:02:00, cell ranking:[25714.5652, 25714.5652] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL]
    pickup:2013-01-01 00:00:00, dropoff:2013-01-01 00:02:00, cell ranking:[25714.5652, 25714.5652] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL]
    pickup:2013-01-01 00:02:00, dropoff:2013-01-01 00:02:00, cell ranking:[16660.2350, 16660.2350] [25714.5652, 25714.5652] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL]
    pickup:2013-01-01 00:02:00, dropoff:2013-01-01 00:02:00, cell ranking:[16660.2350, 16660.2350] [25714.5652, 25714.5652] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL]
    pickup:2013-01-01 00:01:00, dropoff:2013-01-01 00:03:00, cell ranking:[25722.5654, 25724.5654] [16660.2350, 16660.2350] [25714.5652, 25714.5652] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL] [NULL]

    ```

