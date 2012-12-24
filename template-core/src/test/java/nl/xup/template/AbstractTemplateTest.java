package nl.xup.template;

import java.io.IOException;
import java.io.Reader;

import junit.framework.TestCase;

/**
 * @author Minto van der Sluis
 */
public class AbstractTemplateTest extends TestCase {

	// Simple TestTemplate is neede to be able to test
	// AbstractTemplate.
	static class TestTemplate extends AbstractTemplate {

		// ----------------------------------------------------------------------
		// Constructors
		// ----------------------------------------------------------------------

		protected TestTemplate ( TemplateEngine parentEngine ) 
		{
			super( parentEngine );
		}

		// ----------------------------------------------------------------------
		// Implementing: TemplateEngine Interface 
		// ----------------------------------------------------------------------

		@Override
		public String execute() throws UnboundPropertyException {
			return null;
		}

		@Override
		public String execute(Bindings bindings)
				throws UnboundPropertyException, TemplateCompilationException {
			return null;
		}
	}

	// ----------------------------------------------------------------------
	// Test cases
	// ----------------------------------------------------------------------

	public void testConstructors() {
		assertNotNull( new TestTemplate( null ) );
	}

	public void testGetTemplateEngine() {
		Template template = new TestTemplate( null );
		assertNull( template.getTemplateEngine() );
		
		// No use a valid engine
		TemplateEngine engine = new TemplateEngine() {
			// We don't care about the actual engine implementation.
			public String getName() {
				return null;
			}
			public Template createTemplate(Reader reader) throws IOException,
					TemplateCompilationException {
				return null;
			}
		};
		
		template = new TestTemplate( engine );
		assertNotNull( template.getTemplateEngine() );
		assertEquals( engine, template.getTemplateEngine() );
	}

	public void testGetBindings() {
		Template template = new TestTemplate( null );
		
		// By default there is an empty set of bindings.
		assertNotNull( template.getBindings() );
		assertTrue( template.getBindings().isEmpty() );
	}

	public void testSetBindings() {
		Template template = new TestTemplate( null );
		
		// Create a set of bindings.
		SimpleBindings bindings = new SimpleBindings();
		bindings.put( "key", "value" );
		
		template.setBindings( bindings );
		assertNotNull( template.getBindings() );
		assertFalse( template.getBindings().isEmpty() );
		
		template.setBindings( null );
		assertNotNull( template.getBindings() );
		assertTrue( template.getBindings().isEmpty() );
	}
}
