package com.srtianxia;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class HttpProcessor extends AbstractProcessor {
    private Elements mElementUtils;
    private Filer mFiler;
    private Messager mMessager;


    @Override public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mElementUtils = processingEnvironment.getElementUtils();
        mFiler = processingEnvironment.getFiler();
        mMessager = processingEnvironment.getMessager();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Map<String, CodeInfo> map = findAndParseTargets(roundEnvironment);
        for (String fullClassName : map.keySet()) {
            CodeInfo codeInfo = map.get(fullClassName);
            writeCode(codeInfo);
        }
        return true;
    }


    private void writeCode(CodeInfo codeInfo) {
        try {
            JavaFileObject jfo = mFiler.createSourceFile(
                codeInfo.getInterfacePackageName() + "_Proxy",
                codeInfo.getTypeElement());
            Writer writer = jfo.openWriter();
            writer.write(codeInfo.generateJavaCode());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Map<String, CodeInfo> findAndParseTargets(RoundEnvironment roundEnvironment) {
        Map<String, CodeInfo> map = new HashMap<>();
        for (Element element : roundEnvironment.getElementsAnnotatedWith(GET.class)) {
            ExecutableElement executableElement = (ExecutableElement) element;
            TypeElement classElement = (TypeElement) element
                .getEnclosingElement();

            String fullClassName = classElement.getQualifiedName().toString();
            String className = classElement.getSimpleName().toString();
            String methodName = executableElement.getSimpleName().toString();
            String url = executableElement.getAnnotation(GET.class).value();

            MethodInfo methodInfo = new MethodInfo(url, methodName);
            CodeInfo codeInfo = map.get(fullClassName);
            if (codeInfo != null) {
                codeInfo.addMethod(methodInfo);
            } else {
                codeInfo = new CodeInfo(fullClassName, className, classElement);
                codeInfo.addMethod(methodInfo);
                map.put(fullClassName, codeInfo);
            }
        }
        return map;
    }


    @Override public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(GET.class.getCanonicalName());
        return supportTypes;
    }
}
