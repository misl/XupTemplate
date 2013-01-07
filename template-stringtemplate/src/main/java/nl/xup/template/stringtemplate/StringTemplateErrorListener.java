package nl.xup.template.stringtemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.misc.STMessage;

class StringTemplateErrorListener implements STErrorListener {

  // -------------------------------------------------------------------------
  // Class attributes
  // -------------------------------------------------------------------------

  private static Logger logger = LoggerFactory.getLogger( StringTemplateErrorListener.class );

  // -------------------------------------------------------------------------
  // Implementating STErrorListener
  // -------------------------------------------------------------------------

  @Override
  public void runTimeError( STMessage msg ) {
    logger.warn( msg.toString() );
    throw createException( msg );
  }

  @Override
  public void internalError( STMessage msg ) {
    logger.warn( msg.toString() );
    throw createException( msg );
  }

  @Override
  public void compileTimeError( STMessage msg ) {
    logger.warn( msg.toString() );
    throw createException( msg );
  }

  @Override
  public void IOError( STMessage msg ) {
    logger.warn( msg.toString() );
    throw createException( msg );
  }

  // -------------------------------------------------------------------------
  // Private methods
  // -------------------------------------------------------------------------

  private RuntimeException createException( STMessage msg ) {
    return new InternalStringTemplateException( msg );
  }
}
