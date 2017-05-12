package ru.otus.l52;

import ru.otus.l52.annotations.DebugCode;
import ru.otus.l52.annotations.GenerateResource;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.*;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by tully.
 * <p>
 * Generates resource for GeneratedResource annotation.
 * Prints ERROR if @DebugCode found.
 */

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({
        "ru.otus.l52.annotations.DebugCode",
        "ru.otus.l52.annotations.GenerateResource"
})
public class MyAnnotationProcessor extends AbstractProcessor {
    private final static String DEBUG_CODE_NAME = DebugCode.class.getCanonicalName();
    private final static String GENERATE_RESOURCE_NAME = GenerateResource.class.getCanonicalName();

    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        try {
            for (TypeElement typeElement : annotations) {
                if (typeElement.equals(elementUtils.getTypeElement(GENERATE_RESOURCE_NAME))) {
                    Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(typeElement);
                    for (Element element : elements) {
                        GenerateResource annotation = element.getAnnotation(GenerateResource.class);
                        FileObject fileObject = filer.createResource(StandardLocation.SOURCE_OUTPUT, "generated", annotation.file());
                        OutputStream out = fileObject.openOutputStream();
                        out.write((element.getSimpleName() + " " + annotation.message()).getBytes());
                        out.close();

                        messager.printMessage(Diagnostic.Kind.NOTE, "File created: " + fileObject.getName());
                    }

                }
                if (typeElement.equals(elementUtils.getTypeElement(DEBUG_CODE_NAME))) {
                    Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(typeElement);
                    for (Element element : elements) {
                        DebugCode annotation = element.getAnnotation(DebugCode.class);
                        String msg = element.getKind() + " " + element.getSimpleName() + " " + annotation.comment();
                        messager.printMessage(Diagnostic.Kind.ERROR, msg);
                    }
                }
            }


        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, e.getLocalizedMessage());
        }
        return true;
    }
}