/**
 * @author Group 14
 */
module insuranceapp.groupproject_cosc2440_insuranceapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;
    requires google.api.client;
    requires com.google.api.client;
    requires com.google.api.client.json.gson;
    requires google.api.services.drive.v3.rev197;


    opens insuranceapp.groupproject_cosc2440_insuranceapp.Application to javafx.fxml;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Application to javafx.graphics;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Models;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Views;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Dependent;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Controllers;
    opens insuranceapp.groupproject_cosc2440_insuranceapp.Models to javafx.fxml;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;
    opens insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor to javafx.fxml;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor;
    opens insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor to javafx.fxml;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager;
    opens insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager to javafx.fxml;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;
    opens insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager to javafx.fxml;
    exports insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyHolder;
}