package fr.gnss.constellation.ouranos.librairy.almanach;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;

public enum EphemerideType {
	igl, igr, igs, igu;

	public static EphemerideType stringToEphemerideType(String type)
			throws BusinessException {
		if (type == null) {
			String message = "Le type de l'orbite doit être renseigné. [type="
					+ type + "]";
			throw new BusinessException(message);
		}

		if (type.length() != 3) {
			throw new BusinessException(
					"La taille du paramètre pour un EphemerideType doit être de 3 [type="
							+ type + "]");
		}

		EphemerideType res;
		switch (type) {
		case "igl":
			res = EphemerideType.igl;
			break;
		case "igr":
			res = EphemerideType.igr;
			break;
		case "igs":
			res = EphemerideType.igs;
			break;
		case "igu":
			res = EphemerideType.igu;
			break;
		default:
			String message = "Le paramètre ne peut pas être convertit en EphemerideType,  EphemerideType inexistant [type="
					+ type + "]";
			throw new BusinessException(message);
		}

		return res;
	}
}
