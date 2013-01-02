/*
 * This file is part of XupTemplate.
 *
 * XupTemplate is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * XupTemplate is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with XupTemplate.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.xup.template;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;

import javax.imageio.spi.ServiceRegistry;

import nl.xup.template.osgi.TemplateEngineServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Template API entry class that gives access to available Template Engines.
 * 
 * Pattern: Singleton
 * 
 * @author Minto van der Sluis
 */
public final class TemplateEngineFactory {

  // ----------------------------------------------------------------------
  // Static Attributes
  // ----------------------------------------------------------------------

  private static final Logger LOG = LoggerFactory.getLogger( TemplateEngineFactory.class );

  private static TemplateEngineServiceImpl service = null;

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  /**
   * Private scope to prevent instantiation.
   */
  private TemplateEngineFactory() {
  }

  // ----------------------------------------------------------------------
  // Static Methods / Interface (public scope)
  // ----------------------------------------------------------------------

  /**
   * Get the EngineFactory for the template engine name given.
   * 
   * @param name
   *          of the TemplateEngine to be retrieved.
   * @return TemplateEngine for the given engine name or null for an unknown
   *         engine name.
   */
  public static TemplateEngine getEngine( String name ) {
    return getService().getEngine( name );
  }

  /**
   * Gets the collection with all available template engines.
   * 
   * @return A collection of available template engines. If there are no
   *         template engines availabel the collection will be empty.
   */
  public static Collection<TemplateEngine> getEngines() {
    return getService().getEngines();
  }

  /**
   * Convenience method that immediately creates a Template based on the actual
   * template file/reader. To accomplish this the TemplateEngine is determined
   * by the contents of the template file/reader. That particular template
   * engine is used to create the Template.
   * 
   * TODO: describe shebang
   * 
   * @param reader
   *          The reader to be used for the Template
   * @return A valid Template object for the given reader.
   * @throws IOException
   *           when using the reader fails
   * @throws TemplateCompilationException
   *           when compiling the template fails.
   * @throws MissingShebangException
   *           when the template lacks a shebang.
   * @throws UnknownEngineException
   *           when the shebang refers to an engine that is not known to the
   *           engine factory.
   */
  public static Template getTemplate( Reader reader ) throws IOException,
      TemplateCompilationException, MissingShebangException, UnknownEngineException {
    return getService().getTemplate( reader );
  }

  // ----------------------------------------------------------------------
  // Methods / Interface (public scope)
  // ----------------------------------------------------------------------

  /**
   * Disposes the current singleton instance. Static methods will automatically
   * recreate a new singleton instance.
   */
  public void dispose() {
    // Check if we already have a service.
    if( service != null ) {
      // We do have a service. Get rid of it.
      service = null;
      LOG.info( "Closed" );
    }
  }

  // ----------------------------------------------------------------------
  // Private Methods
  // ----------------------------------------------------------------------

  /**
   * Get the singleton TemplateEngineFactory service.
   * 
   * @return the singleton service.
   */
  private static synchronized TemplateEngineServiceImpl getService() {
    // Check if we already have a service.
    if( service == null ) {
      // Nope, not yet. So create one.
      service = new TemplateEngineServiceImpl();
      LOG.info( "Starting..." );
      engineSearch();
      LOG.info( "Started" );
    }

    // Return the singleton factory instance.
    return service;
  }

  /**
   * Searches the class path for installed template engines.
   */
  private static void engineSearch() {
    // Java 6 has java.util.ServiceLoader to load services. However,
    // since we want to be java 5 compatible and do not want to be
    // dependent sun.* (sun.misc.Service) classes we will be using
    // javax.imageio.spi.ServiceRegistry instead. Have a look at:
    // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4640520
    Iterator<TemplateEngine> ps = ServiceRegistry.lookupProviders( TemplateEngine.class );

    // Add discovered engines to our list of engines one by one.
    while( ps.hasNext() ) {
      TemplateEngine engine = ps.next();
      getService().bind( engine, null );
    }
  }
}