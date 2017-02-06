package fr.gnss.constellation.ouranos.service.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.version.ApiVersionUtil;
import fr.gnss.constellation.ouranos.version.Version;

public class TemplateUtils {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TemplateUtils.class);

	private static final String EXTENSION = ".vm";
	private static final String ROOT_DIRECTORY = "templates";
	private static final ApiVersionUtil API_VERSION_UTIL = ApiVersionUtil.getInstance();

	public String getPathTemplateRootDirectory(String resource, Version version) {
		StringBuilder pathTemplate = new StringBuilder();
		pathTemplate.append(ROOT_DIRECTORY).append(resource).append(version).append(EXTENSION);
		return pathTemplate.toString();
	}

	public List<Version> getAvailableVersionForResource(String resource) throws TechnicalException {
		List<Version> availabelResources = new ArrayList<Version>();

		ClassLoader cl = this.getClass().getClassLoader();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);

		try {
			Resource[] templates = resolver
					.getResources("classpath*:" + ROOT_DIRECTORY + "/" + resource + "/*" + EXTENSION);

			for (int i = 0; i < templates.length; ++i) {

				try {
					Version version = API_VERSION_UTIL
							.getVersion(FilenameUtils.getBaseName(templates[i].getFilename()));
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

	public Version resolveTemplateVersionInTermsOf(String resource, Version requestedVersion)
			throws TechnicalException, BusinessException {

		List<Version> availabelVersion = this.getAvailableVersionForResource(resource);
		availabelVersion.add(requestedVersion);
		Collections.sort(availabelVersion);

		int index = availabelVersion.lastIndexOf(requestedVersion);

		Version version;
		if (index >= 1) {
			// TODO: point d'intérêt :)
			/* :D drôle comme manière de faire, non ? :D */
			version = availabelVersion.get(index - 1);
		} else {
			String message = "Aucun numéro de version n'existe pour ce template et cette version [resource=" + resource
					+ ", requestedVersion=" + requestedVersion + "]";
			LOGGER.error(message);
			throw new BusinessException(message);
		}

		return version;
	}

	public String findTemplate(String resource, Version requestedVersion) throws BusinessException {
		String templatePath = "";

		try {
			Version version = resolveTemplateVersionInTermsOf(resource, requestedVersion);
			templatePath = this.getPathTemplateRootDirectory(resource, version);

		} catch (TechnicalException e) {
			String message = "Impossible de trouver le template demandé [resource=" + resource + ", requestedVersion="
					+ requestedVersion + "]";
			LOGGER.error(message);
			throw new BusinessException(message, e);
		}

		return templatePath;
	}
}
