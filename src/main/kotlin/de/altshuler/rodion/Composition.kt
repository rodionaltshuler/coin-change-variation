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
import java.util.*

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


data class OrderParams(val item: Item, val quantity: Int)

fun parseArguments(quantityString: String, code: String) : OrderParams {
    val quantity = quantityString.toInt()
    require(quantity > 0) { "Order quantity expected to be > 0"}
    return OrderParams(itemByCode(code), quantity)
}


fun composeOrder(args : OrderParams) : Order {
    //solution doesn't exist
    return Order(args.item, 0, 0.0, Collections.emptyMap())
}
