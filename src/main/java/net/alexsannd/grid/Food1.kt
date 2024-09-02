package net.alexsannd.grid

import java.awt.Color
import java.awt.Point

class Food(
    private val xPos: Int,
    private val yPos: Int,
    private val foodType: FoodType,
    private val gridController: GridController
) {
    private var color: Color? = null
    var points: Int = 0

    //TODO Implement duration and effects of food
    enum class FoodType {
        APPLE, BANANA, CHERRY, STRAWBERRY, BLUEBERRY, ORANGE, PEAR, PINEAPPLE, WATERMELON
    }

    init {
        when (foodType) {
            FoodType.APPLE -> {
                color = Color(255, 0, 0) // RGB for Red
                points = 1
            }

            FoodType.BANANA -> {
                color = Color(255, 255, 0) // RGB for Yellow
                points = 2
            }

            FoodType.CHERRY -> {
                color = Color(139, 0, 0) // RGB for Dark Red
                points = 3
            }

            FoodType.STRAWBERRY -> {
                color = Color(255, 105, 180) // RGB for Pink
                points = 4
            }

            FoodType.BLUEBERRY -> {
                color = Color(0, 0, 255) // RGB for Blue
                points = 5
            }

            FoodType.ORANGE -> {
                color = Color(255, 165, 0) // RGB for Orange
                points = 6
            }

            FoodType.PEAR -> {
                color = Color(173, 255, 47) // RGB for Green Yellow'
                points = 7
            }

            FoodType.PINEAPPLE -> {
                color = Color(218, 165, 32) // RGB for Goldenrod
                points = 8
            }

            FoodType.WATERMELON -> {
                color = Color(60, 179, 113) // RGB for Medium Sea Green
                points = 9
            }
        }
        gridController.getCell(xPos, yPos)!!.setColor(color!!)
    }

    val position: Point
        get() = Point(xPos, yPos)
}
