/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.upload.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class MultipartParams {
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1000; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 1000; // 50MB
	private List<FileItem> DanhSachParams;
	private ServletFileUpload upload;

	@SuppressWarnings({ "unchecked"})
	public MultipartParams(HttpServletRequest request) {
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			DanhSachParams = upload.parseRequest(request);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getParameter(String name) {
		try {
			for (FileItem it : DanhSachParams) {
				if (it.isFormField()) {
					if (it.getFieldName().equals(name)) {
						return new String(it.get(), "UTF-8");
					}
				}
			}
		} catch (Exception ex) {

		}
		return "";
	}

	public FileItem getFile(String name) {
		try {
			for (FileItem it : DanhSachParams) {
				if (!it.isFormField() && it.getSize() > 0) {
					if (name.equalsIgnoreCase(it.getFieldName())) {
						return it;
					}
				}
			}
		} catch (Exception ex) {

		}
		return null;
	}
}
