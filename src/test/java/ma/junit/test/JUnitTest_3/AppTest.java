package ma.junit.test.JUnitTest_3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {

	@Test
	public void test_1() {
		// Créer un mock
		App app = Mockito.mock(App.class);

		// Afficher la valeur : appName
		System.out.println("test_1 - Valeur : " + app.getAppName());

		// Modifier la valeur de appName par le setter
		app.setAppName("Nouveau nom");
		System.out.println("test_1 - Valeur : " + app.getAppName());

		// Forcer la valeur qui doit s'afficher
		Mockito.when(app.getAppName()).thenReturn("Nom forcé");
		System.out.println("test_1 - Valeur : " + app.getAppName() + "\n");
	}

	@Test
	public void test_2() {
		// Créer un mock
		App app = Mockito.mock(App.class);

		// Rétablir le comportement de getAppName
		Mockito.when(app.getAppName()).thenCallRealMethod();

		// Rétablir le comportement de setAppName
		Mockito.doCallRealMethod().when(app).setAppName(Mockito.anyString());

		// Modifier la valeur par le setter
		app.setAppName("Nom modifié par le Setter");
		System.out.println("test_2 - Valeur : " + app.getAppName() + "\n");
	}

	@Test
	public void test_3() {
		// Créer un Spy
		App app = Mockito.spy(new App());

		// Forcé la valeur retourné
		Mockito.doReturn(5).when(app).getIncrementVersion();

		// Modifié la valeur de l'objet réel
		app.setIncrementVersion(10);

		// affiche "5" mais la vraie valeur dans la classe App vaut bien "10"
		System.out.println("test_3 - Nombre de l'incrémentation de la version : " + app.getIncrementVersion() + "\n");
	}

	@Test
	public void verify_1() {
		App app = Mockito.mock(App.class);
		app.findAppByName("ABC"); // OK - BUG si cette ligne est commentée. Exception -> WANTED BUT NOT INVOKED
		// app.findAppByName("ABCD"); // BUG. Exception -> WANTED BUT NOT INVOKED

		// Vérifier est ce que la méthode findAppByName a été appelée avec un paramétre String = ABC
		Mockito.verify(app).findAppByName(Mockito.eq("ABC"));
		Mockito.verify(app).findAppByName("ABC");
	}

	@Test
	public void verify_2() {
		App app = Mockito.mock(App.class);
		// app.findAppByName("ABC"); BUG - Méthode ne doit pas être appélé ici! Exception -> NEVER WANTED BUT INVOKED
		// Vérifier est ce que la méthode findAppByName n'a pas été appelée
		Mockito.verify(app, Mockito.never()).findAppByName("ABC");
	}

	@Test
	public void verify_3() {
		App app = Mockito.mock(App.class);
		app.getAppName();
		app.getAppName();
		// app.getAppName();
		// Verifier que la méthode findAppByName a été appelé exactement 2 fois, NI PLUS NI MOINS !
		Mockito.verify(app, Mockito.times(2)).getAppName();
	}

	@Test
	public void verify_4() {
		App app = Mockito.mock(App.class);
		app.getAppName();
		app.getAppName();
		// app.getAppName();
		// Verifier que la méthode findAppByName a été appelé au moins 1 seule Fois et au max 2 fois
		Mockito.verify(app, Mockito.atLeast(1)).getAppName(); // Appelé au moins UNE SEULE FOIS
		Mockito.verify(app, Mockito.atMost(2)).getAppName(); // Appelé au max DEUX FOIS
	}

	@Test
	public void verify_5() {
		App app1 = Mockito.mock(App.class);
		App app2 = Mockito.mock(App.class);

		app1.setAppName("first ndame");
		app2.setAppName("second name");

		// Permet de verifier que les méthodes sont éxécutées dans l'ordre définit auparavant
		InOrder order = Mockito.inOrder(app1, app2);

		order.verify(app1).setAppName("first ndame");
		order.verify(app2).setAppName("second name");
	}

	public int getFive() {
		return 4;
	}

	@Test
	public void verify_9() {
		// Le Spy utilise les méthodes réels
		App app = Mockito.spy(App.class);
		app.setAppName("NAME");
		System.out.println(app.getAppName());
		assertEquals(getFive(), 5);
	}

}
