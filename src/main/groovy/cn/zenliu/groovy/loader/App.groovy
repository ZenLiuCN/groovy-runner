package cn.zenliu.groovy.loader

import groovy.io.FileType
import org.codehaus.groovy.control.CompilerConfiguration

class App {
    static void main(final String[] args) {
        if (args.size() == 0) throw new Exception("must have script parameter,Eg: groovy.groovy")
        final File script = new File(args[0])
        if (!script.exists()) throw new Exception("Script ${script.absolutePath} not exists!")
        if (!script.file || !script.canRead()) throw new Exception("Script ${script.absolutePath} invalid or can't read!")
        final def binding = new Binding()
        final def shell = new GroovyShell(binding, new CompilerConfiguration(sourceEncoding: 'UTF-8'))
        script.absoluteFile.parentFile.eachFileRecurse(FileType.FILES) {
            try {
                if (!it.name.contains(".ignore") && it.name.endsWith(".groovy")&& !it.absolutePath.contains("src") && it.name != script.name) {
                    shell.parse(it)
                }
            } catch (Exception e) {
                println(it.absolutePath)
                e.printStackTrace()
            }
        }
        shell.evaluate(script)
        void
    }
}
