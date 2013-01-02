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

package nl.xup.template.osgi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.xup.template.MissingShebangException;
import nl.xup.template.Template;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;
import nl.xup.template.TemplateEngineService;
import nl.xup.template.UnknownEngineException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the TemplateEngineService.
 * 
 * @author Minto van der Sluis
 */
public final class TemplateEngineServiceImpl implements TemplateEngineService {

  // ----------------------------------------------------------------------
  // Static Attributes
  // ----------------------------------------------------------------------

  // Read ahead buffer of 1K should be enough.
  private static final int READ_AHEAD_LIMIT = 1024;
  private static final Pattern SHEBANG_PATTERN = Pattern.compile( "#!([\\w\\.]+)" );
  private static final Logger LOG = LoggerFactory.getLogger( TemplateEngineService.class );

  // ----------------------------------------------------------------------
  // Attributes
  // ----------------------------------------------------------------------

  private Map<String, TemplateEngine> engines = new HashMap<String, TemplateEngine>();

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  public TemplateEngineServiceImpl() {
  }

  // -------------------------------------------------------------------------
  // OSGi Blueprint lifecycle
  // -------------------------------------------------------------------------

  public void init() {
    LOG.info( "Started TemplateEngineService" );
  }
  
  public void destroy() {
    LOG.info( "Stopped TemplateEngineService" );
  }
  
  // -------------------------------------------------------------------------
  // OSGi Blueprint reference binding
  // -------------------------------------------------------------------------

  public void bind( TemplateEngine engine, Map<?, ?> properties ) {
    engines.put( engine.getName(), engine );
    LOG.info( "Added '{}' engine", engine.getName() );
  }

  public void unbind( TemplateEngine engine ) {
    if( engines.remove( engine ) != null ) {
      LOG.info( "Removed '{}' engine", engine.getName() );
    }
  }

  // ----------------------------------------------------------------------
  // Implementing TemplateEngineService
  // ----------------------------------------------------------------------

  /**
   * Get the EngineFactory for the template engine name given.
   * 
   * @param name
   *          of the TemplateEngine to be retrieved.
   * @return TemplateEngine for the given engine name or null for an unknown
   *         engine name.
   */
  public TemplateEngine getEngine( String name ) {
    return engines.get( name );
  }

  /**
   * Gets the collection with all available template engines.
   * 
   * @return A collection of available template engines. If there are no
   *         template engines availabel the collection will be empty.
   */
  public Collection<TemplateEngine> getEngines() {
    return engines.values();
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
  public Template getTemplate( Reader reader ) throws IOException,
      TemplateCompilationException, MissingShebangException, UnknownEngineException {
    // Determine shebang. For this we need to read ahead
    // the first line. After reading the first line we have
    // to reset the reader to the original position.
    BufferedReader aheadReader = new BufferedReader( reader );
    aheadReader.mark( READ_AHEAD_LIMIT );
    String firstLine = aheadReader.readLine();
    aheadReader.reset();

    // Extract shebang from the first line.
    String engineName = extractEngineName( firstLine );
    if( engineName == null ) {
      throw new MissingShebangException();
    }

    // Get the template engine according to that shebang
    TemplateEngine engine = getEngine( engineName );
    if( engine == null ) {
      throw new UnknownEngineException( engineName );
    }

    return engine.createTemplate( aheadReader );
  }

  // ----------------------------------------------------------------------
  // Private methods
  // ----------------------------------------------------------------------

  /**
   * Extracts the engine name from the shebang (#!). If no shebang available
   * this method will return null.
   * 
   * @param line
   *          input line from which to extract the engine name.
   * @return String with the engine name or null if no shebang was detected in
   *         the input line.
   */
  static String extractEngineName( String line ) {
    // NOTE: Method has package scope to be able to unittest it.
    // No line no engine name
    if( line == null || line.isEmpty() ) {
      return null;
    }

    // Look for the shebang (#!) in the line and extract
    // the first word after it.
    String engineName = null;
    Matcher matcher = SHEBANG_PATTERN.matcher( line );
    if( matcher.find() ) {
      engineName = matcher.group( 1 );
    }
    return engineName;
  }
}