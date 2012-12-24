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

/**
 * Factory for creating template objects template scripts.  
 * 
 * @author Minto van der Sluis
 */
public interface TemplateEngine {

	/**
	 * Return the name of the template engine.
	 * @return String containing the engine name.
	 */
	String getName();
	
	/**
	 * Creates a new template from the given reader. The
	 * reader should refer to a valid template script.
	 * 
	 * @param reader template script to be processed.
	 * @return Template object with the compiled template script.
	 * @throws IOException
	 * @throws TemplateCompilationException
	 */
	Template createTemplate( Reader reader )
		throws IOException, TemplateCompilationException;
}
