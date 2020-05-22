package model


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import kotlin.collections.ArrayList

@Document
data class Board(@Id var id: String? = null, val name: String){
}
