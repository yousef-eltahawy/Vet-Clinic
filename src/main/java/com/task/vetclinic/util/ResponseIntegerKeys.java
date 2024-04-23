package com.task.vetclinic.util;

public class ResponseIntegerKeys {

	public static final int OK = 200;
	public static final int NOT_FOUND = 404;
	public static final int CREATED = 201;
	public static final int DATA_INTEGRITY_VIOLATION = 1223;

	private ResponseIntegerKeys() {
		throw new IllegalStateException("Utility class");
	}
}