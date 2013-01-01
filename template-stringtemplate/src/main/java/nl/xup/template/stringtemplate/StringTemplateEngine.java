package nl.xup.template.stringtemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.xup.template.Template;
import nl.xup.template.TemplateEngine;

/**
 * Engine for creating StringTemplates based templates.
 * 
 * @author Minto van der Sluis
 */
public final class StringTemplateEngine implements TemplateEngine {

  // -------------------------------------------------------------------------
  // Class attributes
  // -------------------------------------------------------------------------

  private static Logger logger = LoggerFactory.getLogger( StringTemplateEngine.class );

  private static final int MEMORY_SIZE_64K = 65536;

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  // -------------------------------------------------------------------------
  // OSGi Blueprint lifecycle
  // -------------------------------------------------------------------------

  public void init() {
    logger.info( "Initializaing 'StringTemplate' based TemplateEngine" );
  }

  public void destroy() {
    logger.info( "Removed 'StringTemplate' based TemplateEngine" );
  }

  // ----------------------------------------------------------------------
  // Implementing: TemplateEngine Interface
  // ----------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  public String getName() {
    return "StringTemplate";
  }

  /**
   * {@inheritDoc}
   */
  public Template createTemplate( Reader reader ) throws IOException {
    Template template = null;

    // Read in template into a string.
    BufferedReader bufReader = new BufferedReader( reader );
    StringBuffer templateBuffer = new StringBuffer();
    char[] cbuf = new char[MEMORY_SIZE_64K];
    int charsRead = 0;
    while( charsRead != -1 ) {
      charsRead = bufReader.read( cbuf, 0, MEMORY_SIZE_64K );
      if( charsRead != -1 ) {
        templateBuffer.append( cbuf, 0, charsRead );
      }
    }

    // Use this template string to create the actual template.
    org.antlr.stringtemplate.StringTemplate wrappedTemplate = new org.antlr.stringtemplate.StringTemplate();
    wrappedTemplate.setTemplate( templateBuffer.toString() );
    template = new StringTemplate( this, wrappedTemplate );

    return template;
  }
}
