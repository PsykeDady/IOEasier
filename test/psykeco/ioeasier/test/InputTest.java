package psykeco.ioeasier.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static psykeco.ioeasier.io.Input.*;

import java.io.ByteArrayInputStream;

class InputTest {

	@Test
	void leggiIntTest() {
		int i=10;
		ByteArrayInputStream in = new ByteArrayInputStream(Integer.toString(i).getBytes());
		System.setIn(in);
		assertEquals(10,leggiInt());
	}
	
	@Test
	void leggiCharTest() {
		String inputline="Ciao";
		ByteArrayInputStream in = new ByteArrayInputStream(inputline.getBytes());
		System.setIn(in);
		assertEquals(inputline.charAt(0),leggiChar());
	}
	
	@Test
	void leggiFraseTest() {
		String inputline="questo è un test\nciao";
		ByteArrayInputStream in = new ByteArrayInputStream(inputline.getBytes());
		System.setIn(in);
		assertEquals(inputline.substring(0,inputline.indexOf('\n')),leggiFrase());
	}
	
	@Test
	void leggiParolaTest() {
		String inputline="questo è un test\n";
		ByteArrayInputStream in = new ByteArrayInputStream(inputline.getBytes());
		System.setIn(in);
		assertEquals(inputline.substring(0,inputline.indexOf(' ')),leggiParola());
	}
	
	@Test
	void leggiBoolTest() {
		String inputline="True";
		ByteArrayInputStream in = new ByteArrayInputStream(inputline.getBytes());
		System.setIn(in);
		assertEquals(Boolean.parseBoolean(inputline),leggiBool());
	}
	
	

}
