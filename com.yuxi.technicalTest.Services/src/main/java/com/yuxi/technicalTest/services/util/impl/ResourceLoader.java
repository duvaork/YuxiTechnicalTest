package com.yuxi.technicalTest.services.util.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.yuxi.technicalTest.model.ApplicationException;
import com.yuxi.technicalTest.services.util.IResourceLoader;

public class ResourceLoader implements IResourceLoader{

	public List<String> readLines(String resourceUri) throws ApplicationException {

		BufferedReader reader = null;
		ArrayList<String> fileLines = null;

		try {

			File file = new File(resourceUri);
			reader = new BufferedReader(new FileReader(file));

			fileLines = new ArrayList<String>();
			String line;

			while ((line = reader.readLine()) != null) {
				fileLines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new ApplicationException(e);
				}
			}
		}
		return fileLines;
	}
}
