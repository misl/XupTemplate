package nl.xup.template;

/**
 * Abstract Template implementation to be used as a base class for various
 * different Template implementations. This class contains boiler plate code
 * used by multiple implementation.
 * 
 * @author Minto van der Sluis
 */
public abstract class AbstractTemplate implements Template {

	// ----------------------------------------------------------------------
	// Attributes
	// ----------------------------------------------------------------------

	private TemplateEngine templateEngine = null;
	private Bindings bindings = new SimpleBindings();

	// ----------------------------------------------------------------------
	// Constructors
	// ----------------------------------------------------------------------

	protected AbstractTemplate ( TemplateEngine parentEngine ) 
	{
		templateEngine = parentEngine;
	}

	// ----------------------------------------------------------------------
	// Implementing: TemplateEngine Interface 
	// ----------------------------------------------------------------------

	/**
	 * Gives the TemplateEngine that created the current template.
	 * @return A valid TemplateEngine instance.
	 */
	public final TemplateEngine getTemplateEngine() {
		return templateEngine;
	}

	/**
	 * Gives the Bindings associated with the current template.
	 * @return A valid Bindings instance.
	 */
	public final Bindings getBindings() 
	{
		// Prevent exposure of internal structure. Copy the
		// internal bindings to a fresh instance and return
		// that new instance.
		return new SimpleBindings( bindings );
	}

	/**
	 * Allows for setting the Bindings to be used by the current
	 * template.
	 * @param newBindings the new set of bindings.
	 */
	public final void setBindings(Bindings newBindings) 
	{
		// Check or null value
		if ( newBindings == null ) {
			bindings.clear();
		} else {
			// Prevent exposure of internal structure. So copy
			// the bindings into a new set of bindings.
			bindings = new SimpleBindings( newBindings );
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public abstract String execute() throws UnboundPropertyException,
			TemplateCompilationException;

	/**
	 * {@inheritDoc}
	 */
	public abstract String execute(Bindings bindings) throws UnboundPropertyException,
			TemplateCompilationException;
}
