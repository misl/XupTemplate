package nl.xup.template;

import junit.framework.TestCase;

/**
 * @author Minto van der Sluis
 */
public class UnboundPropertyExceptionTest extends TestCase {

	// ----------------------------------------------------------------------
	// Test cases
	// ----------------------------------------------------------------------

	public void testConstructors() {
		assertNotNull( new UnboundPropertyException( "", "", null) );
	}

	public void testGetterSetter() {
		UnboundPropertyException upEx = new UnboundPropertyException( null, "", null );
		
		// No property set yet.
		assertEquals( null,  upEx.getPropertyName() );
		
		// Let's do it again, but this time with a property.
		upEx = new UnboundPropertyException( "prop", "", null );
		assertEquals( "prop", upEx.getPropertyName() );
	}
}
