/**
 * 
 */
package nl.xup.template.freemarker;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import junit.framework.TestCase;
import nl.xup.template.Bindings;
import nl.xup.template.SimpleBindings;
import nl.xup.template.Template;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;
import nl.xup.template.TemplateEngineFactory;
import nl.xup.template.UnboundPropertyException;

/**
 * @author amd32857 (Minto van der Sluis)
 */
public class FreemarkerTemplateEngineTest extends TestCase {

	// ----------------------------------------------------------------------
	// Attributes
	// ----------------------------------------------------------------------
	TemplateEngine _engine = null;
	
	// ----------------------------------------------------------------------
	// Overloaded methods
	// ----------------------------------------------------------------------

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Retrieve the engine from the factory.
		_engine = TemplateEngineFactory.getEngine( "Freemarker" );
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		// Clean up the engine.
		_engine = null;
	}
	
	// ----------------------------------------------------------------------
	// Test cases
	// ----------------------------------------------------------------------

	public void testGetFreemarkerTemplate() throws IOException, TemplateCompilationException, UnboundPropertyException {
		assertNotNull( _engine );
		assertTrue( _engine instanceof FreemarkerTemplateEngine );
		
		// Read template resource.
		String templateName = "nl/xup/template/freemarker/valid.fm";
		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream( templateName );
		Reader reader = new InputStreamReader( stream );
		
		Template template = _engine.createTemplate( reader );
		assertNotNull( template );
		assertTrue( template instanceof FreemarkerTemplate );
		
		// Set up the proper binding.
		Bindings bindings = new SimpleBindings();
		bindings.put( "naam", "aap" );
		bindings.put( "x", "mij" );
		bindings.put( "notUsed", "x" );
		
		// Check results.
		assertEquals( "Hallo aap, met mij!", template.execute( bindings ) );
		
		// Same check but with the bindings passed in differently.
		template.setBindings( bindings );
		assertEquals( "Hallo aap, met mij!", template.execute() );
		template.setBindings( null );
		
		// Same template bound differently
		bindings.put( "naam", "noot" );
		bindings.remove( "notUsed" );
		
		// Check results again.
		assertEquals( "Hallo noot, met mij!", template.execute( bindings ) );
		
		// Remove all bindings.
		template.setBindings( null );
		bindings.clear();
		
		// Check results without any binding.
		try {
			template.execute( bindings );
			assertTrue( "Exception expected", false );
		} catch ( UnboundPropertyException ex ) {
			assertEquals( "naam", ex.getPropertyName() );
		}
	}

	public void testInvalidTemplate() throws IOException {
		// Read template resource.
		String templateName = "nl/xup/template/freemarker/invalid.fm";
		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream( templateName );
		Reader reader = new InputStreamReader( stream );

		try {
			_engine.createTemplate( reader );
			assertTrue( "Exception expected", false );
		} catch ( TemplateCompilationException ex ) {
			assertTrue( true );
		}
	}
}
