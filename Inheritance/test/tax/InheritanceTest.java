package tax;

import static org.junit.Assert.*;

import org.junit.Test;

public class InheritanceTest {

	@Test
	public void testArt() {
		String name = "Katze";
		Art a = new Art(name);
		assertTrue(name.equals(a.getName()));
	}

	@Test
	public void testGattung() {
		String art = "Katze";
		String gattung = "felis";
		Art a = new Art(art);
		assertTrue(a instanceof Gattung);
		Gattung g = (Gattung)a;
		g.setName(gattung);
		assertNotNull(gattung.equals(g.getName()));
	}

}
