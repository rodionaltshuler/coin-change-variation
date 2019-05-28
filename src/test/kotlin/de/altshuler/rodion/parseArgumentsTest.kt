/* Composition
Copyright (C) 2019  Rodion Altshuler  altshuler.rodion@gmail.com

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package de.altshuler.rodion

import org.junit.Test
import java.lang.Exception

class parseArgumentsTest {

    @Test(expected = Exception::class)
    fun `parse arguments - negative quantity not allowed`() {
        parseArguments("-1", Item.VS5.code)
    }

    @Test(expected = Exception::class)
    fun `parse aguments - zero quantity not allowed`() {
        parseArguments("0", Item.VS5.code)
    }

}

