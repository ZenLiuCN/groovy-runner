package cn.zenliu.groovy.loader.xmind

import groovy.util.slurpersupport.GPathResult

class Topic extends XmindNode {
    String title
    HashSet<Topic> children
    HashSet<String> marker
    String label
    String note

    @Override
    protected Topic parse(final GPathResult node) {
        super.parse(node)
        title = node.title.text()
        if (!node.children.isEmpty()) {
            children = new HashSet<>()
            node.children.topics.topic*.each { final GPathResult n ->
                if (n.@type == "summary") return
                children.add(Topic.parseTopic(n))
            }
        }
        if (!node.notes.isEmpty() && !node.notes.plain.isEmpty()) {
            note = node.notes.plain.text()
        }
        if (!node.labels.isEmpty()) {
            label = node.labels.label.text()
        }
        if (!node."marker-refs".isEmpty()) {
            marker = new HashSet<>()
            node."marker-refs".children().each { final GPathResult it ->
                marker.add(it."@marker-id".text() as String)
            }
        }
        this
    }

    String hasMarker(final String marker) {
        this.marker?.find { it == marker }
    }

    static Topic parseTopic(final GPathResult node) {
        new Topic().parse(node)
    }
}
