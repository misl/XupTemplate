package nl.xup.template;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import junit.framework.TestCase;

public class SimpleBindingsTest extends TestCase {

	// ----------------------------------------------------------------------
	// Test cases
	// ----------------------------------------------------------------------

	public void testDefaultConstructor() {
		Bindings bindings = new SimpleBindings();
		
		assertNotNull( bindings );
		assertEquals( 0, bindings.size() );
	}
	
	public void testFailingMergeConstructor() {
		Bindings bindings = null;
		try {
			bindings = new SimpleBindings( null );
			assertFalse( "NullPointerException expected", true );
		} catch ( NullPointerException npe ) {
			assertTrue( true );
		}
		
		// test map with null key 
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( null, this );
		try {
			bindings = new SimpleBindings( mapje );
			assertFalse( "NullPointerException expected", true );
		} catch ( NullPointerException npe ) {
			assertTrue( true );
		}

		// test map with empty key
		mapje.clear();
		mapje.put( "", this );
		try {
			bindings = new SimpleBindings( mapje );
			assertFalse( "IllegalArgumentException expected", true );
		} catch ( IllegalArgumentException npe ) {
			assertTrue( true );
		}
	}

	public void testValidMergeConstructor() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );

		assertEquals( 1, bindings.size() );
	}

	public void testContainsKey() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );

		assertTrue( bindings.containsKey( "key" ) );
		assertFalse( bindings.containsKey( "missing" ) );
	}

	public void testIsEmpty() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );

		assertFalse( bindings.isEmpty() );
		bindings.clear();
		assertTrue( bindings.isEmpty() );
	}

	public void testGet() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );

		assertEquals( "value", bindings.get( "key" ) );
	}

	public void testPut() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );

		assertEquals( "value", bindings.put( "key", "noot" ) );
		assertEquals( null, bindings.put( "key2", "mies" ) );

		assertEquals( "noot", bindings.get( "key" ) );
		assertEquals( "mies", bindings.get( "key2" ) );
	}

	public void testRemove() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );

		assertEquals( "value", bindings.remove( "key" ) );
		assertFalse( bindings.containsKey( "key" ) );
		assertEquals( 0, bindings.size() );
	}

	public void testClear() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );

		bindings.clear();
		assertFalse( bindings.containsKey( "key" ) );
		assertEquals( 0, bindings.size() );
	}

	public void testContainsValue() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );

		assertTrue( bindings.containsValue( "value" ) );
		assertFalse( bindings.containsValue( "aap" ) );
	}

	public void testEntrySet() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );
		Set<Entry<String, Object>> entrySet = bindings.entrySet();

		assertEquals( entrySet.size(), mapje.size() );
	}

	public void testKeySet() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );

		assertEquals( bindings.keySet().size(), mapje.size() );
	}

	public void testValues() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );

		assertEquals( bindings.values().size(), mapje.size() );
	}

	public void testExceptions() {
		Map< String, Object> mapje = new HashMap<String, Object>();
		mapje.put( "key", "value" );
		Bindings bindings = new SimpleBindings( mapje );

		try {
			bindings.containsKey( null );
			assertFalse( "Exception expected", true );
		} catch( NullPointerException ex ) {
			assertTrue( "Exception expected", true );
		}

		try {
			bindings.containsKey( "" );
			assertFalse( "Exception expected", true );
		} catch( IllegalArgumentException ex ) {
			assertTrue( "Exception expected", true );
		}

		try {
			bindings.containsKey( Boolean.TRUE );
			assertFalse( "Exception expected", true );
		} catch( ClassCastException ex ) {
			assertTrue( "Exception expected", true );
		}
	}
}
