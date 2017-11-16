import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.lucene.createindex.utils.Utilfile;


public class UtilfileTest {

	@Test
	public void testfilenumber() {
		Utilfile utilfile=new Utilfile();
		File docs =new File("F:/search/");
		List<String> Listfile=utilfile.getAllFiles(docs);
		assertEquals(Listfile.toArray().length, 21);
	}
	@Test
	public void testempty() {
		Utilfile utilfile=new Utilfile();
		File docs =new File("F:/searchcontent/");
		List<String> Listfile=utilfile.getAllFiles(docs);
		assertEquals(Listfile.toArray().length, 0);
	}

}
