package br.com.vbank;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import br.com.vbank.domain.Log;
import br.com.vbank.interceptors.ServicoTeste;
import br.com.vbank.repository.LogRepositoryTest;
import br.com.vbank.repository.Repository;
import br.com.vbank.repository.impl.AbstractRepositoryImpl;
import br.com.vbank.repository.service.LogServiceTest;
import br.com.vbank.services.LogService;

@RunWith(Arquillian.class)
public abstract class AbstractTest {

	@PersistenceContext
	EntityManager em;

	@Before
	public void setup() throws Throwable {

	}

	@After
	public void tearDown() {
	}

	@Deployment(testable = true)
	public static WebArchive createTestArchive() {

		File[] deps = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeAndTestDependencies().resolve()
				.withTransitivity().asFile();

		WebArchive archive = ShrinkWrap.create(WebArchive.class, "vbank.war").addPackages(false, Log.class.getPackage())
				.addPackages(false, Repository.class.getPackage())
				.addPackages(false, AbstractRepositoryImpl.class.getPackage())
				.addPackages(false, AbstractTest.class.getPackage())
				.addPackages(false, ServicoTeste.class.getPackage())
				.addPackage(LogRepositoryTest.class.getPackage())
				.addPackage(LogService.class.getPackage())
				.addPackage(LogServiceTest.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsWebInfResource("vbank-ds.xml")
				.addAsLibraries(deps);

		return archive;
	}

}
