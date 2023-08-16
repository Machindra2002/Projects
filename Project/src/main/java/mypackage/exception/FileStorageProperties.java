package mypackage.exception;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

	 private String uploadDir;

	    public FileStorageProperties() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getUploadDir() {
	        return uploadDir;
	    }

	    public void setUploadDir(String uploadDir) {
	        this.uploadDir = uploadDir;
	    }
}
