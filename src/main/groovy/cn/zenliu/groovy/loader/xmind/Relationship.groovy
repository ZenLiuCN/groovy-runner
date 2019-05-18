package cn.zenliu.groovy.loader.xmind

import groovy.util.slurpersupport.GPathResult

class Relationship extends XmindNode {
    String from
    String to
    String title

    @Override
    protected Relationship parse(final GPathResult node) {
        super.parse(node)
        title = node.title.text()
        from = node.@end1.text()
        to = node.@end2.text()
        this
    }

    static Relationship parseRelationship(final GPathResult node) {
        new Relationship().parse(node)
    }
}
