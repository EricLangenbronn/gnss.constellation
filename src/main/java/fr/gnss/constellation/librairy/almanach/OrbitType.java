package fr.gnss.constellation.librairy.almanach;

import fr.gnss.constellation.Exception.BusinessException;

public enum OrbitType {
	clk, cls, erp, sp3, sum;

	public static OrbitType stringToOrbitTypeType(String p_type) throws Exception {
		if (p_type == null) {
			String message = "Le type de l'orbite doit être renseigné. [type="
					+ p_type + "]";
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
					"Le paramètre ne peut pas être convertit en OrbitType,  OrbitType inexistant [type="
							+ p_type + "]");
		}

		return l_res;
	}
}
