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
 * The interface to be implemented by template implementations.
 * 
 * Through this interface it is possible execute template processing
 * and to get and set bindings to be used during execution. *  
 * 
 * @author Minto van der Sluis
 */
public interface Template {

	/**
	 * Gives the parent TemplateEngine that constructed the template.
	 * @return The template engine that created the current template.
	 */
	TemplateEngine getTemplateEngine();
	
	/**
	 * <code>getBindings</code> returns bindings stored with the template. 
	 * @return The bindings stored with the template.
	 */
	Bindings getBindings();
	
	/**
     * <code>setBindings</code> stores the specified <code>Bindings</code>.
     * These bindings will be used when processing the template.
     *
     * @param bindings The specified <code>Bindings</code>
	 * @throws IllegalArgumentException when a null value is passed in.
     */
	void setBindings( Bindings bindings );
	
	/**
	 * Processes the template and substitutes all properties/keys in the 
	 * template for the values of these keys in the bindings stored in
	 * this template.
	 * @return String containing the results of the processed template.
	 * @throws UnboundPropertyException 
	 */
	String execute() throws UnboundPropertyException, TemplateCompilationException;

	/**
	 * Processes the template and substitutes all properties/keys in the 
	 * template for the values of these keys in the bindings store in 
	 * this template and the specified <code>Bindings</code>. The specified
	 * <code>Bindings</code> take precedence over the stored ones. 
     * @param bindings The specified <code>Bindings</code>
	 * @return String containing the results of the processed template.
	 * @throws UnboundPropertyException
	 * @throws TemplateCompilationException 
	 */
	String execute( Bindings bindings ) throws UnboundPropertyException, TemplateCompilationException;
}
