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

import java.lang.Exception

fun main(args: Array<String>?) {
    val message: String = when (args?.size) {
        2 -> {
            try {
                val orderArgs = parseArguments(args.get(0), args.get(1))
                composeOrder(orderArgs).toString()
            } catch (e: Exception) {
                "Error processing order: ${e.message}"
            }
        }
        else -> "Please provide exactly two arguments: quantity and item code (like '10 VS5', '14 MB11')"
    }
    println(message)
}


