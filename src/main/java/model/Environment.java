package model;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    public EnvironmentModel test;

    public EnvironmentModel getTest() {
        return test;
    }

    public List<EnvironmentModel> getListOfEnvironments() {
        List<EnvironmentModel> listOfEnvironments = new ArrayList<>();
        listOfEnvironments.add(getTest());
        return listOfEnvironments;
    }
}
