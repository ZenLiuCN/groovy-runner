package cn.zenliu.groovy.loader.xmind

import groovy.util.slurpersupport.GPathResult

class Sheet extends XmindNode {
    String title
    Topic root
    HashSet<Relationship> relationships

    @Override
    protected Sheet parse(final GPathResult node) {
        super.parse(node)
        title = node.title.text()
        root = Topic.parseTopic(node.topic as GPathResult)
//        println("sheet:$title  ${node.relationships.isEmpty()}")
        if (!node.relationships.isEmpty()) {
            relationships = new HashSet<>()
            node.relationships.relationship*.each { final GPathResult it ->
                relationships.add(Relationship.parseRelationship(it))
            }
        }
        this
    }

    static Sheet parseSheet(final GPathResult node) {
        new Sheet().parse(node)
    }


}
