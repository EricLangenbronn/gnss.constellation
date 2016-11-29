package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.io.File;
import java.util.List;
import java.util.Properties;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public interface ISp3FileDao {

	public List<String> getListSp3FileName(String pathSp3Dir) throws TechnicalException, BusinessException;

	public List<File> getListSp3File(String pathSp3Dir) throws TechnicalException, BusinessException;
}
