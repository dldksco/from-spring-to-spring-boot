package tobyspring.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.StreamSupport;

/**
 * Deffered를 써야되는 이유
 * 우리가 만든 유저 구성정보가 다 로딩이되고 자동 구성 정보를 하나씩 적용되게하기위해
 */
public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;
    
    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }    
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        /**
         * 애플리케이션에서 클래스 패스에서 리소스를  읽어올때는 클래스로더를 이용해
         */

        Iterable<String>  candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        return StreamSupport.stream(candidates.spliterator(),false).toArray(String[]::new);
    }


}

