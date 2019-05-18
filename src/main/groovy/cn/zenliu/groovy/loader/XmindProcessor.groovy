package cn.zenliu.groovy.loader

import cn.zenliu.groovy.loader.xmind.Sheet
import groovy.util.slurpersupport.GPathResult
import java.nio.file.FileSystems
import java.nio.file.Files

class XmindProcessor {
    static GPathResult read(final String file) {
        final def zip = new File(file)
        if (!zip.exists() || !zip.file || !zip.canRead()) throw new Exception("file $zip invalid")
        final def uri = URI.create("jar:file:/${zip.absolutePath.replace("\\", "/")}")
        final def xml = FileSystems.newFileSystem(uri, new HashMap<String, Object>()).getPath("/content.xml")
        final def os = new ByteArrayOutputStream()
        Files.copy(xml, os)
        return XmlSlurper.newInstance().parseText(os.toString("UTF-8"))
    }

    static HashSet<Sheet> parse(final String file) {
        read(file).sheet*.collect { final GPathResult it ->
            Sheet.parseSheet(it)
        }
    }
}



