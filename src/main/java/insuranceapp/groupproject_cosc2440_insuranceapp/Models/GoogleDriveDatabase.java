/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleDriveDatabase {
    private final NetHttpTransport HTTP_TRANSPORT;
    private final String APPLICATION_NAME = "insurance-app-cosc2440";
    private final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private final String CREDENTIALS_FILES_PATH = "src/main/resources/insurance-app-cosc2440-50049e9d75a1.json";
    private GoogleCredential credential;
    private final Drive drive;

    public GoogleDriveDatabase() throws IOException, GeneralSecurityException {
        FileInputStream serviceAccountStream = new FileInputStream(CREDENTIALS_FILES_PATH);
        this.HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        this.credential = GoogleCredential.fromStream(serviceAccountStream).createScoped(Collections.singleton(DriveScopes.DRIVE));
        this.drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public String uploadFile(java.io.File filePath, String claimId, String cardNumber) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(claimId + "_" + cardNumber + "_" + filePath.getName());

        FileContent mediaContent = new FileContent("application/pdf", filePath);
        File file = drive.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        return file.getId();
    }

    public File getFile(String fileId) throws IOException {
        File file = drive.files().get(fileId).execute();
        System.out.println("Title: " + file.getName());
        System.out.println("Description: " + file.getDescription());
        System.out.println("MIME type: " + file.getMimeType());
        return file;
    }
}
