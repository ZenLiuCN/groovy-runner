# groovy-runner

a package for group some useful tools to run groovy script

# speciation

1. preload any groovy script in base script file dirctory(*as in and in subdirectories*) **(must end with`.groovy` and without named as `*.ignore*.groovy`)**
2. inner toolsets

    1.  org.codehaus.groovy:groovy:2.5.6
    2.  org.codehaus.groovy:groovy-jsr223:2.5.6
    3.  org.codehaus.groovy:groovy-xml:2.5.6 `for xml processor`
    4.  org.codehaus.groovy:groovy-json:2.5.6 `for some json processor`
    5.  com.squareup:kotlinpoet:1.2.0 `for kotlin code generate`
    6.  com.squareup:javapoet:1.11.1 `for java code generate`
    7.  org.jooq:jooq:3.11.11 `query lib`
    8.  com.zaxxer:HikariCP:3.3.1 `most liked connection pool`

# exmaple of useage

```shell
java -jar runner.jar myboot.groovy
```
myboot
```groovy
   println "hello"
```
# inner simple api

1. load jar into classpath
```groovy
    cn.zenliu.groovy.loader.JarLoader.loadJar("somedriver.jar")
```
2. parse xmind file `my favor part`
```groovy
   final GPathResult content=cn.zenliu.groovy.loader.XmindProcessor.read("my.xmind")
   final Set<cn.zenliu.groovy.loader.xmind.Sheet> sheets=cn.zenliu.groovy.loader.XmindProcessor.parse("my.xmind")
   //now you can do any thing with xmind data, like generate code from it
```
3. connect to database
```groovy
    import cn.zenliu.groovy.loader.JarLoader
    import cn.zenliu.groovy.loader.Jooq
    import com.zaxxer.hikari.HikariConfig
    import org.jooq.SQLDialect
    import org.jooq.conf.Settings
    import org.jooq.impl.DefaultDataType

    JarLoader.loadJar("sqlite-jdbc-3.27.2.1.jar")
    final def config = new HikariConfig()
    config.driverClassName = "org.sqlite.JDBC"
    config.jdbcUrl = "jdbc:sqlite::memory:"
    final def setting = new Settings()
    final def dsl = Jooq.createDSL(SQLDialect.SQLITE, config, setting)
    println dsl.createTable("test").column("id", new DefaultDataType<String>(dsl.dialect(), String.class, "TEXT")).execute()
    println dsl.execute("INSERT INTO test values('1')")
    println dsl.resultQuery("SELECT * FROM test").fetchOne("id")
```
