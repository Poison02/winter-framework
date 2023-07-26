package cdu.zch.winter.io;

import jakarta.annotation.sub.AnnoScan;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.sql.DataSourceDefinition;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Zch
 * @date 2023/7/26
 **/
public class ResourceResolverTest {

    @Test
    public void scanClass() {
        var pkg = "cdu.zch.scan";
        var rr = new ResourceResolver(pkg);
        List<String> classes = rr.scan(res -> {
            String name = res.name();
            if (name.endsWith(".class")) {
                return name.substring(0, name.length() - 6).replace("/", ".").replace("\\", ".");
            }
            return null;
        });

        Collections.sort(classes);
        System.out.println(classes);
        String[] listClasses = new String[] {
                "cdu.zch.scan.convert.ValueConverterBean", 
                "cdu.zch.scan.destroy.AnnotationDestroyBean", 
                "cdu.zch.scan.init.SpecifyInitConfiguration", 
                "cdu.zch.scan.proxy.OriginBean", 
                "cdu.zch.scan.proxy.FirstProxyBeanPostProcessor", 
                "cdu.zch.scan.proxy.SecondProxyBeanPostProcessor", 
                "cdu.zch.scan.nested.OuterBean", 
                "cdu.zch.scan.nested.OuterBean$NestedBean", 
                "cdu.zch.scan.sub1.Sub1Bean", 
                "cdu.zch.scan.sub1.sub2.Sub2Bean", 
                "cdu.zch.scan.sub1.sub2.sub3.Sub3Bean", 
        };

        for (String clazz : listClasses) {
            assertTrue(classes.contains(clazz));
        }
    }

    @Test
    public void scanJar() {
        var pkg = PostConstruct.class.getPackageName();
        var rr = new ResourceResolver(pkg);
        List<String> classes = rr.scan(res -> {
            String name = res.name();
            if (name.endsWith(".class")) {
                return name.substring(0, name.length() - 6).replace("/", ".").replace("\\", ".");
            }
            return null;
        });

        assertTrue(classes.contains(PostConstruct.class.getName()));
        assertTrue(classes.contains(PreDestroy.class.getName()));
        assertTrue(classes.contains(PermitAll.class.getName()));
        assertTrue(classes.contains(DataSourceDefinition.class.getName()));
        // jakarta.annotation.sub.AnnoScan is defined in classes:
        assertTrue(classes.contains(AnnoScan.class.getName()));
    }

    @Test
    public void scanTxt() {
        var pkg = "cdu.zch.scan";
        var rr = new ResourceResolver(pkg);
        List<String> classes = rr.scan(res -> {
            String name = res.name();
            if (name.endsWith(".txt")) {
                return name.replace("\\", "/");
            }
            return null;
        });
        Collections.sort(classes);
        assertArrayEquals(new String[] {
                // txt files:
                "cdu/zch/scan/sub1/sub1.txt",
                "cdu/zch/scan/sub1/sub2/sub2.txt",
                "cdu/zch/scan/sub1/sub2/sub3/sub3.txt",
        }, classes.toArray(String[]::new));
    }

}
