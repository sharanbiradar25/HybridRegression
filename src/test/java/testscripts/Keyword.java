package testscripts;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import testcases.ContactsSmokeTest;
import testcases.NewOrg;

public class Keyword {
	public static String methodReturnResult = null;
	public static String k = "KW";

	/*****************************
	 * DischargeComplete
	 * 
	 * @throws WriteException
	 * @throws RowsExceededException
	 * @throws SQLException
	 * @throws ParseException
	 * @throws FindFailed
	 *****************************************/

	// Create New Org
	public static String CreateNewOrg() throws BiffException, InterruptedException, IOException, RowsExceededException,
			WriteException, SQLException {

		return NewOrg.CreateNewOrg();
	}

	public static String assertContactsModuleUI() throws BiffException, InterruptedException, IOException,
			RowsExceededException, WriteException, SQLException {

		return ContactsSmokeTest.assertContactsModuleUI();
	}

}
