package fr.gnss.constellation.ouranos.librairy.almanach;

import org.apache.commons.lang3.StringUtils;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;

public enum OrbitType {
	clk, cls, erp, sp3, sum;

	/**
	 *  Returns an OrbitType with a value represented by the specified string. 
	 *  
	 * @param p_type - the string to be converted
	 * @return the string representation of the specified OrbitType
	 * @throws BusinessException - if the string is null, empty or not references
	 */
	public static OrbitType stringToOrbitType(String p_type) throws BusinessException {
		if (StringUtils.isBlank(p_type)) {
			String message = "Le type de l'orbite doit être renseigné. [type=" + p_type + "]";
			throw new BusinessException(message);
		}

		if (p_type.length() != 3) {
			String message = "La taille du paramètre pour un OrbitType doit être de 3";
			throw new BusinessException(message);
		}

		OrbitType l_res;
		switch (p_type) {
		case "clk":
			l_res = OrbitType.clk;
			break;
		case "cls":
			l_res = OrbitType.cls;
			break;
		case "erp":
			l_res = OrbitType.erp;
			break;
		case "sp3":
			l_res = OrbitType.sp3;
			break;
		case "sum":
			l_res = OrbitType.sum;
			break;
		default:
			throw new BusinessException(
					"Le paramètre ne peut pas être convertit en OrbitType,  OrbitType inexistant [type=" + p_type
							+ "]");
		}

		return l_res;
	}
}
