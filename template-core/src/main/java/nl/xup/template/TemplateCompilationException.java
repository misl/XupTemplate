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

/**
 * This exception indicates template compilation fialed, probably due to
 * template errors.
 * 
 * @author Minto van der Sluis
 */
@SuppressWarnings( "serial" )
public class TemplateCompilationException extends Exception {

  // ----------------------------------------------------------------------
  // Attributes
  // ----------------------------------------------------------------------

  private int line = -1;
  private int column = -1;

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  /**
   * Contructs a TemplateCompilationException with the given underlying cause.
   * 
   * @param throwable
   *          the underlying Throwable that caused this exception to be raised.
   */
  public TemplateCompilationException( Throwable throwable ) {
    super( throwable );
  }

  /**
   * Contructs a TemplateCompilationException with the location of the error and
   * the given underlying cause.
   * 
   * @param errorLine
   *          the line number where the underlying cause took place.
   * @param errorColumn
   *          the column number where the underlying cause took place.
   * @param throwable
   *          the underlying Throwable that caused this exception to be raised.
   */
  public TemplateCompilationException( int errorLine, int errorColumn, Throwable throwable ) {
    super( throwable );
    line = errorLine;
    column = errorColumn;
  }

  // ----------------------------------------------------------------------
  // Public interface
  // ----------------------------------------------------------------------

  /**
   * Gives the line number on which the template compilation failed.
   * 
   * @return Integer with the line number where template compilation failed. If
   *         the line number is unknown -1 will be returned.
   */
  public final int getLine() {
    return line;
  }

  /**
   * Gives the column on which the template compilation failed.
   * 
   * @return Integer with the column number where template compilation failed.
   *         If the column number is unknown -1 will be returned.
   */
  public final int getColumn() {
    return column;
  }
}
