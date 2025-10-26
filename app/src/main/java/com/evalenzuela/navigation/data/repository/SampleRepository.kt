package com.evalenzuela.navigation.data.repository
import com.evalenzuela.navigation.data.model.Item
class SampleRepository {

    private val items = mutableListOf(
        Item(
            id = 1,
            title = "Sopaipillas.",
            description = "Ingredientes: " +
                    "-1 mitad de zapallo\n" +
                    "-2 tazas de harina\n" +
                    "-2 cucharadas de polvos de hornear\n" +
                    "-1 cucharada de sal\n" +
                    "-1 taza de agua\n" +
                    "-1 cucharada de aceite de oliva\n"
        ),
        Item(
            id = 2,
            title = "Panqueques",
            description = "Ingredientes: "+
                    "-1 huevo\n" +
                    "-250 ml de leche\n" +
                    "-40gr de mantequilla\n" +
                    "30gr de azucar\n" +
                    "-8gr de levadura\n" +
                    "-1 cucharada de sal"
        ),
        Item(
            id = 3,
            title = "Queque",
            description = "Ingredientes: " +
                    "-4 huevos\n" +
                    "-3 cucharadas de margarina\n" +
                    "-2 tazas de azucar\n" +
                    "-1 taza de leche\n" +
                    "-3 tazas de harina"
        ),
        Item(
            id = 4,
            title = "Calzones rotos",
            description = "Ingredientes: " +
                    "-3 tazas de harina sin polvos de hornear\n" +
                    "-1/2 taza de azucar flor\n" +
                    "-1 1/2 cucharada de polvos de hornear\n" +
                    "-60 gramos de mantequilla blanda\n" +
                    "-2 huevos\n" +
                    "-1 cucharada de extracto de vainilla\n" +
                    "-1 chucharada de sal\n" +
                    "-agua tibia\n" +
                    "-Aceite o manteca para freir"
        ),
        Item(
            id = 5,
            title = "Trufas de chocolate",
            description = "Ingredientes:" +
                    "-400gr de chocolate semi amargo\n" +
                    "-150ml de crema\n" +
                    "-100gr de mantequilla sin sal, a temperatura hambiente\n" +
                    "-Chocolate granulado"
        )
    )

    fun getAll(): List<Item> = items

    fun getById(id: Int): Item? = items.find { it.id == id }

    fun addItem(item: Item) {
        items.add(item)
    }

    fun updateItem(id: Int, newItem: Item) {
        val index = items.indexOfFirst { it.id == id }
        if (index != -1) {
            items[index] = newItem
        }
    }

    fun deleteItem(id: Int) {
        items.removeAll { it.id == id }
    }
}
