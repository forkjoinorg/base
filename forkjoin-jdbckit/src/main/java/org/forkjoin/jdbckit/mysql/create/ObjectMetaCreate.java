package org.forkjoin.jdbckit.mysql.create;

import org.forkjoin.jdbckit.mysql.Config;
import org.forkjoin.jdbckit.mysql.HttlUtils;
import org.forkjoin.jdbckit.mysql.SqlUtils;
import org.forkjoin.jdbckit.mysql.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectMetaCreate {
    private static final Logger log = LoggerFactory.getLogger(ObjectMetaCreate.class);

    public static void create(List<Table> tl, Config config, String objectPack, String objectMetaPack) throws Exception {
        File dir = config.getPackPath(objectMetaPack);
        if (!dir.exists()) {
            if(!dir.mkdirs()){
                throw new RuntimeException("创建路径失败:" + dir.getAbsolutePath());
            }
        }


        for (Table ta : tl) {
            File f = new File(dir, ta.getClassName() + "Meta.java");
            log.info("ObjectCreate file:{}", f.getAbsolutePath());
            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(f))) {
                Map<String, Object> context = new HashMap<>();
                context.put("t", ta);
                context.put("sql", new SqlUtils());
                context.put("pack", config.getPack(objectMetaPack));
                context.put("beanPack", config.getPack(objectPack));
                HttlUtils.render(
                        "/org/forkjoin/jdbckit/mysql/template/objectMeta.httl",
                        context, out
                );
            }
        }
    }
}
