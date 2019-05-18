package cn.zenliu.groovy.loader.xmind

import groovy.util.slurpersupport.GPathResult

abstract class XmindNode {
    String xid
    String modifier
    Long timestamp

    protected XmindNode parse(final GPathResult node) {
        xid = node.@id.text()
        modifier = node."@modified-by".text()
        timestamp = node.@timestamp.toLong()
        this
    }
}
