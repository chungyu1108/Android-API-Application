# final-project-team-57
**********
Overview
***********
Steam Game Explorer is a dynamic application utilizing the Steam API, developed to enhance the gaming experience by allowing users to navigate, save, and categorize games from Steam's extensive library. The application interfaces with a Firestore database through the Firebase SDK, enabling users to organize their game preferences into categories such as "Want to Play," "I Like," or "Don't Like." This project aims to create a user-friendly platform for gamers to discover and keep track of their favorite games.

Features
API Integration
Steam API: Leveraging the free Steam API provided by Valve, our application fetches games based on categories, new releases, and user searches. More details can be found here.
Featured Games: Utilizing the featured endpoint from the Steam API, our app showcases currently highlighted games on the Steam storefront. This section includes different arrays for large capsules, featured Windows games, and featured MacOS games.
On Sale Games: An additional feature, time-permitting, will incorporate the salepage endpoint to display games currently on sale, helping users discover great deals.

UI Organization
Home Page: Features game categories, new releases, and a search functionality.
Category Pages: Dedicated pages for different game categories and user-specific lists.
Game Detail Page: Offers detailed information about each game, including price and a direct link to the Steam store page.

User Experience (UX)
Primary focus on pulling the first 50 results from the featured page, presented in an engaging and easy-to-navigate format.
Allows users to sort results based on personal preferences like "wants to play" and "likes".
Seamless navigation across different screens where the Firebase-hosted database displays user-categorized games.

Additional Features
Firebase SDK and Firestore: Integrates with Firestore to manage user data, storing saved games and preferences in a structured format, ensuring that users can revisit their selections anytime.
can you change the game class to include a key, image url, and a boolean value isFavorite.
Division of task
Member 1: Responsible for API integration and data fetching.
Member 2: In charge of database setup and management.
Member 3: Focuses on UI development and enhancing user experience.
Member 4: Takes on additional features, implementation, and testing.
