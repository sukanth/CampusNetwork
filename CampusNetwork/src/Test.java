import java.text.ParseException;

import com.campusnetwork.utils.DateUtils;

public class Test {

	public static void main(String[] args) {
		try {
			System.out.println(DateUtils.formatDate("10/22/2017","mm/dd/yyyy", "yyyy-mm-dd"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
