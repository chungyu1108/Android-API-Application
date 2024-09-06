import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore

object FirestoreHelper {
    private val db: FirebaseFirestore = Firebase.firestore

    fun getAllGames(): Task<QuerySnapshot> {
        Log.d("FirestoreHelper", "getAllGames() called")
        return db.collection("games").get()
    }

    fun deleteGame(gameTitle: String) {
        val gameDocument = db.collection("games").document(gameTitle)

        gameDocument.delete()
            .addOnSuccessListener {
                Log.d("FirestoreHelper", "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e ->
                Log.e("FirestoreHelper", "Error deleting document", e)
            }
    }

    fun updateGameFavoriteStatus(gameTitle: String, isFavorite: Boolean, imageUrl: String) {
        if (!isFavorite) {
            deleteGame(gameTitle)
        } else {
            val gameDocument = db.collection("games").document(gameTitle)

            Log.d("GameCardInfo", "Game Image URL: $imageUrl")

            val regex = Regex("/apps/(\\d+)")
            val matchResult = regex.find(imageUrl)
            val steamID = matchResult?.groups?.get(1)?.value

            val gameData = hashMapOf(
                "gameTitle" to gameTitle, // Consider using camelCase for consistency
                "favorited" to isFavorite,
                "imageUrl" to imageUrl, // Same here, camelCase is preferred in Kotlin
                "steamID" to steamID
            )

            gameDocument.set(gameData, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d("FirestoreHelper", "DocumentSnapshot successfully updated!")
                }
                .addOnFailureListener { e ->
                    Log.e("FirestoreHelper", "Error updating document", e)
                }
        }
    }

}
