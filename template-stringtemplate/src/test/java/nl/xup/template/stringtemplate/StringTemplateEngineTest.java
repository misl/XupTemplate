/**
 * 
 */
package nl.xup.template.stringtemplate;

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
public class StringTemplateEngineTest extends TestCase {

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
		_engine = TemplateEngineFactory.getEngine( "StringTemplate" );
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

	public void testGetStringTemplate() throws IOException, TemplateCompilationException, UnboundPropertyException {
		assertNotNull( _engine );
		assertTrue( _engine instanceof StringTemplateEngine );
		
		// Read template resource.
		String templateName = "nl/xup/template/stringtemplate/valid.st";
		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream( templateName );
		Reader reader = new InputStreamReader( stream );
		
		Template template = _engine.createTemplate( reader );
		assertNotNull( template );
		assertTrue( template instanceof StringTemplate );
		
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
		bindings.clear();
		
		// Check results without any binding. Since StringTemplate does
		// not throw any errors. Just the template is returned.
		assertEquals( "Hallo , met !", template.execute( bindings ) );
	}

	public void testInvalidTemplate() throws IOException, TemplateCompilationException, UnboundPropertyException {
		// Read template resource.
		String templateName = "nl/xup/template/stringtemplate/invalid.st";
		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream( templateName );
		Reader reader = new InputStreamReader( stream );

		// Since StringTemplate does not throw any errors. Just the 
		// template is returned.
		Template template = _engine.createTemplate( reader );
		assertEquals( "Hallo ", template.execute() );
	}
}
