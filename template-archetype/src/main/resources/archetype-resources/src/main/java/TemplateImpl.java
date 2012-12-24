package ${groupId}.${artifactId};

import java.util.HashMap;
import java.util.Map;

import nl.xup.template.Bindings;
import nl.xup.template.SimpleBindings;
import nl.xup.template.Template;
import nl.xup.template.UnboudPropertyException;


/**
 * TODO
 */
public class TemplateImpl implements Template {

	// ----------------------------------------------------------------------
	// Attributes
	// ----------------------------------------------------------------------

	// TODO: Replace type 'String' with the real native template type.
	private String _nativeTemplate = null;
	private Bindings _bindings = new SimpleBindings();
	
	// ----------------------------------------------------------------------
	// Constructors
	// ----------------------------------------------------------------------

	TemplateImpl ( String nativeTemplate ) {
		_nativeTemplate = nativeTemplate;
	}
	
	// ----------------------------------------------------------------------
	// Implementing: TemplateEngine Interface 
	// ----------------------------------------------------------------------

	public Bindings getBindings() {
		// Prevent exposure of internal structure. Copy the
		// internal bindings to a fresh instance and return
		// that new instance.
		return new SimpleBindings( _bindings );
	}

	public void setBingings(Bindings bindings) {
		// Prevent exposure of internal structure. So copy
		// the bindings into a new set of bindings.
		Bindings newBindings = new SimpleBindings( bindings );
		
		// The bindings where correct, replace the existing
		// set with the new ones.
		_bindings = newBindings;
	}
	
	public String execute() throws UnboudPropertyException {
		return execute( null );
	}

	public String execute(Bindings bindings) throws UnboudPropertyException {
		String result = null;
		
		// Build a map with all bindings, making sure the specified bindings
		// take precedence.
		Bindings usedBindings = new SimpleBindings( _bindings );
		if ( bindings != null ) {
			usedBindings.putAll( bindings );
		} 
				
		try {
			// TODO: Do the native template processing magic here.
			result = _nativeTemplate;
		} catch ( Exception ex) {
			// TODO: Transform native exception in to generic 
			// template exceptions.
			UnboudPropertyException upEx = new UnboudPropertyException(
					"Unboud property: " + "Unknown", ex );
			upEx.setProperty( "Unknown" );
			throw upEx;
		}
		
		return result;
	}
}
