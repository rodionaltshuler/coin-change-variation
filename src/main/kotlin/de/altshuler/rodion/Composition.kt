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

    val (item, quantity) = args

    val packs : Set<Int> = item.packs.map { it.count }.toSet()

    // We'll use BFS (https://en.wikipedia.org/wiki/Breadth-first_search) to find a minimal number of packs our order can be composed from
    // {Node} data structure is required to keep track of packs we used to get to solution
    data class Node(val parent : Node? = null, val remaining : Int, val value : Int, val availablePacks : Set<Int>)

    val queue : Queue<Node> = PriorityQueue { x : Node, y : Node -> x.remaining - y.remaining }

    packs.forEach{
        //It's a variation of 'Coin change problem' https://www.algorithmist.com/index.php/Coin_Change, so on each step we're dividing
        //problem to two sub-problems:
        //1. subset of problems using pack {it}
        queue.add(Node(remaining = quantity, value = 0, availablePacks = packs - it))
        //2. subset of problems not using pack {it}
        queue.add(Node(remaining = quantity - it, value = it, availablePacks = packs))
    }

    fun constructPacks(node : Node) : Collection<Int> {

        fun constructPacks(acc : List<Int>, node : Node) : List<Int> {
            if (node.parent != null) {
                return constructPacks(acc + node.parent.value, node.parent)
            }
            return acc
        }

        return constructPacks(listOf(node.value), node)
    }

    tailrec fun composeOrderAcc(acc : Queue<Node>) : Node? {
        if (acc.isEmpty()) {
            //no solution
            return null
        }

        val node = acc.remove()!!
        if (node.remaining == 0) {
            //solution found
            return node
        }

        if (node.remaining > 0) {
            node.availablePacks.forEach{
                acc.add(Node(parent = node, remaining = node.remaining, value = 0, availablePacks = node.availablePacks - it))
                acc.add(Node(parent = node, remaining = node.remaining - it, value = it, availablePacks = node.availablePacks))
            }
        }

        //if node.remaining != 0 -> process to next iteration
        return composeOrderAcc(acc)
    }

    val solution = composeOrderAcc(queue)

    if (solution != null) {
        val packsQuantityBySize : Map<Int, Int> = constructPacks(solution).groupBy { it }.filter { it.key > 0 }.mapValues{ it.value.count() }
        val quantityByPack : Map<Pack, Int> = packsQuantityBySize.mapKeys { entry -> item.packs.find { it.count == entry.key }!! }
        return Order(
            item,
            quantity,
            quantityByPack.map { it.key.price * it.value }.sum(),
            quantityByPack
        )
    }

    //solution doesn't exist
    return Order(item, 0, 0.0, Collections.emptyMap())
}

