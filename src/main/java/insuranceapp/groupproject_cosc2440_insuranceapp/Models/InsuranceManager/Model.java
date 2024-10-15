/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager;

import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.View;

public class Model {
    private static Model model;
    private final View view;

    public Model() {
        view = new View();
    }

    public static synchronized Model getInstance() {
        if(model == null) {
            model = new Model();
        }
        return model;
    }

    public View getView() {
        return view;
    }
}
