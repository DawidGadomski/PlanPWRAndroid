package pl.pwr.edu.s241223.planpwr.AndroidArchitecture;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_card_table",
        foreignKeys = {
        @ForeignKey(
                entity = Subject.class,
                parentColumns = "id",
                childColumns = "subject_id"
        ),


})
public class TestCard {
    @PrimaryKey(autoGenerate = true)
    private int testID;
    private String testInfo;
    @ColumnInfo(name = "subject_id")
    private int subjectID;

    public TestCard(){}

    public TestCard(String testName, String testDate){
        this.testInfo = testDate + " - " + testName;

    }

    public String getTestInfo() {
        return this.testInfo;
    }

    public int getTestID() {
        return testID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public void setTestInfo(String testInfo) {
        this.testInfo = testInfo;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }
}
