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

import java.util.*

data class Order(val item : Item, val totalQuantity : Int, val totalAmount : Double, val packs : Map<Pack, Int> = Collections.emptyMap()) {

    fun isEmpty() = totalQuantity == 0 && totalAmount == 0.0 && packs.isEmpty()

    override fun toString() = "${totalQuantity} ${item.code} $${totalAmount} \n " +
            packs.map { "      ${it.value} x ${it.key.count} $${it.key.price}"}.joinToString (separator = "\n")
}