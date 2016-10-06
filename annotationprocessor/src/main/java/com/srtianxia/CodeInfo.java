package com.srtianxia;

import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.TypeElement;

/**
 * Created by srtianxia on 2016/10/4.
 */

public class CodeInfo {
    private String mInterfacePackageName;
    private String mInterfaceSimpleName;
    private List<MethodInfo> mMethodList = new ArrayList<>();
    private TypeElement mTypeElement;

    public CodeInfo(String interfacePackageName, String interfaceSimpleName, TypeElement element) {
        this.mInterfacePackageName = interfacePackageName;
        this.mInterfaceSimpleName = interfaceSimpleName;
        this.mTypeElement = element;
    }


    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("// Generated code from AnnotationHttp. Do not modify!\n");
        builder.append("import com.srtianxia.httplibs.Callback;\n" +
            "import com.srtianxia.httplibs.HttpClient;\n" +
            "import com.srtianxia.httplibs.Request;\n");

        builder.append("public class ").append(mInterfaceSimpleName + "_Proxy");
        builder.append(" implements ").append(mInterfacePackageName).append(" {");
        generateField(builder);

        for (MethodInfo info : mMethodList) {
            generateMethod(builder, info);
        }

        builder.append("\n").append("\n}");

        return builder.toString();
    }


    private void generateMethod(StringBuilder builder, MethodInfo methodInfo) {
        builder.append("  @Override ").append("public void ").append(methodInfo.getMethodName())
            .append("(Callback callback) {\n" +
                "        mClient.newCall(new Request.Builder().url(" + "\"" + methodInfo.getUrl() + "\"" +
                ").build())\n" +
                "            .enqueue(callback);\n" +
                "    }");
    }


    private void generateField(StringBuilder builder) {
        builder.append("\n  private HttpClient mClient = new HttpClient();\n");
    }


    public void addMethod(MethodInfo methodNameList) {
        mMethodList.add(methodNameList);
    }


    public TypeElement getTypeElement() {
        return mTypeElement;
    }


    public String getInterfacePackageName() {
        return mInterfacePackageName;
    }
}
