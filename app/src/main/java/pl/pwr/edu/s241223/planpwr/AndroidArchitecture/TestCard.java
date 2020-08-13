package pl.pwr.edu.s241223.planpwr.AndroidArchitecture;

import java.io.Serializable;

public class TestCard implements Serializable {

    private String testName;
    private String testDate;

    public TestCard(){}

    public TestCard(String testName, String testDate){
        this.testName = testName;
        this.testDate = testDate;

    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
}
