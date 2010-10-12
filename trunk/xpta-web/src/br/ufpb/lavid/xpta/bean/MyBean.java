package br.ufpb.lavid.xpta.bean;

//package mypackage;

import java.io.File;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import net.balusc.util.FileUtil;

public class MyBean {

    // Init ---------------------------------------------------------------------------------------

    private UploadedFile uploadedFile;
    private String fileName;

    // Actions ------------------------------------------------------------------------------------

    public void submit() {

        // Just to demonstrate what information you can get from the uploaded file.
        System.out.println("File type: " + uploadedFile.getContentType());
        System.out.println("File name: " + uploadedFile.getName());
        System.out.println("File size: " + uploadedFile.getSize() + " bytes");

        try {
            // Precreate an unique file and then write the InputStream of the uploaded file to it.
            String uploadedFileName = FileUtil.trimFilePath(uploadedFile.getName());
            File uniqueFile = FileUtil.uniqueFile(new File("/upload"), uploadedFileName);
            FileUtil.write(uniqueFile, uploadedFile.getInputStream());
            fileName = uniqueFile.getName();

            // Show succes message.
            FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                FacesMessage.SEVERITY_INFO, "File upload succeed!", null));

        } catch (IOException e) {

            // Show error message.
            FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "File upload failed with I/O error.", null));

            // Always log stacktraces.
            e.printStackTrace();
        }
    }

    // Getters ------------------------------------------------------------------------------------

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public String getFileName() {
        return fileName;
    }

    // Setters ------------------------------------------------------------------------------------

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

}
