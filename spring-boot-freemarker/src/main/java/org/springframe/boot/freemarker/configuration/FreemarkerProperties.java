package org.springframe.boot.freemarker.configuration;

import org.springframework.boot.autoconfigure.template.AbstractTemplateViewResolverProperties;
import org.springframework.boot.autoconfigure.template.AbstractViewResolverProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "freemarker.auto")
public class FreemarkerProperties extends AbstractTemplateViewResolverProperties {

    private static String DEFAULT_TEMPLATE_PATH = "classpath:/template";

    private static String DEFAULT_PREFIX = "/";

    private static String DEFAULT_SUFFIX = ".ftl";

    private String[] templateLoaderPaths = new String[]{DEFAULT_TEMPLATE_PATH};

    private Boolean preferFileSystemAccess = true;

    private Map<String, String> properties = new HashMap<String, String>();

    protected FreemarkerProperties() {
        super(DEFAULT_PREFIX, DEFAULT_SUFFIX);
    }

    public String[] getTemplateLoaderPaths() {
        return templateLoaderPaths;
    }

    public void setTemplateLoaderPaths(String[] templateLoaderPaths) {
        this.templateLoaderPaths = templateLoaderPaths;
    }

    public Boolean getPreferFileSystemAccess() {
        return preferFileSystemAccess;
    }

    public void setPreferFileSystemAccess(Boolean preferFileSystemAccess) {
        this.preferFileSystemAccess = preferFileSystemAccess;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
