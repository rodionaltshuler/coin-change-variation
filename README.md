
## Composition
sample coding assigment

#### Problem statement:
How to compose some number from predefined numbers available (i.e. how to compose 20 from 2 and 5), using the smallest numbers quantity possible?
 
#### Solution
It's a variation of 'Coin change problem' `https://www.algorithmist.com/index.php/Coin_Change` plus BFS  `https://en.wikipedia.org/wiki/Breadth-first_search` as we're interested in 'shortest path' possible. 
So overview of the solution is the following:
   
1. We're constructing solution tree of nodes, each keeping value (`coin` used) and link to it's parent, so we can reconstruct the whole path later.
2. For each node, we're constructing 2 child nodes for each remaining coin available, dividing each time to two subsets of possible solutions: one using particular `coin` and second is not.
3. We're keeping all the nodes in priority queue, each time selecting the node with minimal `change remained` (closest one to possible solution) to construct child nodes to put in a same priority queue. 
4. The process of searching a solution continues iteratively till of the outcomes:
   <br>A) solution found - we found a node with `change remaining` == 0
   <br>B) solution not found - queue is empty, but no nodes with `change remaining` == 0 found so far.
   
5. Having solution representing a node with a link to parent (and so on), we're reconstructing whole solution.  

#### Business application
Problem statement formulated as a business goal to receive an order represented by some item and quantity, as a combination of packs.
For each item, own set of packs is available.

#### Input
Solution is a command-line java application, expecting two arguments: item quantity and item code:
`./gradlew run --args='10 VS5'`

#### Output
Output is a total order amount, followed by quantity by packs with it's quantity, contents and price per each:

`10 VS5 $17.98` <br>
     `2 x 5 $8.99`

#### Launch commands
Project supplied with a gradle wrapper, so you only need Java8 to run application.
From the root folder of the project, use the following commands:

1. Run requesting order composition for 10 VS5 items 
`./gradlew run --args='10 VS5'`

2. Run tests
`./gradlew run`

#### License
````
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