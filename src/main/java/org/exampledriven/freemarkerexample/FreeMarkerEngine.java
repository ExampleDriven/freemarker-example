package org.exampledriven.freemarkerexample;

import freemarker.template.*;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.StringWriter;

@Component
public class FreeMarkerEngine {

    private Configuration configuration = new Configuration();

    public FreeMarkerEngine() {

        configuration.setClassForTemplateLoading(getClass(), "/");
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setIncompatibleImprovements(new Version(2, 3, 20));

    }

    public String process(String templateName, Object model) {
        try {

            Template template = configuration.getTemplate(templateName);
            StringWriter stringWriter = new StringWriter();
            template.process(model, stringWriter);

            return stringWriter.toString();

        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

}