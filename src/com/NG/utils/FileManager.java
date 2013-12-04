package com.NG.utils;


public class FileManager {

	public static String getSaveFilePath() {
		if (CommonUtil.hasSDCard()) {
			return CommonUtil.getRootFilePath() + "NG/files/";
		} else {
			return CommonUtil.getRootFilePath() + "NG/files";
		}
	}
}
