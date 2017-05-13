package ru.otus.l52;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by tully.
 */
public class MyClassTransformer implements ClassFileTransformer {

    private ClassPool pool;

    public MyClassTransformer() {
        pool = ClassPool.getDefault();
    }

    public byte[] transform(ClassLoader loader,
                            String className,
                            Class classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            pool.insertClassPath(new ByteArrayClassPath(className, classfileBuffer));
            CtClass cclass = pool.get(className.replaceAll("/", "."));

            if (!cclass.getName().startsWith("ru.otus.")) {
                return null; // the same as return classfileBuffer; without changes
            }

            for (CtMethod currentMethod : cclass.getDeclaredMethods()) {
                AddLog annotation = (AddLog) currentMethod.getAnnotation(AddLog.class);
                if (annotation != null) {
                    currentMethod.insertBefore("{System.out.println(\"" + annotation.message() + "\");}");
                }
            }

            return cclass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
