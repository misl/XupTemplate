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
 * This exception indicates the presence of unbound properties during template
 * excecution. Unbound properties are properties for which no binding is present
 * even though they are used in the template. In other words, this exception
 * indicates a missing binding or a template error.
 * 
 * @author Minto van der Sluis
 */
public class UnboundPropertyException extends Exception {

  private static final long serialVersionUID = 1L;

  // ----------------------------------------------------------------------
  // Attributes
  // ----------------------------------------------------------------------

  private String propertyName = null;

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  /**
   * Creates an instance of this exception.
   * 
   * @param property
   *          the property name that was unbound.
   * @param message
   *          the error message.
   * @param throwable
   *          passing through lowlevel exceptions.
   */
  public UnboundPropertyException( String property, String message, Throwable throwable ) {
    super( message, throwable );
    propertyName = property;
  }

  // ----------------------------------------------------------------------
  // Getters and Setters
  // ----------------------------------------------------------------------

  /**
   * Gives the name of an unbound property.
   * 
   * @return String with the property name.
   */
  public final String getPropertyName() {
    return propertyName;
  }
}
