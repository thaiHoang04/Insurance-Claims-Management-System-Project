/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Document {
    private StringProperty id;
    private StringProperty documentName;
    private StringProperty cloudId;


    public Document(String id, String documentName, String cloudId) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.documentName = new SimpleStringProperty(this, "documentName", documentName);
        this.cloudId = new SimpleStringProperty(this, "cloudId", cloudId);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getDocumentName() {
        return documentName.get();
    }

    public StringProperty documentNameProperty() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName.set(documentName);
    }

    public String getCloudId() {
        return cloudId.get();
    }

    public StringProperty cloudIdProperty() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId.set(cloudId);
    }
}
