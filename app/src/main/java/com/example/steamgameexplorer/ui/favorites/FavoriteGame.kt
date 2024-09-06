import com.google.firebase.firestore.DocumentSnapshot

data class FavoriteGame(
    val id: String,
    val imageUrl: String?,
    val isFavorite: Boolean,
    val title: String,
    val steamID: String,
) {
    constructor(doc: DocumentSnapshot) : this(
        id = doc.id,
        imageUrl = doc.getString("imageUrl"),
        isFavorite = doc.getBoolean("favorited") ?: false,
        title = doc.getString("gameTitle") ?: "",
        steamID = doc.getString("steamID") ?: ""
    )
}