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

enum class Item(val fullName : String, val code: String, val packs : List<Pack>) {

    VS5(fullName = "Vegermite Scroll", code = "VS5", packs = listOf(
        Pack(3, 6.99),
        Pack(5, 8.99)
    )
    ),
    MB11(fullName = "Blueberry Muffin", code = "MB11", packs = listOf(
        Pack(2, 9.95),
        Pack(5, 16.95),
        Pack(8, 24.95)
    )
    ),
    CF(fullName = "Croissant", code = "CF", packs = listOf(
        Pack(3, 5.95),
        Pack(5, 9.95),
        Pack(9, 16.99)
    )
    );

}

fun itemByCode(code : String) : Item = Item.values().find { it.code == code } ?:
    throw IllegalArgumentException("Can't find item with code $code")

data class Pack(val count : Int, val price : Double)

