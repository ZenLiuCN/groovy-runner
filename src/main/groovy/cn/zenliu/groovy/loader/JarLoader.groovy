package cn.zenliu.groovy.loader

class JarLoader {
    static void loadJar(final String jar) {
        final def jarFile = new File(jar)
        if (!jarFile.exists()) throw new Exception("$jar is not exists")
        if (!jarFile.isFile()) throw new Exception("$jar is not file")
        if (!jarFile.canRead()) throw new Exception("$jar is not readable")
        this.classLoader.addURL(jarFile.toURI().toURL())
    }
}
