package fr.gnss.constellation.ouranos.service.resource;

public enum HttpHeaderType {
	json("application/json"), xml("application/xml");

	private String type;

	private HttpHeaderType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
