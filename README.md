# Jolokia
binディレクトリにjolokiaのjavaagent.jarを配置して以下でPIDの一覧を取得、アプリのPIDを特定する。

> java -jar .\jolokia-agent-jvm-2.1.1-javaagent.jar list

PIDを指定してjolokia agentを起動する。

> java -jar .\jolokia-agent-jvm-2.1.1-javaagent.jar --user jolokia --password jolokia start <PID>

## カスタムJMXの属性取得

`http://127.0.0.1:8778/jolokia/read/com.example:type=JMX_Demo` にアクセス。

## 全スレッドダンプの取得

`http://127.0.0.1:8778/jolokia/exec/java.lang:type=Threading/dumpAllThreads(boolean,boolean)/true/true` にアクセス。
