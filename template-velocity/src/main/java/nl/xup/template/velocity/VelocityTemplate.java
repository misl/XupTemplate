package nl.xup.template.velocity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import nl.xup.template.AbstractTemplate;
import nl.xup.template.Bindings;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;
import nl.xup.template.UnboundPropertyException;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;

/**
 * Template API Template implementation for the Apache Velocity templating
 * engine.
 * 
 * @author Minto van der Sluis
 */
public final class VelocityTemplate extends AbstractTemplate {

  // ----------------------------------------------------------------------
  // Static Attributes
  // ----------------------------------------------------------------------

  private static final int KILOBYTE = 1024;

  // ----------------------------------------------------------------------
  // Object Attributes
  // ----------------------------------------------------------------------

  private String templateContent = null;

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  /**
   * Constructor to create a Velocity Template implementation instance.
   * 
   * @param engine
   *          TemplateEngine that created this instance.
   * @param reader
   *          content of the template.
   */
  VelocityTemplate( TemplateEngine engine, Reader templateReader ) throws IOException {
    super( engine );

    // Read the actual contents of the template.
    templateContent = getTemplateContent( templateReader );
  }

  // ----------------------------------------------------------------------
  // Implemented abstract methods from AbstractTemplate
  // ----------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  public String execute() throws UnboundPropertyException, TemplateCompilationException {
    return execute( null );
  }

  /**
   * {@inheritDoc}
   */
  public String execute( Bindings bindings ) throws UnboundPropertyException,
      TemplateCompilationException {
    // Build a map with all bindings, making sure the specified bindings
    // take precedence.
    Bindings usedBindings = getBindings();
    if( bindings != null ) {
      usedBindings.putAll( bindings );
    }

    // Create a context with out bindings.
    VelocityContext context = new VelocityContext( usedBindings );
    StringWriter writer = new StringWriter();
    StringReader reader = new StringReader( templateContent );

    try {
      Velocity.evaluate( context, writer, "template", reader );
    } catch( ParseErrorException e ) {
      throw new TemplateCompilationException( e.getLineNumber(), e.getColumnNumber(), e );
    } catch( MethodInvocationException e ) {
      throw new TemplateCompilationException( e.getLineNumber(), e.getColumnNumber(), e );
    } catch( IOException e ) {
      throw new TemplateCompilationException( e );
    } finally {
      reader.close();
    }

    return writer.toString();
  }

  // ----------------------------------------------------------------------
  // Private methods
  // ----------------------------------------------------------------------

  /**
   * A method to extract the template content into a string.
   */
  private String getTemplateContent( Reader templateReader ) throws IOException {
    BufferedReader reader = new BufferedReader( templateReader );
    StringBuilder template = new StringBuilder();
    char[] buf = new char[KILOBYTE];
    int numRead = 0;

    // Loop over the content till there is no more.
    while( (numRead = reader.read( buf )) != -1 ) {
      template.append( buf, 0, numRead );
    }
    reader.close();

    return template.toString();
  }
}
