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

class composeOrderTest {

    @Test
    fun `quantity in packs matches order params`() {
        val quantity = 5
        val orderParams = OrderParams(Item.VS5, quantity)

        val order = composeOrder(orderParams)
        val totalQuantityInPacks = order.packs.map { it.key.count * it.value }.sum()
        assert(order.totalQuantity == totalQuantityInPacks) { "Total quantity expected: $quantity, actual quantity summing packs: $totalQuantityInPacks" }
    }

    @Test
    fun `total quantity matches expected`() {
        val quantity = 5
        val orderParams = OrderParams(Item.VS5, quantity)

        val order = composeOrder(orderParams)
        assert(order.totalQuantity == quantity) { "Total quantity expected: $quantity, actual total quantity of order: ${order.totalQuantity}" }
    }

    @Test
    fun `return empty order if order quantity less then smallest pack`() {
        val minPack : Int = Item.CF.packs.map { it.count }.min()!!
        val quantity = minPack - 1
        require(minPack > 0)

        val orderParams = OrderParams(Item.CF, quantity)
        val order = composeOrder(orderParams)

        assert(order.isEmpty()) { "If order unable to compose, empty order should be returned"}
    }

    @Test
    fun `order composed of biggest packs possible`() {
        val maxPack : Int = Item.CF.packs.map { it.count }.max()!!
        val expectedPacksCount = 100
        val quantity = maxPack * expectedPacksCount
        val orderParams = OrderParams(Item.CF, quantity)

        val order = composeOrder(orderParams)
        assert(order.packs.size == 1) { "The order expected to consist from single pack type, actual pack types found: ${order.packs.size}"}
        val pack = order.packs.iterator().next()
        assert(pack.key.count == maxPack) { "Expected packs of size ${maxPack} in the order, actual ${pack.key.count}"}
        assert(pack.value == expectedPacksCount) { "Expected packs count is ${expectedPacksCount}, actual ${pack.value}"}
    }

    @Test
    fun `big order composed of various packs`() {
        val expectedPacks : Map<Pack, Int> = mapOf(Item.VS5.packs[0] to 2, Item.VS5.packs[1] to 100)
        val orderParams = OrderParams(Item.VS5, 2 * 3 + 5 * 100)
        val order = composeOrder(orderParams)
        assert(order.packs == expectedPacks)
    }

    @Test
    fun `compose order quantity=16 using packs 3 and 5`() {
        val expectedPacks : Map<Pack, Int> = mapOf(Item.VS5.packs[0] to 2, Item.VS5.packs[1] to 2)
        val orderParams = OrderParams(Item.VS5, 16)
        val order = composeOrder(orderParams)
        assert(order.packs == expectedPacks)
    }

    @Test
    fun `composing order quantity=4 is impossible from 3,5,9`() {
        val orderParams = OrderParams(Item.CF, 4)
        val order = composeOrder(orderParams)
        assert(order.isEmpty()) { "Order of quantity 4 is unable to compose from packs [3, 5, 9] - empty order expected"}
    }

}

