package io.rybalkinsd.kotlinbootcamp.geometry
import kotlin.math.max
import kotlin.math.min
/**
 * Entity that can physically intersect, like flame and player
 */
interface Collider {
    fun isColliding(other: Collider): Boolean
}

/**
 * 2D point with integer coordinates
 */
data class Point(var x: Int,var y: Int) : Collider {
    override fun isColliding(other: Collider): Boolean {
        return when (other) {
            is Point -> {
                if((x == other.x) && (y == other.y))
                    true
                else
                    false
            }

            is Bar -> {

                if(other.firstCornerX <= x && x <= other.secondCornerX && other.firstCornerY <= y && y <= other.secondCornerY)
                    true
                else
                    false
            }
            else -> false
        }
    }
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Point -> {
                if (x == other.x && y == other.y)
                    true
                else
                    false
            }
            else
            -> false
        }
    }

}

/**
 * Bar is a rectangle, which borders are parallel to coordinate axis
 * Like selection bar in desktop, this bar is defined by two opposite corners
 * Bar is not oriented
 * (It does not matter, which opposite corners you choose to define bar)
 */
data class Bar(var firstCornerX: Int,var firstCornerY: Int,var secondCornerX: Int,var secondCornerY: Int) : Collider {

  init{
      if (firstCornerX > secondCornerX) {
          var tmp = firstCornerX
          firstCornerX = secondCornerX
          secondCornerX = tmp
      }
      if (firstCornerY > secondCornerY) {
          var tmp = firstCornerY
          firstCornerY = secondCornerY
          secondCornerY = tmp
      }
  }


    override fun isColliding(other: Collider): Boolean {
        return when (other) {
            is Point -> {
                if(firstCornerX <= other.x && other.x <= secondCornerX && firstCornerY <= other.y && other.y <= secondCornerY)
                    true
                else
                    false

            }

            is Bar -> {

                if(firstCornerX>other.secondCornerX || secondCornerX<other.firstCornerX || firstCornerY>other.secondCornerY||secondCornerY<other.firstCornerY)
                {
                         false
                }
                else
                    true
            }
            else -> false
        }
    }
}