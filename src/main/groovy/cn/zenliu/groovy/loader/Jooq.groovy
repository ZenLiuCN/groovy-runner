package cn.zenliu.groovy.loader

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.conf.Settings
import org.jooq.impl.DSL

class Jooq {
    static DSLContext createDSL(final SQLDialect dialect, final HikariConfig config, final Settings settings) {
        final def ds = new HikariDataSource(config)
        DSL.using(ds, dialect, settings)
    }
}
