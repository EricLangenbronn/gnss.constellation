package fr.gnss.constellation.ouranos.librairy.almanach;

import org.apache.commons.lang3.CharUtils;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;

public enum Sp3FileType {
	a, b, c, d;

	/**
	 * Returns an Sp3FileType with a value represented by the specified
	 * character.
	 * 
	 * @param p_type
	 *            - the character to be converted
	 * @return the character representation of the specified Sp3FileType
	 * @throws BusinessException
	 *             - if the string is null, empty or not references
	 */
	public static Sp3FileType stringToSp3FileType(Character p_type) throws BusinessException {
		if (p_type == null) {
			String message = "Le type de l'orbite doit être renseigné. [type=" + p_type + "]";
			throw new BusinessException(message);
		}

		Sp3FileType l_res;
		switch (p_type) {
		case 'a':
			l_res = Sp3FileType.a;
			break;
		case 'b':
			l_res = Sp3FileType.b;
			break;
		case 'c':
			l_res = Sp3FileType.c;
			break;
		case 'd':
			l_res = Sp3FileType.d;
			break;
		default:
			throw new BusinessException(
					"Le paramètre ne peut pas être convertit en Sp3FileType,  Sp3FileType inexistant [type=" + p_type
							+ "]");
		}

		return l_res;
	}

}
