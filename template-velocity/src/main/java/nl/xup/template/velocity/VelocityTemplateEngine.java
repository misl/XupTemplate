package nl.xup.template.velocity;

import java.io.IOException;
import java.io.Reader;

import nl.xup.template.Template;
import nl.xup.template.TemplateEngine;

import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  private static Logger logger = LoggerFactory.getLogger( VelocityTemplateEngine.class );

  private static final String TEMPLATE_ENGINE_NAME = "Velocity";

  // ----------------------------------------------------------------------
  // Attributes
  // ----------------------------------------------------------------------

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  // -------------------------------------------------------------------------
  // OSGi Blueprint lifecycle
  // -------------------------------------------------------------------------

  public void init() {
    logger.info( "Initializaing 'Velocity' based TemplateEngine" );
  }

  public void destroy() {
    logger.info( "Removed 'Velocity' based TemplateEngine" );
  }

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
