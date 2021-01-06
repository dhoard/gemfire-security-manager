# gemfire-security-manager

Simple hard coded Gemfire SecurityManager implementation

username = admin
password = xyz1234

----

1. Clone the project and build it using mvn
```
mvn clean package
```
2. Create a directory for the jar
```
mkdir /opt/security-manager
```
2. Copy target/gemfire-security-manager-0.1.0-SNAPSHOT.jar to the created directory
```
cp target/gemfire-security-manager-0.1.0-SNAPSHOT.jar /opt/security-manager
```
3. Change to the gemfire installation root
```
cd /opt/pivotal-gemfire-9.10.5
``` 
4. Adde gemfire bin to path
```
export PATH=$PATH:/opt/pivotal-gemfire-9.10.5/bin
```
4. Launch the gfsh shell
```
gfsh
```
5. Start the locator
```
gfsh> start locator --name=locator1 \
    > --J=-Dgemfire.security-manager=com.github.dhoard.SimpleSecurityManager \
    > --classpath=/opt/security-manager/gemfire-security-manager-0.1.0-SNAPSHOT.jar
```
6. Start the server
```
gfsh> start server --name=server1 \
    > --locators=gemfire.address.cx[10334] --server-port=40411 \
    > --J=-Dgemfire.security-manager=com.github.dhoard.SimpleSecurityManager \
    > --classpath=/opt/security-manager/gemfire-security-manager-0.1.0-SNAPSHOT.jar \
    > --user=admin --password=xyz1234
```
7. Connect
```
gfsh> connect --locator=gemfire.address.cx[10334] \
    > --user=admin --password=xyz1234
```
8. List members
```
gfsh> list members
```
Example output...
```  
Member Count : 2
  
  Name   | Id
-------- | ------------------------------------------------------------------
locator1 | 192.168.100.173(locator1:9670:locator)<ec><v0>:41000 [Coordinator]
server1  | 192.168.100.173(server1:10139)<v1>:41001
```  
9. Query the region to check for data
```
gfsh> query --query="select * from /check"  
```
Example output...
```
Result : true
Limit  : 100
Rows   : 1

Result
-----------------------------------------------
test-value-9564bbcd-11d3-450a-9b22-6b18b40ac7a9
```
