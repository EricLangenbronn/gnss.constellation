package fr.gnss.constellation.ouranos.service.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import fr.gnss.constellation.ouranos.version.ApiVersionUtil;
import fr.gnss.constellation.ouranos.version.Version;

public class TemplateUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(TemplateUtils.class);

	private static final String EXTENSION = ".vm";
	private static final String ROOT_DIRECTORY = "templates";
	private static final ApiVersionUtil API_VERSION_UTIL = ApiVersionUtil.getInstance();

	public String getPathTemplateRootDirectory(String resource, String resourceType, Version version) {

		StringBuilder pathTemplate = new StringBuilder();
		pathTemplate.append("classpath:/").append(ROOT_DIRECTORY).append("/").append(resource).append("/").append(resourceType).append("/").append(version).append(EXTENSION);
		return pathTemplate.toString();
	}

	public List<Version> getAvailableVersionForResource(String resource, String resourceType) throws TechnicalException {
		List<Version> availabelResources = new ArrayList<Version>();

		ClassLoader cl = this.getClass().getClassLoader();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);

		try {
			// TODO : pas propre : voir pour réutiliser
			// getPathTemplateRootDirecotry
			Resource[] templates = resolver.getResources("classpath*:" + ROOT_DIRECTORY + "/" + resource + "/" + resourceType + "/*" + EXTENSION);

			for (int i = 0; i < templates.length; ++i) {

				try {
					String fileName = FilenameUtils.getBaseName(templates[i].getFilename());
					// String strVersion = fileName.split("-")[0];
					Version version = API_VERSION_UTIL.getVersion(fileName);
					availabelResources.add(version);
				} catch (BusinessException e) {
					String message = "Impossible de transformer le nom du template en numéro de version";
					LOGGER.error(message, e);
				}
			}
		} catch (IOException e) {
			String l_message = "Impossible d'atteindre le répertoire de template [ressource=" + resource + "]";
			LOGGER.error(l_message, e);
			throw new TechnicalException(l_message, e);
		}

		return availabelResources;
	}

	public String resolveTemplateVersionInTermsOf(String resource, ResourceType resourceType, Version requestedVersion) throws BusinessException {
		String templatePath = "";

		try {
			List<Version> availabelVersions = this.getAvailableVersionForResource(resource, resourceType.toString());
			Version version = API_VERSION_UTIL.checkIfVersionOrPreviousIsContains(requestedVersion, availabelVersions);
			templatePath = this.getPathTemplateRootDirectory(resource, resourceType.toString(), version);

		} catch (TechnicalException e) {
			String message = "Impossible de trouver le template demandé [resource=" + resource + ", requestedVersion=" + requestedVersion + "]";
			LOGGER.error(message);
			throw new BusinessException(message, e);
		}

		return templatePath;
	}
}
