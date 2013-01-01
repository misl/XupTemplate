package nl.xup.template.velocity;

import java.io.IOException;
import java.io.Reader;

import org.apache.velocity.app.Velocity;

import nl.xup.template.Template;
import nl.xup.template.TemplateEngine;

/**
 * Engine for Apache Velocity based templates.
 * 
 * @author Minto van der Sluis
 */
public final class VelocityTemplateEngine implements TemplateEngine {

  // Apache Velocity need initialization that needs to be done only once.
  static {
    try {
      Velocity.init();
    } catch( Exception e ) {
      // We tried our best, but initialization failed.
      // TODO: improve handling
      e.printStackTrace();
    }
  }

  // ----------------------------------------------------------------------
  // Class Attributes
  // ----------------------------------------------------------------------

  private static final String TEMPLATE_ENGINE_NAME = "Velocity";

  // ----------------------------------------------------------------------
  // Attributes
  // ----------------------------------------------------------------------

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  // ----------------------------------------------------------------------
  // Implementing: TemplateEngine Interface
  // ----------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  public String getName() {
    return TEMPLATE_ENGINE_NAME;
  }

  /**
   * {@inheritDoc}
   */
  public Template createTemplate( Reader reader ) throws IOException {
    return new VelocityTemplate( this, reader );
  }
}
